package com.ch018.library.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.PersonEditValidator;

@Controller
@RequestMapping(value = "/librarian/users")
public class LibrarianUsersController {

	@Autowired
	private PersonService personService;
	@Autowired
	private BookInUseService bookInUseService;
	@Autowired
    BCryptPasswordEncoder encoder;
	
	@RequestMapping(value = "")

	public String showAll(Model model) throws Exception {
		
		 List<Person> person = personService.getAll();
        
		 for (Person pers : person) {
        	 personService.countRating(pers);
         }

		 model.addAttribute("users", person);  
         
		 
		 
		 return "librarian_users"; 
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String addUser(Model model) throws Exception{
		Person user = new Person();
		model.addAttribute("user", user);
		return "librarian_add_user";
	}
	
	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
	public String addUser( @ModelAttribute("user") @Valid Person user, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			System.out.println("Errors Adding User" + result.toString());
			return "librarian_add_user";
		}else {
			Person person = new Person();
			user.setMultiBook(user.getBooksAllowed());
			user.setMailConfirm(true);
			user.setConfirm(true);
			String password = encoder.encode(user.getPassword());
			System.out.println("Password: " + password);
			user.setPassword(password);
			user.setProle("ROLE_USER");
			user.setTimelyReturn(0);
			user.setUntimekyReturn(0);
			user.setFailedOrders(0);
			
			personService.save(user);

		}	
		
		return "redirect:/librarian/users";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") int id, Model model) throws Exception {
		model.addAttribute("user", personService.getById(id));
		return "librarian_edit_user";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.POST) 
	public String editUser(@ModelAttribute("user") @Valid PersonEditValidator user, BindingResult result, @RequestParam("pid") int pid,
			Model model) throws Exception {
		
		if (result.hasErrors()) {
			System.out.println("Error editing user: " + result.toString());
			return "librarian_edit_user";
		} else {
			personService.update(user);
		}
		
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
		return "librarian_user_advanced_search";
	}
	
	@RequestMapping(value = "/advencedsearch", method = RequestMethod.POST)
	public String advancedSearch(@ModelAttribute("user") Person user, BindingResult result, Model model) throws Exception {
		List<Person> person = personService.advancedSearch(user);
		
		if(person.size() > 0){
			model.addAttribute("users", person);
		}else {
			model.addAttribute("users", personService.getAll());
		}
		
		return "librarian_users";
	}
	
	@RequestMapping(value = "/simplesearch", method = RequestMethod.GET)
	public String simpleSearch(Model model) throws Exception {
		return "librarian_users";
	}
	
	@RequestMapping(value = "/simplesearch", method = RequestMethod.POST)
	public String simpleSearch(@RequestParam("request") String request, Model model) throws Exception {
		List<Person> person = personService.simpleSearch(request);
		
		if(person.size() > 0){
			model.addAttribute("users", person);
		}else {
			model.addAttribute("users", personService.getAll());
		}
		return "librarian_users";
	}
	
	@RequestMapping(value = "/readnow", method = RequestMethod.GET)
	public String usingBooks(@RequestParam("id") int id, Model model) throws Exception {
		
		Person person = personService.getById(id);
		List<BooksInUse> booksInUse = personService.getUsingBooks(person);
		model.addAttribute("person", person);
		model.addAttribute("booksInUse", booksInUse);
		
		return "librarian_using_books";
	}
}
