package com.ch018.library.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;
import com.ch018.library.exceptions.BookAlreadyRatedException;
import com.ch018.library.exceptions.BookUnavailableException;
import com.ch018.library.exceptions.IncorrectDateException;
import com.ch018.library.exceptions.TooManyOrdersException;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PaginationService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.RateService;
import com.ch018.library.service.RateServiceImpl;
import com.ch018.library.service.WishListService;
import com.ch018.library.util.Constans;
import com.ch018.library.util.SearchParamsRate;

/**
 * 
 * @author Edd Arazian
 */

@Controller
@RequestMapping(value = "/books/order")
public class OrderController {

	final Logger logger = LoggerFactory.getLogger(OrderController.class);

	private final static String SOMETHING_WRONG = "Something wrong. Stay Calm. We resolving problem...";
	
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
	@Autowired
	private RateService rateService;
	@Autowired
	private SearchParamsRate searchParams;
	@Autowired
	private PaginationService<Rate> paginationService;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public String orderGet(@PathVariable(value = "id") Integer bookId, Model model,
			Principal principal) {
		
		searchParams.setMainFieldsDefault();
		
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
		model.addAttribute("inUse", useService.isPersonHaveBook(person, book));
		model.addAttribute("inOrders", ordersService.isPersonOrderedBook(person, book));
		model.addAttribute("inWishList", wishService.isPersonWishBook(person, book));
		model.addAttribute("rate", rateService.getRate(person, book));
		model.addAttribute("recommend", bookService.getRecommended(Constans.AMOUNT_OF_RECOMMEND));
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
		} catch (IncorrectDateException | HibernateException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(SOMETHING_WRONG, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/my", method = RequestMethod.GET)
	@Secured({ "ROLE_USER" })
	public String myOrders(Model model, Principal principal) {
		Person person = personService.getByEmail(principal.getName());
		List<Orders> orders = ordersService.getOrderByPerson(person);

		model.addAttribute("orders", orders);
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
	public ResponseEntity<String> editOrder(@RequestParam("orderId") Integer orderId,
													@RequestParam("date") Long date, Principal principal)
													throws IncorrectInput {
		Person person = personService.getByEmail(principal.getName());
		Orders order;
		try {
			order = ordersService.editOrder(person, orderId, new Date(date));
		} catch (IncorrectDateException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(SOMETHING_WRONG, HttpStatus.BAD_REQUEST);
		}
		JSONObject json = new JSONObject();
		json.put("orderId", orderId);
		json.put("orderDate", order.getOrderDate().getTime());
		int days = (int) (order.getReturnDate().getTime() - order.getOrderDate().getTime()) / (24 * 3600 * 1000);
		json.put("days", days);
		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAdditionalOrders")
	public ResponseEntity<String> getAdditionalOrders(@RequestParam("bookId") Integer bookId,
															@RequestParam("time") Long time) {
		List<Orders> orders = new ArrayList<>();
		Book book = bookService.getBookById(bookId);
		long ordersCount = ordersService.getOrdersCountWithoutPerson(book);
		if(ordersCount >= book.getCurrentQuantity()) {
			Date date = new Date(time);
			orders = ordersService.getOrdersForPeriodFromMonth(book, date);
		}
		JSONObject jsonOrders = new JSONObject();
		List<JSONObject> jsons = new ArrayList<>();
		for(Orders order : orders) {
			JSONObject jsonOrder = new JSONObject();
			jsonOrder.put("orderDate", order.getOrderDate().getTime());
			jsonOrder.put("returnDate", order.getReturnDate().getTime());
			jsons.add(jsonOrder);
		}
		jsonOrders.put("orders", jsons);

		return new ResponseEntity<>(jsonOrders.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getMinOrderDate")
	public ResponseEntity<String> getMinOrderDate(@RequestParam("bookId") Integer bookId) {
		Book book = bookService.getBookById(bookId);
		Date date = null;
		try {
			date = ordersService.getMinOrderDate(book);
		} catch (TooManyOrdersException | BookUnavailableException | IncorrectDateException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(SOMETHING_WRONG, HttpStatus.BAD_REQUEST);
		}
		JSONObject minDate = new JSONObject();
		minDate.put("minDate", date.getTime());
		return new ResponseEntity<>(minDate.toString(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addRate")
	public ResponseEntity<String> addRate(@ModelAttribute Rate rate, @RequestParam("bookId") Integer bookId) {

		
		try {
			rateService.addRate(rate, bookId);
		} catch (BookAlreadyRatedException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
		JSONObject responseJson = new JSONObject();
		responseJson.put("score", rate.getScore());
		return new ResponseEntity<>(responseJson.toString(), HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/getComments")
	public ResponseEntity<String> getComments(@ModelAttribute SearchParamsRate tmpSearchParams) {
		logger.info("tmpS = {}", tmpSearchParams.getBook());
		
		if(searchParams.isMainFieldsEmpty())
			searchParams.setMainFieldsDefault();
		
		List<Rate> rates = paginationService.getPaginatedResult(searchParams, tmpSearchParams, Rate.class);
		
		JSONObject ratesJson = new JSONObject();
		List<JSONObject> arrayJsons = new ArrayList<>();
		for(Rate rate : rates) {
			JSONObject json = new JSONObject();
			json.put("name", rate.getPerson().getName());
			json.put("surname", rate.getPerson().getSurname());
			json.put("score", rate.getScore());
			json.put("message", rate.getMessage());
			
			arrayJsons.add(json);
		}
		
		ratesJson.put("comments", arrayJsons);
		ratesJson.put("pagesQuantity", searchParams.getPagesQuantity());
		
		return new ResponseEntity<String>(ratesJson.toString(), HttpStatus.OK);
		
		
	}
	
	
}
