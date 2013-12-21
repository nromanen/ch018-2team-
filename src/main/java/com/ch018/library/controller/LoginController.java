/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Edd Arazian
 */
@Controller
public class LoginController {
    
    @Autowired
    PersonService pService;
    @Autowired
    BookService bService;
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "home";
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }
    
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, Model model){
            return "index";
        
        
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginProcess(){
        return new ModelAndView("index");
    }
    
}
