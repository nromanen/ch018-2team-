package com.ch018.library.controller;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.LanguageService;
import com.ch018.library.service.PersonService;
import com.ch018.library.validation.PersonEditValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/librarian/users")
public class LibrarianUsersController {

		private static final double DEFAULT_RATING = 0.5;
		private static final int FIRST_PAGE = 1;
	
		@Autowired
		private PersonService personService;
	
		@Autowired
		private BookInUseService bookInUseService;
	
		@Autowired
		private BCryptPasswordEncoder encoder;
		
		@Autowired
		private LanguageService langService;
	
		@RequestMapping(value = "")
		public String showAll(Model model) throws Exception {
			
			List<Person> person = personService.getAll();
	
			for (Person pers : person) {
				personService.countRating(pers);
			}
			model.addAttribute("users", personService.pagination(FIRST_PAGE));
			
			//Locale locale = LocaleContextHolder.getLocale();
	
			return "librarian_users";
		}
		
		@RequestMapping(value = "/orderbyname", method = RequestMethod.GET)
		public String orderByName(Model model) throws Exception {
			
			model.addAttribute("users", personService.orderByName());
			return "librarian_users";
		}
		
		@RequestMapping(value = "/orderbysurname", method = RequestMethod.GET)
		public String orderBySurame(Model model) throws Exception {
			
			model.addAttribute("users", personService.orderBySurname());
			return "librarian_users";
		}
		
		@RequestMapping(value = "/orderbyrating", method = RequestMethod.GET)
		public String orderByRating(Model model) throws Exception {
			
			model.addAttribute("users", personService.orderByRating());
			return "librarian_users";
		}
		
		@RequestMapping(value = "/adduser", method = RequestMethod.GET)
		public String addUser(Model model) throws Exception {
			Person user = new Person();
			model.addAttribute("user", user);
			return "librarian_add_user";
		}
	
		@RequestMapping(value = "/adduser", method = RequestMethod.POST)
		public String addUser(@ModelAttribute("user") @Valid Person user,
				BindingResult result) throws Exception {
			if (result.hasErrors()) {
				System.out.println("Errors Adding User" + result.toString());
				return "librarian_add_user";
			} else {
				user.setMultiBook(user.getBooksAllowed());
				user.setMailConfirm(true);
				user.setConfirm(true);
				String password = encoder.encode(user.getPassword());
				System.out.println("Password: " + password);
				user.setPassword(password);
				user.setProle("ROLE_USER");
				user.setTimelyReturn(0);
				user.setUntimekyReturn(0);
				user.setGeneralRating(DEFAULT_RATING);
				user.setFailedOrders(0);
	
				personService.save(user);
	
			}
	
			return "redirect:/librarian/users";
		}
	
		@RequestMapping(value = "/edituser", method = RequestMethod.GET)
		public String editUser(@RequestParam("id") int id, Model model)
				throws Exception {
			model.addAttribute("user", personService.getById(id));
			return "librarian_edit_user";
		}
	
		@RequestMapping(value = "/edituser", method = RequestMethod.POST)
		public String editUser(
				@ModelAttribute("user") @Valid PersonEditValidator user,
				BindingResult result, @RequestParam("pid") int pid, Model model)
				throws Exception {
	
			if (result.hasErrors()) {
				System.out.println("Error editing user: " + result.toString());
				return "librarian_edit_user";
			} else {
				personService.update(user);
			}
	
			return "redirect:/librarian/users";
		}
	
		@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
		public ResponseEntity<String> deleteUser(@RequestParam("id") int id, Model model) throws Exception {
			
			/*
			String error = "Error deleting books! This user has an order!";
			
			try {
				personService.delete(id);
			} catch (Exception e) {
				System.out.println("Error deleting books");
				model.addAttribute("exception", error);
				return "redirect:/librarian/users";
			}
			*/

            //personService.delete(id);
            System.out.println("ID: "+id);
            if (personService.delete(id))
            return new ResponseEntity ("Unable to delete the user. Reason: he has orders", HttpStatus.OK);
            return new ResponseEntity ("User was deleted", HttpStatus.OK);


		}


		@RequestMapping(value = "/advencedsearch", method = RequestMethod.GET)
		public String advencedSearch(Model model) throws Exception {
			Person person = new Person();
			model.addAttribute("user", person);
			return "librarian_user_advanced_search";
		}
	
		@RequestMapping(value = "/advencedsearch", method = RequestMethod.POST)
		public String advancedSearch(@ModelAttribute("user") Person user,
				BindingResult result, Model model) throws Exception {
			List<Person> person = personService.advancedSearch(user);
	
			if (person.size() > 0) {
				model.addAttribute("users", person);
			} else {
				model.addAttribute("users", personService.getAll());
			}
	
			return "librarian_users";
		}
	
		@RequestMapping(value = "/simplesearch", method = RequestMethod.GET)
		public String simpleSearch(Model model) throws Exception {
			return "librarian_users";
		}
	
		@RequestMapping(value = "/simplesearch", method = RequestMethod.POST)
		public String simpleSearch(@RequestParam("request") String request,
				Model model) throws Exception {
	
			if (request.equals("")) {
				model.addAttribute("users", personService.getAll());
				return "librarian_users";
			}
	
			List<Person> person = personService.simpleSearch(request);
	
			if (person.size() > 0) {
				model.addAttribute("users", person);
			} else {
				model.addAttribute("users", personService.getAll());
			}
			return "librarian_users";
		}
	
		@RequestMapping(value = "/readnow", method = RequestMethod.GET)
		public String usingBooks(@RequestParam("id") int id, Model model)
				throws Exception {
	
			Person person = personService.getById(id);
			List<BooksInUse> booksInUse = personService.getUsingBooks(person);
			model.addAttribute("person", person);
			model.addAttribute("booksInUse", booksInUse);
	
			return "librarian_using_books";
		}
}
