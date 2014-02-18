package com.ch018.library.controller;

import java.security.Principal;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context.xml"})
@WebAppConfiguration
public class WishListControllerTest {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private WishListService wishService;

	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private Person person;
	private Book book;
	
	
	@Before
	public void setup() {
		Mockito.reset(personService);
		Mockito.reset(bookService);
		Mockito.reset(wishService);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		person = new Person();
		person.setPid(1);
		person.setName("testName");
		person.setName("testSurname");
		person.setEmail("testEmail");
		person.setPassword("111111");
		person.setPersonRole("ROLE_USER");
		
		book = new Book();
		book.setbId(1);
		book.setTitle("book");
		
	}
	
	@Test
	public void deleteOrder() throws Exception {
		
		
		
		when(wishService.getWishByID(1)).thenReturn(new WishList());
		
		
		String response = "{}";
		
		String str = mockMvc.perform(get("/books/wishlist/delete").param("wishId", "1"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertEquals(response, str);
	}
	
	
}
