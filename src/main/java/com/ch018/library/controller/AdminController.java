package com.ch018.library.controller;

import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.SearchParamsPerson;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
        @Autowired
        private PersonService personService;
        
        @Autowired
        private SearchParamsPerson searchParams;

        @RequestMapping(method = RequestMethod.GET)
        public String admin(@ModelAttribute SearchParamsPerson tmpSearchParams, Model model) {
        	
        	if(!searchParams.isInit())
        		searchParams.setDefaults();
        	else
        		searchParams.update(tmpSearchParams);
        	
            model.addAttribute("persons", personService.getPersonsBySessionParams());
            model.addAttribute("roles", Arrays.asList("ROLE_USER", "ROLE_LIBRARIAN"));
            return "admin";
        }
        
        
        @RequestMapping(value = "/delete", method = RequestMethod.POST)
        public @ResponseBody String delete(@RequestParam("id") Integer id) throws IncorrectInput {
            try {
                personService.delete(id);
                return new JSONObject().toString();
            } catch(Exception e) {
                throw new IncorrectInput("problem during user deleting");
            }

        }
        
        @RequestMapping(value = "change", method = RequestMethod.POST)
        public @ResponseBody String changeRole(@RequestParam("id") Integer id, @RequestParam("role") String role) throws IncorrectInput {
            try {
                Person person = personService.getById(id);
                person.setProle(role);
                personService.update(person);
                return new JSONObject().toString();
            } catch(Exception e) {
                throw new IncorrectInput("problems during changing role");
            }
        }
}