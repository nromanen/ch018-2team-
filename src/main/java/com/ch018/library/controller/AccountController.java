package com.ch018.library.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.PersonalInfo;
import org.springframework.validation.ObjectError;
import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.Password;

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
        @Secured({"ROLE_USER"})
        public String account(Model model, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            model.addAttribute("person", person);
            return "account";
        }

        @RequestMapping(value = "changeEmail", method = RequestMethod.POST)
        public ResponseEntity<String> changeEmail(@RequestParam("email") String email, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            logger.info("person {} send request to email change to {}", person, email);
            try {
                personService.changeEmail(email, person);
                JSONObject json = new JSONObject();
                json.put("email", email);
                logger.info("person {} email changed to ", person, person.getEmail());
                return new ResponseEntity<>(json.toString(), HttpStatus.OK);
            } catch(Exception e) { 
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
            }
        }

        @RequestMapping(value = "changePassword", method = RequestMethod.POST)
        public ResponseEntity<String> changePassword(@Valid @ModelAttribute Password password, BindingResult result,
                                                        Principal principal) {
            logger.info("person {} send request to password change", principal.getName());
            validator.validate(password, result);
            if(result.hasErrors()) {
                return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST); 
            }
            Person person = personService.getByEmail(principal.getName());
            try {
                personService.updatePassword(password, person);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
            }
            logger.info("person {} password succesfully changed", SecurityContextHolder.getContext().getAuthentication().getName());
            return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK); 
        }
        
        @RequestMapping(value = "updatePersonalInfo", method = RequestMethod.POST)
        public ResponseEntity<String> updatePersonalInfo(@Valid @ModelAttribute PersonalInfo info, BindingResult result
                                                    , Principal principal ) {
            if (result.hasErrors()) {
                return new ResponseEntity<>(getErrors(result), HttpStatus.BAD_REQUEST); 
            
            }
            try {
                Person person = personService.getByEmail(principal.getName());
                personService.updatePersonalInfo(person, info);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
        }
        
       private String getErrors(BindingResult result) {
           StringBuilder sb = new StringBuilder();
           for (ObjectError error : result.getAllErrors()) {
                    sb.append(error.getDefaultMessage());
                    sb.append("\n\r");
                }
           return sb.toString();
       }
}