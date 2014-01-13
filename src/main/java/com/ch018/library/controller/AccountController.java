package com.ch018.library.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.Password;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;

// TODO: sort imports
/**
 *
 * @author Edd Arazian
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

        @Autowired
        private PersonService personService;
        
        @Autowired(required = true)
        @Qualifier("passwordvalidator")
        private Validator validator;

        final Logger logger = LoggerFactory.getLogger(AccountController.class);

        @RequestMapping(method = RequestMethod.GET)
        @Secured({"ROLE_USER"}) // TODO: extract as constants somewhere
        public String accountG(Model model, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            model.addAttribute("person", person);
            return "account";
        }

        @RequestMapping(value = "changeEmail", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public ResponseEntity<String> changeEmail(@RequestParam("email") String email, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            logger.info("person {} send request to email change to {}", person, email);
            if (personService.changeEmail(email, person)) {
                JSONObject json = new JSONObject();
                json.put("email", email);
                logger.info("person {} email changed to ", person, person.getEmail());
                return new ResponseEntity<>(json.toString(), HttpStatus.OK);
            } else 
                return new ResponseEntity<>("can't change email", HttpStatus.BAD_REQUEST); // TODO: extract as property
        }

        @RequestMapping(value = "changePassword", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public ResponseEntity<String> changePassword(@Valid @ModelAttribute() Password password, BindingResult result,
                                                        Principal principal, HttpServletResponse response) throws Exception{
            
            logger.info("person {} send request to password change", SecurityContextHolder.getContext().getAuthentication().getName());
            validator.validate(password, result);
            if(result.hasErrors()) {
                return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
            }
            if(personService.updatePassword(password, principal)){
                logger.info("person {} password succesfully changed", SecurityContextHolder.getContext().getAuthentication().getName());
                return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
            }
            else{
                logger.error("person {} password doesn't changed", SecurityContextHolder.getContext().getAuthentication().getName());
                return new ResponseEntity<>("password doesn't changed", HttpStatus.OK);
            }
        }

        @RequestMapping(value = "changeField", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public @ResponseBody String changeField(@RequestParam("fieldName") String fieldName, 
                                                  @RequestParam("fieldValue") String fieldValue) throws Exception {
            String field;
            if((field = personService.updateField(fieldName, fieldValue)) != null){
                JSONObject json = new JSONObject();
                json.put("field", field);
                return json.toString();
            }     
            else
                throw new IncorrectInput("Incorrect " + fieldName);
        }
}