package com.ch018.library.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.exceptions.DeleteSecurityViolationException;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;

/**
 * 
 * @author Edd Arazian
 */
@Controller
@RequestMapping(value = "/books/wishlist")
public class WishListController {

		@Autowired
		private WishListService wishService;
		
		@Autowired
		private PersonService personService;
		
		@Autowired
		private BookService bookService;
		
		@Autowired
		private BookInUseService useService;
		
		@Autowired
		private OrdersService ordersService;
	
		final Logger logger = LoggerFactory.getLogger(WishListController.class);
	
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ResponseEntity<String> add(@RequestParam("bookId") Integer bookId, Principal principal) {
			String email = principal.getName();
			Person person = personService.getByEmail(email);
			Book book = bookService.getBookById(bookId);
			WishList wish = new WishList(person, book);
			try {
				wishService.save(wish);
			} catch (ConstraintViolationException e) {
				return new ResponseEntity<>("Book already in WishList", HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				return new ResponseEntity<>("Something wrong. Stay calm. We resolving problem...", HttpStatus.BAD_REQUEST);
			}
			JSONObject json = new JSONObject();
			json.put("title", book.getTitle());
			return new ResponseEntity<>(json.toString(), HttpStatus.OK);
	
		}
	
		@RequestMapping(value = "/my", method = RequestMethod.GET)
		@Secured({ "ROLE_USER" })
		public String myWishList(Model model, Principal principal) {
			Person person = personService.getByEmail(principal.getName());
			List<WishList> wishes = wishService.getWishByPerson(person);
			model.addAttribute("wishes", wishes);
			return "wishlist";
		}
	
		@RequestMapping(value = "/delete")
		public @ResponseBody ResponseEntity<String> delete(@RequestParam("wishId") Integer wishId) {
			try {
				wishService.remove(wishService.getWishByID(wishId));
			} catch (DeleteSecurityViolationException e) {
				logger.error(e.getMessage());
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			JSONObject json = new JSONObject();
			return new ResponseEntity<>(json.toString(), HttpStatus.OK);
		}

}
