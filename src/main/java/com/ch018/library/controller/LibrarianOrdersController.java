package com.ch018.library.controller;


import com.ch018.library.DAO.OrdersDao;
import com.ch018.library.entity.Orders;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/librarian/orders")
public class LibrarianOrdersController {

		private static final String VALIDATION_FAILED = "Term must be between 1 and 70";
		private static final int MIN_ISUUE = 1;
		private static final int MAX_ISSUE = 70;
		private static final long MILLIS_IN_DAY = 24 * 3600 * 1000;
		
		@Autowired
		private OrdersService ordersService;
    
		@Autowired
		private OrdersDao ordersDao;
		
		@Autowired
		private BookInUseService booksInUseService;
		
		@Autowired
		private BookService bookService;
		
		@Autowired
		private PersonService personService;
	
		
		@RequestMapping(value = "")
		public String showAll(Model model) throws Exception {
			
			List<Orders> orders = ordersService.getAll();
			
			System.out.println("Orders size:" + orders.size());
			
			model.addAttribute("orders", ordersService.getAll());
			return "librarian_orders";
		}
		
		
		@RequestMapping(value = "/issue", method = RequestMethod.GET)
		public String issueGet(@RequestParam("id") int id, Model model){
			
			Orders order = ordersService.getOrderByID(id);
			int maxIssueDays = 0;
			try {
				maxIssueDays = ordersService.getMaxIssueDays(order);
			} catch (Exception e) {
				System.out.println("UNAVAIL");
			}
			model.addAttribute("order", order);
			model.addAttribute("term", maxIssueDays);
			
			return "librarian_orders_issue";
		}
		
		@RequestMapping(value = "/issue", method = RequestMethod.POST)
		public String issuePost(@RequestParam("id") int id, @RequestParam("term") String term, Model model) throws Exception {
			
			int termInt = 0;
			Orders order = ordersService.getOrderByID(id);
			
			try {
				termInt = Integer.parseInt(term);
			} catch (Exception e) {
				
				model.addAttribute("order", ordersService.getOrderByID(id));
				model.addAttribute("term", (order.getReturnDate().getTime() - order.getOrderDate().getTime())/MILLIS_IN_DAY);
				model.addAttribute("validation", "Please, enter correct value!");
				return "librarian_orders_issue";
			}
			
			if ((termInt <= MAX_ISSUE) && (termInt >= MIN_ISUUE)) {
				ordersService.issue(order, termInt);
				ordersService.delete(ordersService.getOrderByID(id));
				return "redirect:/librarian/orders";
			} else {
				model.addAttribute("order", ordersService.getOrderByID(id));
				model.addAttribute("term", (order.getReturnDate().getTime() - order.getOrderDate().getTime())/MILLIS_IN_DAY);
				model.addAttribute("validation", VALIDATION_FAILED);
				return "librarian_orders_issue";
			}
			
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
	
	    @RequestMapping(value = "/searchById", method = RequestMethod.POST)
	    public String searchById(Model model,@RequestParam("title") String title,@RequestParam("surname") String surname,@RequestParam("date") String date) throws Exception {
	
	        int surnameSearch = 0;
	        int titleSearch = 0;
	
	        if (surname.compareTo("Surname")!=0) surnameSearch++;
	        if (title.compareTo("Title")!=0) titleSearch++;
	        if (surnameSearch==1&&titleSearch==0) model.addAttribute("orders",ordersService.getOrdersByPersonSurname(ordersService.getAll(), surname));
	        if (surnameSearch==0&&titleSearch==1) model.addAttribute("orders",ordersService.getOrdersByBookTitle(ordersService.getAll(),title));
	        if (surnameSearch==1&&titleSearch==1) model.addAttribute("orders",ordersDao.testCriteria(title, surname)); //model.addAttribute("orders",ordersService.getOrdersByPersonSurname(ordersService.getOrdersByBookTitle(ordersService.getAll(),title),surname));
            //System.out.println("Controller "+ordersDao.testCriteria(title,surname));
            return "librarian_orders";
	    }

    @RequestMapping(value = "/sortSurname")
    public String sSurname(Model model) throws Exception {

        model.addAttribute("orders", ordersService.sortOrdersBySurname());
        return "librarian_orders";
    }
	
}
