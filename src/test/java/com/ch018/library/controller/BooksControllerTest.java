package com.ch018.library.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.SearchParams;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

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
    private SearchParams searchParams;    

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	List<Book> books;
	
	
	List<BooksInUse> uses;
	
	Person person;
	
	/*
	@Before
	public void setup() {
		Mockito.reset(searchParams);
		Mockito.reset(pageContainer);
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
		
		person = new Person();
		person.setEmail("email");
		
		BooksInUse use = new BooksInUse();
		use.setPerson(person);
		use.setBook(book1);
		
		uses = Arrays.asList(use);
		
	}
	
	@Test
	public void booksNormalFlow() throws Exception {
		
		
		when(bookService.getLastByField("arrivalDate", 4)).thenReturn(books);
		
		when(bookService.getLastByField("ordersQuantity", 4)).thenReturn(books);
		
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("arrivals"))
			.andExpect(model().attributeExists("populars"))
			.andExpect(forwardedUrl("/WEB-INF/templates/base-template.jsp"));
		
		
		
		
	}
	
	@Test
	public void booksSearchPostNormalFlow() throws Exception {
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(post("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		verify(pageContainer, times(1)).getPage(searchParams);
		
	}
	
	
	
	@Test
	public void booksSearchPostEmptyList() throws Exception {
		Page pageEmpty = new Page();
		pageEmpty.setBooks(new ArrayList<Book>());
		
		when(pageContainer.getPage(searchParams)).thenReturn(pageEmpty);
		mockMvc.perform(post("/books/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attributeExists("nothing"));
		
		verify(pageContainer, times(1)).getPage(searchParams);

	}
	
	@Test
	public void booksSearchGetPageChangedNormalFlow() throws Exception {
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(searchParams.getPage()).thenReturn(1);
		when(searchParams.getOrderField()).thenReturn("title");
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(get("/books/search").param("page", "2"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		
		verify(pageContainer, times(1)).getPage(searchParams);
		verifyNoMoreInteractions(pageContainer);
		
	}
	
	@Test
	public void booksSearchGetPageNotChangedNormalFlow() throws Exception {
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(searchParams.getPage()).thenReturn(1);
		when(searchParams.getOrderField()).thenReturn("title");
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(get("/books/search").param("page", "1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		
		verify(pageContainer, times(1)).getPage(searchParams);
		verifyNoMoreInteractions(pageContainer);
		
	}
	
	@Test
	public void booksSearchGetPageNotChangedOrderChangedNormalFlow() throws Exception {
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(searchParams.getPage()).thenReturn(1);
		when(searchParams.getOrderField()).thenReturn("title");
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(get("/books/search").param("page", "1").param("orderField", "authors"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		
		verify(pageContainer, times(1)).getPage(searchParams);
		verify(pageContainer, times(1)).recalculate(searchParams);;
		verifyNoMoreInteractions(pageContainer);
		
	}
	
	@Test
	public void booksSearchGetPageandOrderChangedNormalFlow() throws Exception {
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(searchParams.getPage()).thenReturn(1);
		when(searchParams.getOrderField()).thenReturn("title");
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(get("/books/search").param("page", "3").param("orderField", "authors"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		
		verify(pageContainer, times(1)).getPage(searchParams);
		verify(pageContainer, times(1)).recalculate(searchParams);;
		verifyNoMoreInteractions(pageContainer);
		
	}
	
	@Test
	public void booksSearchGetOrderChangedNormalFlow() throws Exception {
		when(searchParams.isSlidersNull()).thenReturn(false);
		when(searchParams.getPage()).thenReturn(1);
		when(searchParams.getOrderField()).thenReturn("title");
		when(pageContainer.getPage(searchParams)).thenReturn(page);
		
		mockMvc.perform(get("/books/search").param("page", "1").param("order", "true"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attribute("page", hasProperty("books")))
		.andExpect(model().attributeDoesNotExist("nothing"))
		.andExpect(model().attributeDoesNotExist("query"));
		
		
		verify(pageContainer, times(1)).getPage(searchParams);
		verify(pageContainer, times(1)).recalculate(searchParams);;
		verifyNoMoreInteractions(pageContainer);
		
	}
	
	@Test
	public void autocompleteNormal() throws Exception {
		String query = "book";
		
		String response = "{\"suggestions\":[\"book1\",\"book2\"]}";
		when(bookService.getBooksByTitle(query)).thenReturn(books);
		
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
	*/

}
