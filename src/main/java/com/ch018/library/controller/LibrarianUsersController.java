package com.ch018.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping(value = "/librarian/users")
public class LibrarianUsersController {

	@Autowired
	PersonService personService;
	@Autowired
	BookInUseService bookInUseService;
	
	@RequestMapping(value = "")
	public String showAll(Model model) throws Exception {
		
		 List<Person> person = personService.getAll();
                
                for (Person pers : person) {
                       personService.countRating(pers);
                }
                
                model.addAttribute("users", person);
                
                return "librarian/users"; 
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) throws Exception{
		
		Person user = new Person();
		model.addAttribute("user", user);
		
		return "librarian/adduser";
	}
	
	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") Person user, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			System.out.println("Errors Adding User" + result.toString());
		}else {
			personService.save(user);	
		}
		
		
		return "redirect:/librarian/users";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") int id, Model model) throws Exception {
		
		model.addAttribute("user", personService.getById(id));
		
		return "librarian/edituser";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.POST) 
	public String editUser(@ModelAttribute("user") Person user, BindingResult result, @RequestParam("pid") int pid,
			Model model) throws Exception {
		Person person = personService.getById(pid);
		String password = person.getPassword();
		String salt = person.getSalt();
		user.setPassword(password);
		personService.update(user);
		
		return "redirect:/librarian/users";
	}
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int id, Model model) throws Exception {
		
		personService.delete(id);
		
		return "redirect:/librarian/users";
	}
	
	@RequestMapping(value = "/advencedsearch", method = RequestMethod.GET)
	public String advencedSearch(Model model) throws Exception {
		
		Person person = new Person();
		model.addAttribute("user", person);
		
		return "librarian/usersadvencedsearch";
	}
	
	@RequestMapping(value = "/advencedsearch", method = RequestMethod.POST)
	public String advancedSearch(@ModelAttribute("user") Person user, BindingResult result, Model model) throws Exception {
		
		List<Person> person = personService.advancedSearch(user);
		
		model.addAttribute("users", person);
		
		return "librarian/users";
	}
	
	@RequestMapping(value = "/simplesearch", method = RequestMethod.GET)
	public String simpleSearch(Model model) throws Exception {
		
		return "librarian/users";
	}
	
	@RequestMapping(value = "/simplesearch", method = RequestMethod.POST)
	public String simpleSearch(@RequestParam("request") String request, Model model) throws Exception {
	
		List<Person> person = personService.simpleSearch(request);
		
		model.addAttribute("users", person);
		
		return "librarian/users";
	}
}
