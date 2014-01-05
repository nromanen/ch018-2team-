package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;

@Controller
@RequestMapping(value = "/librarian/users")
public class LibrarianUsersController {

	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "")
	public String showAll(Model model) throws Exception {
		
		model.addAttribute("users", personService.getAll());
		
		return "librarianusers"; 
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) throws Exception{
		
		Person user = new Person();
		model.addAttribute("user", user);
		
		return "librarianadduser";
	}
	
	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") Person user, Model model) throws Exception {
		
		personService.save(user);
		
		return "";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") int id, Model model) throws Exception {
		
		model.addAttribute("user", personService.getById(id));
		
		return "librarianedituser";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.POST) 
	public String editUser(@ModelAttribute("user") Person user, BindingResult result, @RequestParam("pid") int pid,
			Model model) throws Exception {
		Person person = personService.getById(pid);
		String password = person.getPassword();
		String salt = person.getSalt();
		System.out.println("User ID = " + pid + "Password: " + password + "Salt: " + salt);
		user.setPassword(password);
		personService.update(user);
		
		return "redirect:/librarian/users";
	}
	
}
