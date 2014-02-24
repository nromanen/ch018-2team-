package com.ch018.library.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PaginationService;
import com.ch018.library.service.PersonService;
import com.ch018.library.util.Constans;
import com.ch018.library.util.PageContainer;
import com.ch018.library.util.SearchParamsBook;
import com.ch018.library.util.Switch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context.xml"})
@WebAppConfiguration
public class BooksControllerTest {
	

	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private BookInUseService useService;
	
	@Autowired
    private SearchParamsBook searchParams;  
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private Switch switcher;
	
	@Autowired
	private PaginationService<Book> paginationService;
	
	@Autowired
	private PageContainer<Book> pageContainer;
	

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	List<Book> books;
	List<Book> booksAutocomplete;
	
	SearchParamsBook tmpParams;
	
	List<BooksInUse> uses;
	
	Person person;
	
	
	@Before
	public void setup() {
		Mockito.reset(searchParams);
		Mockito.reset(bookService);
		Mockito.reset(genreService);
		Mockito.reset(switcher);
		Mockito.reset(useService);
		Mockito.reset(genreService);
		Mockito.reset(paginationService);
		Mockito.reset(pageContainer);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		Book book1 = new Book();
		Book book2 = new Book();
		
		book1.setbId(1);
		book1.setTitle("book1");
		
		book2.setbId(2);
		book2.setTitle("book2");

		booksAutocomplete = Arrays.asList(book1, book2);
		
		person = new Person();
		person.setEmail("email");
		
		BooksInUse use = new BooksInUse();
		use.setPerson(person);
		use.setBook(book1);
		
		uses = Arrays.asList(use);
		
		books = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			books.add(new Book());
		
		tmpParams = new SearchParamsBook();
	}
	
	@Test
	public void booksHomePage() throws Exception {
		
		when(bookService.getLastByField("arrivalDate", Constans.AMOUNT_OF_BOOKS_TO_MAIN)).thenReturn(books);
		
		when(bookService.getRecommended(Constans.AMOUNT_OF_BOOKS_TO_MAIN)).thenReturn(books);
		
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("arrivals"))
			.andExpect(model().attributeExists("populars"))
			.andExpect(model().attribute("arrivals", hasSize(Constans.AMOUNT_OF_BOOKS_TO_MAIN)))
			.andExpect(model().attribute("populars", hasSize(Constans.AMOUNT_OF_BOOKS_TO_MAIN)))
			.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
		
		verify(bookService, times(1)).getLastByField("arrivalDate", Constans.AMOUNT_OF_BOOKS_TO_MAIN);
		verify(bookService, times(1)).getRecommended(Constans.AMOUNT_OF_BOOKS_TO_MAIN);
		
		verifyNoMoreInteractions(bookService);		
	}
	
	@Test
	public void bookSearchGetNormalFlow() throws Exception {
		
		when(searchParams.isMainFieldsEmpty()).thenReturn(false);
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(paginationService.getPaginatedResult(searchParams, tmpParams, Book.class))
						.thenReturn(books);
		
		mockMvc.perform(get("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attribute("books", hasSize(books.size())))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
	verify(searchParams, times(1)).isMainFieldsEmpty();
	verify(searchParams, times(1)).isSlidersNull();
	verify(paginationService, times(1)).getPaginatedResult(searchParams, tmpParams, Book.class);
	
	verifyNoMoreInteractions(searchParams);
	verifyNoMoreInteractions(paginationService);
		
	}
	
	@Test
	public void bookSearchGetEmptyArray() throws Exception {
		
		when(searchParams.isMainFieldsEmpty()).thenReturn(false);
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(paginationService.getPaginatedResult(searchParams, tmpParams, Book.class))
						.thenReturn(new ArrayList<Book>());
		
		mockMvc.perform(get("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attributeExists("nothing"))
		.andExpect(model().attribute("books", hasSize(0)))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
	verify(searchParams, times(1)).isMainFieldsEmpty();
	verify(searchParams, times(1)).isSlidersNull();
	verify(paginationService, times(1)).getPaginatedResult(searchParams, tmpParams, Book.class);
	
	verifyNoMoreInteractions(searchParams);
	verifyNoMoreInteractions(paginationService);
		
	}
	
	@Test
	public void bookSearchGetWithParams() throws Exception {
		tmpParams.setPage(1);
		tmpParams.setOrderField("authors");
		when(searchParams.isMainFieldsEmpty()).thenReturn(false);
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(paginationService.getPaginatedResult(searchParams, tmpParams, Book.class))
						.thenReturn(books);
		
		mockMvc.perform(get("/books/search").param("page", "1").param("orderField", "authors"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attribute("books", hasSize(books.size())))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
	verify(searchParams, times(1)).isMainFieldsEmpty();
	verify(searchParams, times(1)).isSlidersNull();
	verify(paginationService, times(1)).getPaginatedResult(searchParams, tmpParams, Book.class);
	
	verifyNoMoreInteractions(searchParams);
	verifyNoMoreInteractions(paginationService);
		
	}
	
	@Test
	public void bookSearchPostSwitcherFalse() throws Exception {
		tmpParams.setTitle("java");
		when(switcher.getSwitcher()).thenReturn(false);
		when(paginationService.getPaginatedResult(searchParams, Book.class))
						.thenReturn(books);
		
		mockMvc.perform(post("/books/search").param("title", "java"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attribute("books", hasSize(books.size())))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
	verify(switcher, times(1)).getSwitcher();
	verify(paginationService, times(1)).getPaginatedResult(searchParams, Book.class);
	
	verifyNoMoreInteractions(switcher);
	verifyNoMoreInteractions(paginationService);
		
	}
	
	@Test
	public void bookSearchPostSwitcherTrue() throws Exception {
		tmpParams.setTitle("java");
		when(switcher.getSwitcher()).thenReturn(true);
		when(paginationService.getPaginatedResult(searchParams, Book.class))
						.thenReturn(books);
		
		mockMvc.perform(post("/books/search").param("title", "java"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attribute("books", hasSize(books.size())))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
	verify(switcher, times(1)).getSwitcher();
	verify(pageContainer, times(1)).setItems(books);
	verify(paginationService, times(1)).getPaginatedResult(searchParams, Book.class);
	
	verifyNoMoreInteractions(switcher);
	verifyNoMoreInteractions(pageContainer);
	verifyNoMoreInteractions(paginationService);
		
	}
	
	
	@Test
	public void autocompleteNormal() throws Exception {
		String query = "book";
		
		String response = "{\"suggestions\":[\"book1\",\"book2\"]}";
		when(bookService.getBooksByTitle(query)).thenReturn(booksAutocomplete);
		
		String str = mockMvc.perform(get("/books/autocomplete").param("query", query))
							.andExpect(status().isOk())
							.andReturn().getResponse().getContentAsString();
	

		assertEquals(response, str);
		
	}
	
	@Test
	public void autocompleteEmptyQuery() throws Exception {
		String query = "";
		List<Book> booksEmpty = new ArrayList<>();
		String response = "{\"suggestions\":[]}";
		when(bookService.getBooksByTitle(query)).thenReturn(booksEmpty);
		
		String str = mockMvc.perform(get("/books/autocomplete").param("query", query))
							.andExpect(status().isOk())
							.andReturn().getResponse().getContentAsString();
	

		assertEquals(response, str);
		
	}
	
	@Ignore
	@Test
	public void myBooksNormal() throws Exception {
		when(useService.getBooksInUseByPerson(person)).thenReturn(uses);
		
		mockMvc.perform(get("/books/mybooks"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("uses"))
		.andExpect(model().attribute("uses", hasSize(1)))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
		verify(useService, times(1)).getBooksInUseByPerson(person);
		
		verifyNoMoreInteractions(useService);
		
	}
	
	@Ignore
	@Test
	public void myBooksNormalWithGarbageParams() throws Exception {
		when(useService.getBooksInUseByPerson(person)).thenReturn(uses);
		
		mockMvc.perform(get("/books/mybooks").param("aaa", "123"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("uses"))
		.andExpect(model().attribute("uses", hasSize(1)))
		.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
	
		verify(useService, times(1)).getBooksInUseByPerson(person);
		
		verifyNoMoreInteractions(useService);
		
	}

}
