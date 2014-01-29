package com.ch018.library.controller;

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

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.Switch;
import com.ch018.library.service.PersonService;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "/admin")
@Secured({ "ROLE_ADMIN" })
public class AdminController {
    
        @Autowired
        private PersonService personService;
        
        @Autowired
        private Switch switcher;

        @RequestMapping(method = RequestMethod.GET)
        public String admin(@RequestParam(value = "page", required = false) Integer page,
        						@RequestParam(value = "orderField", required = false) String orderField,
        						@RequestParam(value = "order", required = false) Boolean order, Model model) {
        	
            model.addAttribute("persons", personService.getAll());
            model.addAttribute("roles", Arrays.asList("ROLE_USER", "ROLE_LIBRARIAN"));
            model.addAttribute("switcher", switcher.getValue());
            return "admin";
        }
        
        @RequestMapping(value = "/syssetings", method = RequestMethod.POST)
        public String changeSysSettings(@RequestParam(value = "switcher", required = false) String sw) {
        	if(sw == null) {
        		switcher.setValue(Boolean.FALSE);
        		return "redirect:/admin";
        	}
        	if(sw.equals("on"))
        		switcher.setValue(Boolean.TRUE);
        	else
        		switcher.setValue(Boolean.FALSE);
        	return "redirect:/admin";
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