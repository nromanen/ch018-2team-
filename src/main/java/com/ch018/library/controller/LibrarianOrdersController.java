package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Orders;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping(value = "/librarian/orders")
public class LibrarianOrdersController {

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
		model.addAttribute("orders", ordersService.getAll());
		return "librarian_orders";
	}
	
	
	@RequestMapping(value = "/issue", method = RequestMethod.GET)
	public String issueGet(@RequestParam("id") int id, Model model) throws Exception {
		
		Orders order = ordersService.getOrderByID(id);
		
		model.addAttribute("order", order);
		model.addAttribute("term", order.getDaysAmount());
		
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
			model.addAttribute("term", order.getDaysAmount());
			model.addAttribute("validation", "Please, enter correct value!");
			return "librarian_orders_issue";
		}
		
		if( termInt <= MAX_ISSUE && termInt >= MIN_ISUUE){
			ordersService.issue(order, termInt);
			ordersService.delete(ordersService.getOrderByID(id));
			return "redirect:/librarian/orders";
		}else {
			model.addAttribute("order", ordersService.getOrderByID(id));
			model.addAttribute("term", order.getDaysAmount());
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
	
    @RequestMapping(value= "/searchById", method = RequestMethod.POST)
    public String searchById(Model model,/*,BindingResult result@RequestParam("id") String id,*/@RequestParam("title") String title,@RequestParam("surname") String surname,@RequestParam("date") String date) throws Exception {
        int idSearch=0;
        int surnameSearch=0;
        int titleSearch=0;
        //if (id.compareTo("ID")!=0) idSearch++;
        if (surname.compareTo("Surname")!=0) surnameSearch++;
        if (title.compareTo("Title")!=0) titleSearch++;
        //if (idSearch==1&&surnameSearch==0&&titleSearch==0) model.addAttribute("orders",ordersService.getOrderByID(Integer.parseInt(id)));
        if (idSearch==0&&surnameSearch==1&&titleSearch==0) model.addAttribute("orders",ordersService.getOrdersByPersonSurname(ordersService.getAll(), surname));
        if (idSearch==0&&surnameSearch==0&&titleSearch==1) model.addAttribute("orders",ordersService.getOrdersByBookTitle(ordersService.getAll(),title));
        //if (idSearch==1&&surnameSearch==1&&titleSearch==0) model.addAttribute("orders",ordersService.getOrdersByOrdersId(ordersService.getOrdersByPersonSurname(ordersService.getAll(),surname),Integer.parseInt(id)));
        if (idSearch==0&&surnameSearch==1&&titleSearch==1) model.addAttribute("orders",ordersService.getOrdersByPersonSurname(ordersService.getOrdersByBookTitle(ordersService.getAll(),title),surname));
        //if (idSearch==1&&surnameSearch==0&&titleSearch==1) model.addAttribute("orders",ordersService.getOrdersByOrdersId(ordersService.getOrdersByBookTitle(ordersService.getAll(),title),Integer.parseInt(id)));
        //if (idSearch==1&&surnameSearch==1&&titleSearch==1) model.addAttribute("orders",ordersService.getOrdersByOrdersId(ordersService.getOrdersByBookTitle(ordersService.getOrdersByPersonSurname(ordersService.getAll(),surname),title),Integer.parseInt(id)));
        //System.out.println("!"+idSearch+"!"+surnameSearch+"!"+titleSearch);
        //model.addAttribute("orders", ordersService.getOrdersByPersonSurname(ordersService.getAll(),surname));/*.getOrdersByBookTitle(ordersService.getAll(), title)); /*getOrderByID(Integer.parseInt(id)));  /*Integer.parseInt(id))*/
        System.out.println(":"+surname+":"+date+":"+title);
        return "librarian_orders";
    }
	
}
