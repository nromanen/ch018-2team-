package com.ch018.library.controller;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
							"file:src/main/webapp/WEB-INF/spring/appServlet/controllers.xml",
							"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BooksControllerTest {
	

	
	@Autowired
    BookService bookService;
	
	@Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
	@Test
	public void booksGetTest() throws Exception {

		Book book = new Book();
		book.setbId(1);
		book.setTitle("book1");
		
		when(Mockito.mock(BookService.class).getAll()).thenReturn(Arrays.asList(book));
		
		
		
		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(view().name("books"))
			.andExpect(model().attribute("books", hasSize(1)));

	}

}
