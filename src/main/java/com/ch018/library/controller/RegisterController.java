/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.BookDaoImpl;
import com.ch018.library.controller.errors.IncorrectDate;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.RegistrationFormValidator;
import com.ch018.library.validation.UserRegistrationForm;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

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
    
    final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Secured({"ROLE_ANONYMOUS"})
    public ResponseEntity<String> addUser(@Valid @ModelAttribute UserRegistrationForm form, BindingResult result) throws Exception{
        JSONObject json = new JSONObject();
        System.out.println(form.getCellPhone() + " " + form.getEmail());
        validator.validate(form, result);
        
        if(result.hasErrors()){
         
            return new ResponseEntity<>(result.getFieldError().getField(), HttpStatus.BAD_REQUEST);
        }
        
        Person person = new Person(form.getName(), form.getSurname(), form.getEmail(), form.getPassword(), form.getCellPhone());
        
        if(personService.getByEmail(person.getEmail()) != null){
            
            return new ResponseEntity<>("user exists", HttpStatus.BAD_REQUEST);
            
            //throw new IncorrectDate("user already exists");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        person.setProle("ROLE_USER");
        person.setMultiBook(5);
        
        personService.save(person);
        
        logger.info("new user {} registered", person);
        
        return new ResponseEntity<>( HttpStatus.OK);
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(Model model){
        
        
        return "redirect:/index";
    }
    
}
