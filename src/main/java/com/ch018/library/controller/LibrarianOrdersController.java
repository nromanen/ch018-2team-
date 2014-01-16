package com.ch018.library.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	private final int DEFAULT_TERM = 14;
	private final String VALIDATION_FAILED = "Term must be between 1 and 70";
	private final int MIN_ISUUE = 1;
	private final int MAX_ISSUE = 70;
	
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
		person = personService.getById(11);
		
		order.setBook(book);
		order.setPerson(person);
		order.setOrderDate(date);
		
		ordersService.save(order);*/
		
		model.addAttribute("orders", ordersService.getAll());
		return "librarian_orders";
	}
	
	
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String issueGet(@RequestParam("id") int id, Model model) throws Exception {
		
		model.addAttribute("order", ordersService.getOrderByID(id));
		model.addAttribute("term", DEFAULT_TERM);
		
		return "librarian_orders_issue";
	}
	
	@RequestMapping(value = "/issue", method = RequestMethod.POST)
	public String issuePost(@RequestParam("id") int id, @RequestParam("term") int term, Model model) throws Exception {
		
		if( term <= MAX_ISSUE && term >= MIN_ISUUE){
			ordersService.issue(ordersService.getOrderByID(id), term);
			ordersService.delete(ordersService.getOrderByID(id));
		}else {
			model.addAttribute("order", ordersService.getOrderByID(id));
			model.addAttribute("term", DEFAULT_TERM);
			model.addAttribute("validation", VALIDATION_FAILED);
			return "librarian_orders_issue";
		}
		
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
