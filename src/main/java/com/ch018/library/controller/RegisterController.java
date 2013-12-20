/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.BookDaoImpl;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView addUser(@ModelAttribute Person person){
        try{
            System.out.println("+++++++++============");
            pService.save(person);
        }catch(Exception e){
            System.out.println(e + "+++++++++============");
            log.error(e);
            return new ModelAndView("unsuccessful");
        }
        
        return new ModelAndView("home", "person", pService.getByEmail(person.getEmail()));
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser(){
        return "index";
    }
    
}
