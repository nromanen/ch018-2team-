package com.ch018.library.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context.xml"})
@WebAppConfiguration
public class BooksControllerTest {
	

	@Autowired
	private BookService bookService;

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	List<Book> books;
	
	@Before
	public void setup() {
		
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		Book book1 = new Book();
		Book book2 = new Book();
		
		book1.setbId(1);
		book1.setTitle("book1");
		
		book2.setbId(2);
		book2.setTitle("book2");

		books = Arrays.asList(book1, book2);
	}
	
	@Test
	public void testBooks() throws Exception {

		when(bookService.getAll()).thenReturn(books);
		
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("books", hasSize(2)))
			.andExpect(model().attribute("books", hasItem(
					allOf(
							hasProperty("bId", is(1)),
							hasProperty("title", is("book1"))
							))))
			.andExpect(model().attribute("books", hasItem(
					allOf(
							hasProperty("bId", is(2)),
							hasProperty("title", is("book2"))
							))))
			.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	}
	


}
