package com.ch018.library.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import com.ch018.library.util.OrderDays;

/**
 * 
 * @author Edd Arazian
 */

@Controller
@RequestMapping(value = "/books/order")
public class OrderController {

	final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private BookService bookService;
	@Autowired
	private PersonService personService;
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private WishListService wishService;
	@Autowired
	private BookInUseService useService;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public String orderGet(@PathVariable(value = "id") Integer bookId, Model model,
			Principal principal) {
		if(bookId == null) {
			return "redirect:/books";
		}
		
		Book book = bookService.getBookById(bookId);
		model.addAttribute("book", book);
		if(principal == null) {
			return "order";
		}

		Person person = personService.getByEmail(principal.getName());
		
		boolean limit = ordersService.isLimitReached(person);
		model.addAttribute("isBookLimitReached", limit);
		if (limit) {
			model.addAttribute("inUse", true);
			return "order";
		}
		
		OrderDays minDate = ordersService.getMinOrderDate(book);
		//model.addAttribute("orders", minDate.getOrders());
		model.addAttribute("inUse", useService.isPersonHaveBook(person, book));
		model.addAttribute("inOrders",
				ordersService.isPersonOrderedBook(person, book));
		model.addAttribute("inWishList",
				wishService.isPersonWishBook(person, book));
		
		model.addAttribute("minDate", minDate.getMinOrderDate().getTime());
		//model.addAttribute("days", minDate.getDaysAvailable());
		return "order";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Secured({ "ROLE_USER" })
	public ResponseEntity<String> addOrder(
				@RequestParam("bookId") Integer bookId,
				@RequestParam("time") Long time, Principal principal)
						throws IncorrectInput {
		
		Person person = personService.getByEmail(principal.getName());
		Date date = new Date(time);
		logger.info("add order time {}", new Date(time));
		try {
			ordersService.addOrder(person, bookId, date);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage().equals("Incorrect Date Choosen") ? e.getMessage() : "something wrong", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	@Secured({ "ROLE_USER" })
	public String myOrders(Model model, Principal principal) {
		Person person = personService.getByEmail(principal.getName());
		List<Orders> orders = ordersService.getOrderByPerson(person);
		Map<Orders, OrderDays> ordersMinDates = new HashMap<>();
		for (Orders order : orders) {
			ordersMinDates.put(order,
					ordersService.getMinOrderDate(order.getBook()));
		}
		model.addAttribute("ordersMinDates", ordersMinDates);
		return "orders";

	}

	@RequestMapping(value = "/delete")
	@Secured({ "ROLE_USER" })
	public @ResponseBody
	String deleteOrder(@RequestParam("orderId") Integer orderId) {
		ordersService.delete(ordersService.getOrderByID(orderId));
		return new JSONObject().toString();
	}

	@RequestMapping(value = "/edit")
	@Secured({ "ROLE_USER" })
	public ResponseEntity<String> editOrder(
			@RequestParam("orderId") Integer orderId,
			@RequestParam("date") Long date, Principal principal)
			throws IncorrectInput {
		Person person = personService.getByEmail(principal.getName());
		Orders order;
		try {
			order = ordersService.editOrder(person, orderId, new Date(date));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		JSONObject json = new JSONObject();
		OrderDays minDate = ordersService.getMinOrderDate(order.getBook());
		json.put("orderId", orderId);
		json.put("date", date);
		json.put("minDate", minDate.getMinOrderDate().getTime());
		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAdditionalOrders")
	public ResponseEntity<String> getAdditionalOrders(@RequestParam("bookId") Integer bookId,
															@RequestParam("time") Long time) {
		Book book = bookService.getBookById(bookId);
		Date date = new Date(time);
		List<Orders> orders = ordersService.getOrdersForPeriodFromMonth(book, date);
		JSONObject jsonOrders = new JSONObject();
		List<JSONObject> jsons = new ArrayList<>();
		for(Orders order : orders) {
			JSONObject jsonOrder = new JSONObject();
			jsonOrder.put("orderDate", order.getOrderDate().getTime());
			jsonOrder.put("days", order.getDaysAmount());
			jsons.add(jsonOrder);
		}
		jsonOrders.put("orders", jsons);

		return new ResponseEntity<>(jsonOrders.toString(), HttpStatus.OK);
	}

}
