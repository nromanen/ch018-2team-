package com.ch018.library.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
