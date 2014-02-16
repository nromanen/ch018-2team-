package com.ch018.library.controller;

import java.security.Principal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookService;
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
	
	@Ignore
	@Test
	public void addOrder() throws Exception {
		when(personService.getByEmail("email")).thenReturn(person);
		when(bookService.getBookById(1)).thenReturn(book);
		WishList wishList = new WishList(person, book);
		wishService.save(wishList);
		
		
		MockHttpServletRequestBuilder postRequest = post("/books/wishlist/add");
		
		mockMvc.perform(postRequest)
			.andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title", is("book")));
	}
	
}
