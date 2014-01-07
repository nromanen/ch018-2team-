/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.controller.errors.IncorrectDate;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import java.util.Arrays;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {
    
    @Autowired
    PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    
    public String admin(Model model){
        model.addAttribute("persons", personService.getAll());
        model.addAttribute("roles", Arrays.asList("ROLE_USER", "ROLE_LIBRARIAN"));
        return "admin";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody String delete(@RequestParam("id") Integer id) throws IncorrectDate{
        
        try{
            personService.delete(id);
            return new JSONObject().toString();
        }catch(Exception e){
            throw new IncorrectDate("problem during user deleting");
        }
        
    }
    
    @RequestMapping(value = "change", method = RequestMethod.POST)
    public @ResponseBody String changeRole(@RequestParam("id") Integer id, @RequestParam("role") String role) throws IncorrectDate{
        try{
            Person person = personService.getById(id);
            person.setProle(role);
            personService.update(person);
            return new JSONObject().toString();
        }catch(Exception e){
            throw new IncorrectDate("problems during changing role");
        }
    }
}
