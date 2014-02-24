package com.ch018.library.controller;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PaginationService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.PageContainer;
import com.ch018.library.util.SearchParamsPerson;
import com.ch018.library.util.Switch;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    
		private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
        @Autowired
        private PersonService personService;
        
        @Autowired
        private PaginationService<Person> paginationService;
        
        @Autowired
        private SearchParamsPerson searchParams;
        
        @Autowired
        PageContainer<Person> pageContainer;
        
        @Autowired
        private Switch switcher;

        @RequestMapping(method = RequestMethod.GET)
        public String admin(@ModelAttribute SearchParamsPerson tmpSearchParams, Model model) {
        	
        	List<Person> persons = null;
        	
        	if(searchParams.isMainFieldsEmpty())
        		searchParams.setMainFieldsDefault();
        	
        	persons = paginationService.getPaginatedResult(searchParams, tmpSearchParams, Person.class);
        	
            model.addAttribute("persons", persons);
            model.addAttribute("roles", Arrays.asList("ROLE_USER", "ROLE_LIBRARIAN"));
            model.addAttribute("switcher", switcher);
            return "admin";
        }
        
        @RequestMapping(value = "/syssetingsSearch", method = RequestMethod.POST)
        public ResponseEntity<String> changeSysSettings(@RequestParam(value = "switcher") Boolean sw) {
        	logger.info("switcher = {}", switcher.getSwitcher());
        	
        	if(sw) {
        		switcher.setSwitcher(Boolean.TRUE);
        		return new ResponseEntity<>("true", HttpStatus.OK);
        	}
        	else {
        		switcher.setSwitcher(Boolean.FALSE);
        		return new ResponseEntity<>("false", HttpStatus.OK);
        	}
        }
        
        @RequestMapping(value = "/syssetingsRecommendation", method = RequestMethod.POST)
        public ResponseEntity<String> changeRecommendationSetting(@RequestParam(value = "recommendation") Boolean sw) {
        	logger.info("recommend = {}", switcher.getRecommendationState());
        	if(sw) {
        		switcher.setRecommendationState(Boolean.TRUE);
        		return new ResponseEntity<>("true", HttpStatus.OK);
        	}
        	else {
        		switcher.setRecommendationState(Boolean.FALSE);
        		return new ResponseEntity<>("false", HttpStatus.OK);
        	}
        	
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
                person.setPersonRole(role);
                personService.update(person);
                return new JSONObject().toString();
            } catch(Exception e) {
                throw new IncorrectInput("problems during changing role");
            }
        }
        
        
}