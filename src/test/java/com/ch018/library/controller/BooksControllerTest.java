package com.ch018.library.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
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
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context.xml"})
@WebAppConfiguration
public class BooksControllerTest {
	
/*
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private BookInUseService useService;

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	List<Book> books;
	
	Page page;
	
	List<BooksInUse> uses;
	
	Person person;
	
	
	@Before
	public void setup() {
		Mockito.reset(bookService);
		Mockito.reset(personService);
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
		
		person = new Person();
		person.setEmail("email");
		
		BooksInUse use = new BooksInUse();
		use.setPerson(person);
		use.setBook(book1);
		
		uses = Arrays.asList(use);
		
	}*/
	/*
	@Test
	public void booksNormalFlow() throws Exception {
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
	public void booksSearchNormalFlow() throws Exception {
		BookSearch search = new BookSearch();
		when(bookService.getBooksComplex(search)).thenReturn(page);
		
		mockMvc.perform(post("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		verify(bookService, times(1)).getBooksComplex(search);
		verifyNoMoreInteractions(bookService);
	}
	
	@Test
	public void booksSearchEmptyList() throws Exception {
		BookSearch search = new BookSearch();
		Page pageEmpty = new Page();
		pageEmpty.setBooks(new ArrayList<Book>());
		when(bookService.getBooksComplex(search)).thenReturn(pageEmpty);
		mockMvc.perform(post("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attributeExists("nothing"))
		.andExpect(model().attributeExists("query"));
		
		verify(bookService, times(1)).getBooksComplex(search);
		verifyNoMoreInteractions(bookService);
	}
	
	@Test
	public void advancedSearchNormalFlow() throws Exception {
		BookSearch search = new BookSearch();
		search.setTitle("title1");
		page.setBookSearch(search);
		when(bookService.getBooksComplexByParams(search)).thenReturn(page);
		
		mockMvc.perform(post("/books/advancedSearch", "title", "title1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("bookSearch", hasProperty("title", equalTo("title1")))))
		//.andExpect(model().attribute("page", hasSize(2)))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		verify(bookService, times(1)).getBooksComplexByParams(search);
		verifyNoMoreInteractions(bookService);
	}

	@Test
	public void advancedSearchEmptyList() throws Exception {
		BookSearch search = new BookSearch();
		Page page1 = new Page();
		search.setTitle("title1");
		page1.setBooks(new ArrayList<Book>());
		page1.setBookSearch(search);
		when(bookService.getBooksComplexByParams(search)).thenReturn(page1);
		
		mockMvc.perform(post("/books/advancedSearch", "title", "title1"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("bookSearch", hasProperty("title", equalTo("title1")))))
		//.andExpect(model().attribute("page", hasSize(2)))
		.andExpect(model().attributeExists("nothing"));
		
		verify(bookService, times(1)).getBooksComplexByParams(search);
		verifyNoMoreInteractions(bookService);
	}
	
	@Test
	public void autocompleteNormal() throws Exception {
		String query = "title";
		when(bookService.getBooksByTitle(query)).thenReturn(books);
		
		mockMvc.perform(get("/books/autocomplete").param("query", ""))
			.andExpect(status().isOk());
			//.andDo(print())
			//.andExpect(jsonPath("$", hasSize(2)))
			//.andExpect(jsonPath("$[0].suggestions", is("book1")))
			//.andExpect(jsonPath("$[1].suggestions", is("book2")));
		
	}
	
	
	@Ignore
	@Test
	public void myBooksNormal() throws Exception {
		Principal principal = new Principal() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		when(personService.getByEmail(principal.getName())).thenReturn(person);
		when(useService.getBooksInUseByPerson(person)).thenReturn(uses);
	
		mockMvc.perform(get("books/mybooks"))
			
			//.andExpect(status().isOk())
			.andExpect(model().attributeExists("uses"))
			.andExpect(model().attribute("uses", hasProperty("person", hasProperty("email", equalTo("email")))));
	}*/

}
