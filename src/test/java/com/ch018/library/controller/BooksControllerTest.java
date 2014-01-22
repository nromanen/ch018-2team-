package com.ch018.library.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
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
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
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
	Page page;
	
	@Before
	public void setup() {
		Mockito.reset(bookService);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		Book book1 = new Book();
		Book book2 = new Book();
		
		book1.setbId(1);
		book1.setTitle("book1");
		
		book2.setbId(2);
		book2.setTitle("book2");

		books = Arrays.asList(book1, book2);
		
		page = new Page();
		page.setBooks(books);
		
	}
	
	@Ignore
	@Test
	public void BooksNormalFlow() throws Exception {
		BookSearch search = new BookSearch();
		when(bookService.getBooksComplex(search)).thenReturn(page);
		
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("page"))
			.andExpect(model().attribute("page", hasProperty("books")))
			.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
		
		verify(bookService, times(1)).getBooksComplex(search);
		verifyNoMoreInteractions(bookService);
		
	}
	
	@Test
	public void BooksNullSearch() throws Exception {
		BookSearch search = new BookSearch();
		when(bookService.getBooksComplex(search)).thenThrow(new Exception());
		mockMvc.perform(get("/books"))
			.andExpect(view().name("error"));
	}
	

	

}
