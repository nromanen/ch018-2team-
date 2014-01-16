package com.ch018.library.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

@Controller
@RequestMapping(value = "/librarian/toreturn")
public class LibrarianReturnController {

	private final String VALIDATION_FAILED = "Days must be between 1 and 15";
	private final int MIN_ISUUE = 1;
	private final int MAX_ISSUE = 15;
	
		@Autowired
		private BookInUseService booksInUseService;
		@Autowired
		private PersonService personService;
		@Autowired
		private BookService bookService;
		
		@RequestMapping(value = "")
		public String showAll(Model model) throws Exception {
			model.addAttribute("booksInUse", booksInUseService.getAll());
			return "librarian_toreturn";
		}
		
		@RequestMapping(value = "/getback", method = RequestMethod.GET)
		public String getBookBack(@RequestParam("id") int id, Model model) throws Exception {
			BooksInUse  bookInUse = new BooksInUse();
			bookInUse = booksInUseService.getBookInUseById(id);
			model.addAttribute("bookInUse", bookInUse);
			return "librarian_getbookback";
		}
		
		@RequestMapping(value = "/getback", method = RequestMethod.POST)
		public String getBack(@RequestParam("id") int id, Model model) throws Exception {
			BooksInUse  bookInUse = new BooksInUse();
			bookInUse = booksInUseService.getBookInUseById(id);
			booksInUseService.getBookBack(booksInUseService.getBookInUseById(id));
			return "redirect:/librarian/toreturn";
		}
		
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public String edit(@RequestParam("id") int id, Model model) throws Exception {
			model.addAttribute("inuse", booksInUseService.getBookInUseById(id));
			return "librarian_edittoreturn";
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public String edit(@RequestParam("id") int id, @RequestParam("days") int days, Model model) throws Exception {
			
			if( days <= MAX_ISSUE && days >= MIN_ISUUE){
			BooksInUse bookInUse = booksInUseService.getBookInUseById(id);
			Date date = bookInUse.getReturnDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, days);
			date = calendar.getTime();
			bookInUse.setReturnDate(date);
			booksInUseService.update(bookInUse);
			} else {
				model.addAttribute("inuse", booksInUseService.getBookInUseById(id));
				model.addAttribute("validation", VALIDATION_FAILED);
				return "librarian_edittoreturn";
			}
			return "redirect:/librarian/toreturn";
		}
		
}
