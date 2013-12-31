package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.OrdersService;

@Controller
@RequestMapping(value = "/librarian/orders")
public class LibrarianOrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	BookInUseService booksInUseService;
	
	@RequestMapping (value = "")
	public String showAll (Model model) throws Exception {
		
		
		return "librarianorders";
	}
	
	@RequestMapping (value = "/addOrder", method = RequestMethod.GET)
	public String addOrder (Model model) throws Exception {
		
		return "librarianaddOrder";
	}
	
	@RequestMapping (value = "/addOrder", method = RequestMethod.GET)
	public String addOrder () throws Exception {
		
		return "librarianaddOrder";
	}
}
