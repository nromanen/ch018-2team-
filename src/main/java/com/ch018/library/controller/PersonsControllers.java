package com.ch018.library.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

@Controller
public class PersonsControllers {

	@Autowired
	private PersonService personService;
	@Autowired
	private BookService bookService;
	
	/*@RequestMapping(value = "/")
	public String listOfStudents(Model model) throws Exception {
		
		model.addAttribute("persons", personService.getAllPersons());
		
		return "persons";
	}*/
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.GET)
	public String personOptions(@RequestParam("id") int id, Model model) throws Exception {
		

		model.addAttribute("person", personService.getPersonById(id));
		model.addAttribute("book", bookService.getAllBooks());
		
		return "addOrder";
	}
	
	@RequestMapping(value = "/personsList")
	public String personsList(Model model) throws SQLException {
		model.addAttribute("person", personService.getAllPersons());

		return "person";
	}
}
