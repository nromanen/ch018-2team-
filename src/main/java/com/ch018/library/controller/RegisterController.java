package com.ch018.library.controller;


import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.UserRegistrationForm;
import javax.validation.Valid;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Edd Arazian
 */
@Controller
public class RegisterController {
        @Autowired
        PersonService personService;
        
        @Autowired
        BCryptPasswordEncoder encoder;
        
        @Autowired(required = true)
        @Qualifier("registrationformvalidator")
        Validator validator;
        
        @Autowired(required = true)
        @Qualifier("passwordvalidator")
        Validator validatorPass;
        
        final Logger logger = LoggerFactory.getLogger(RegisterController.class);

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        @Secured({"ROLE_ANONYMOUS"})
        public ResponseEntity<String> addUser(@Valid @ModelAttribute UserRegistrationForm form, BindingResult result) {
            validator.validate(form, result);
            if(result.hasErrors()){
                FieldError fieldError = result.getFieldError();
                logger.info("incorrect {} entered", fieldError);
                return new ResponseEntity<>(fieldError.getField(), HttpStatus.BAD_REQUEST);
            }
            if(personService.register(form))
                return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
            else
                return new ResponseEntity<>("problems with registration", HttpStatus.BAD_REQUEST);
            
        }
        
        @RequestMapping(value = "/confirm", method = RequestMethod.GET)
        public String confirmation(@RequestParam("key") String key){
           
            if(personService.confirmMail(key))
                return "redirect:/index";
            else
                return "error";
        }
        
        @RequestMapping(value = "/restore", method = RequestMethod.POST)
        public ResponseEntity<String> restore(@RequestParam("email") String email) {
            
                if(personService.restoreSendEmail(email)){
                    return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
                }
                return new ResponseEntity<>("email not exists", HttpStatus.BAD_REQUEST);
                       
        }
        
        @RequestMapping(value = "/restore/password", method = RequestMethod.GET)
        public String restorePass(@RequestParam("key") String key, Model model) {
            if(personService.isKeyValid(key)) {
                model.addAttribute("key", key);
                return "restore";
            }
            return "error";
        } 
        
        @RequestMapping(value = "/restore/password", method = RequestMethod.POST)
        public ResponseEntity<String> restorePassPost(@RequestParam("key") String key,@Valid @ModelAttribute Password password
                                                                , BindingResult result){
            validatorPass.validate(password, result);
            if(result.hasErrors())
                return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
            if(personService.restorePass(key, password))
                return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
            return new ResponseEntity<>("error during restore", HttpStatus.BAD_REQUEST);
        }
        

        @RequestMapping(value = "/register", method = RequestMethod.GET)
        public String addUser(Model model){
            return "redirect:/index";
        }

}
