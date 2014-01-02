package com.ch018.library.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;

@Controller
@RequestMapping(value = "/librarian/orders")
public class LibrarianOrdersController {

	@Autowired
	OrdersService ordersService;
	@Autowired
	BookInUseService booksInUseService;
	@Autowired
	BookService bookService;
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "")
	public String showAll (Model model) throws Exception {
		
		/*Calendar calen = Calendar.getInstance();
		calen.add(Calendar.DAY_OF_YEAR, +3);
		Date date = new Date();
		date = calen.getTime();
		
		Book book = new Book();
		Person person = new Person();
		Orders order = new Orders();
		
		book = bookService.getBookById(3);
		person = personService.getById(1);
		
		order.setBook(book);
		order.setPerson(person);
		order.setOrderDate(date);
		
		ordersService.save(order);*/
		
		/*Calendar calen = Calendar.getInstance();
		calen.add(Calendar.DAY_OF_YEAR, 0);
		Date date = new Date();
		date = calen.getTime();
		
		Book book = new Book();
		Person person = new Person();
		Orders order = new Orders();
		
		book = bookService.getBookById(4);
		person = personService.getById(1);
		
		order.setBook(book);
		order.setPerson(person);
		order.setOrderDate(date);
		System.out.println(date + "Date from controller ++++++++");
		ordersService.save(order);*/
		
		model.addAttribute("orders", ordersService.getAll());
		
		ordersService.getOrdersToday();
		
		return "librarianorders";
	}
	
	
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String issue(@RequestParam("id") int id) throws Exception {
		
		Orders order = new Orders();
		order = ordersService.getOrderByID(id);
		ordersService.delete(order);
		
		BooksInUse  bookInUse = new BooksInUse();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, +14);
		Date date = new Date();
		date = calendar.getTime();
		
		bookInUse.setBook(order.getBook());
		bookInUse.setPerson(order.getPerson());
		bookInUse.setReturnDate(date);
		
		booksInUseService.save(bookInUse);
		return "redirect:/librarian/orders";
	}
	
	//Add book is not finished. But Dates work pretty good
		@RequestMapping(value = "/toissueinhour")
		public String toIssueInHour(Model model) throws SQLException {
			
			return "redirect:/librarian/books";
		}
		
	
	@RequestMapping (value = "/addOrder", method = RequestMethod.GET)
	public String addOrder (Model model) throws Exception {
		
		return "librarianaddOrder";
	}
	
	@RequestMapping (value = "/addOrder", method = RequestMethod.POST)
	public String addOrder () throws Exception {
		
		return "librarianaddOrder";
	}
	
	@RequestMapping (value = "/search", method = RequestMethod.GET)
	public String search(Model model) throws Exception {
		return "librarianorders";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam("request") String request) throws Exception {
		
		List<Orders> orders = ordersService.search(request);
		
		Orders order = new Orders();
		order = orders.get(0);
		
		System.out.println(order.getOrderDate() + "~~~~~~~~~~~~~~~~~");
		
		return "redirct:/librarian/orders";
	} 
}
