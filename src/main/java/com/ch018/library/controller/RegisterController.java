/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.BookDAOImpl;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Edd Arazian
 */
@Controller
public class RegisterController {
    
    static Logger log = LogManager.getLogger(RegisterController.class);
    
    @Autowired
    PersonService pService;
    
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Secured({"ROLE_ANONYMOUS"})
    public String addUser(@ModelAttribute Person person){
        try{
            if(person.getName().equals("admin"))
                person.setProle("ROLE_ADMIN");
            else
                person.setProle("ROLE_USER");
            person.setConfirm(true); //temporary
            pService.save(person);
        }catch(Exception e){
            log.error(e);
            return "unsuccessful";
        }
        
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(){
        return "redirect:/index";
    }
    
}
