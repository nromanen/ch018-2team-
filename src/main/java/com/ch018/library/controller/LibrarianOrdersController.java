package com.ch018.library.controller;

import java.sql.SQLException;
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
		person = personService.getById(3);
		
		order.setBook(book);
		order.setPerson(person);
		order.setOrderDate(date);
		
		ordersService.save(order);*/
		
		model.addAttribute("orders", ordersService.getAll());
		return "librarian_orders";
	}
	
	
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String issue(@RequestParam("id") int id) throws Exception {
		ordersService.issue(ordersService.getOrderByID(id));
		ordersService.delete(ordersService.getOrderByID(id));
		return "redirect:/librarian/orders";
	}
	
	@RequestMapping(value = "/toissueinhour")
	public String toIssueInHour(Model model) throws SQLException {
		List<Orders> orders = ordersService.getOrdersInHour();
		model.addAttribute("orders", orders);
		return "librarian_orders";
	}
		
	@RequestMapping(value = "/toissuetoday")
	public String toIssueToday(Model model) throws Exception {
		List<Orders> orders = ordersService.getOrdersToday();
		model.addAttribute("orders", orders);
		return "librarian_orders";
	}
	
}
