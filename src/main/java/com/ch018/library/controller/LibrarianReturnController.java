package com.ch018.library.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

@Controller
@RequestMapping(value = "/librarian/toreturn")
public class LibrarianReturnController {

	@Autowired
	BookInUseService booksInUseService;
	@Autowired
	PersonService personService;
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "")
	public String showAll(Model model) throws Exception {
		
		model.addAttribute("booksInUse", booksInUseService.getAll());
		return "librarian/toreturn";
	}
	
	@RequestMapping(value = "/getback", method = RequestMethod.GET)
	public String getBookBack(@RequestParam("id") int id) throws Exception {
		
		BooksInUse  bookInUse = new BooksInUse();
		bookInUse = booksInUseService.getBookInUseById(id);
		
		Date now = new Date();
		
		Person person = bookInUse.getPerson();
		int booksReturnedIntime = person.getTimelyReturn();
		int booksReturnedNotIntime = person.getUntimekyReturn();
		
		if (now.before(bookInUse.getReturnDate())) {
			System.out.println("BRIT " + booksReturnedIntime);
			System.out.println("before well done");
			booksReturnedIntime += 1;
			person.setTimelyReturn(booksReturnedIntime);
			personService.update(person);
			System.out.println("BRIT " + booksReturnedIntime);
		}else if (now.after(bookInUse.getReturnDate())) {
			System.out.println("BRNIT " + booksReturnedNotIntime);
			System.out.println("after means too late");
			booksReturnedNotIntime += 1;
			person.setUntimekyReturn(booksReturnedNotIntime);
			personService.update(person);
			System.out.println("BRNIT " + booksReturnedNotIntime);
		}
			
		Book book = bookInUse.getBook();
		int quantity = book.getCurrentQuantity();
		quantity += 1;
		book.setCurrentQuantity(quantity);
		bookService.update(book);
		
		//booksInUseService.delete(bookInUse);
		
		return "redirect:/librarian/toreturn";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) throws Exception {
		
		model.addAttribute("inuse", booksInUseService.getBookInUseById(id));
		
		return "librarian/edittoreturn";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@RequestParam("id") int id, @RequestParam("days") int days) throws Exception {
		
		System.out.println("Days: " + days + "ID: " + id);
		
		BooksInUse bookInUse = booksInUseService.getBookInUseById(id);
		Date date = bookInUse.getReturnDate();
		System.out.println("Return date: " + date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		date = calendar.getTime();
		
		System.out.println("Increased date is: " + date);
		
		
		bookInUse.setReturnDate(date);
		
		booksInUseService.update(bookInUse);
		
		return "redirect:/librarian/toreturn";
	}
	
}
