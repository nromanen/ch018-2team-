/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Edd Arazian
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String accountG(Model model, Principal principal){
        Person person = personService.getByEmail(principal.getName());
        model.addAttribute("person", person);
        return "account";
    }
    
}
