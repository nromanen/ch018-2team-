package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;

@Controller
@RequestMapping(value = "/librarian/toreturn")
public class LibrarianReturnController {

	@Autowired
	BookInUseService booksInUseService;
	
	@RequestMapping(value = "")
	public String showAll(Model model) throws Exception {
		
		model.addAttribute("booksInUse", booksInUseService.getAll());
		return "librariantoreturn";
	}
	
	@RequestMapping(value = "/getback", method = RequestMethod.GET)
	public String getBookBack(@RequestParam("id") int id) throws Exception {
		
		BooksInUse  bookInUse = new BooksInUse();
		bookInUse = booksInUseService.getBookInUseById(id);
		
		booksInUseService.delete(bookInUse);
		
		return "redirect:/librarian/toreturn";
	}
	
	
}
