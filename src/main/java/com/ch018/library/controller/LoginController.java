/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
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
    
    
    @RequestMapping(value = "/")
    public String loginProcess(HttpServletRequest req, Authentication auth){
        
        if(auth != null && auth.isAuthenticated()){
            if(req.isUserInRole("ROLE_USER"))
                return "redirect:/books/";
            else if(req.isUserInRole("ROLE_LIBRARIAN"))
                return "redirect:/librarian/books";
            else if(req.isUserInRole("ROLE_ADMIN"))
                return "redirect:/admin";
        }
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "home";
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest req, Authentication auth){
        if(auth != null && auth.isAuthenticated()){
            if(req.isUserInRole("ROLE_USER") || req.isUserInRole("ROLE_ADMIN"))
                return "redirect:/books/";
            else if(req.isUserInRole("ROLE_LIBRARIAN"))
                return "redirect:/librarian";
        }
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginProcess(){
        return new ModelAndView("index");
    }
    
    
    @RequestMapping(value = "/denied")
    public String denied(){
        return "denied";
    }
    
    @RequestMapping(value = "/error")
    public String error(){
        return "error";
    }
}
