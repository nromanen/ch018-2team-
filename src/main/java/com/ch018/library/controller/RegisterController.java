/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.BookDaoImpl;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Secured({"ROLE_ANONYMOUS"})
    public String addUser(@ModelAttribute Person person, @RequestParam("rPassword") String rPassword){
        
        if(!person.getPassword().equals(rPassword) || personService.getByEmail(person.getEmail()) != null)
            return "unsuccessful";
        
        
        
        person.setPassword(encoder.encode(person.getPassword()));
        
        
        personService.save(person);
        
        logger.info("new user {} registered", person);
        
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(){
        return "redirect:/index";
    }
    
}
