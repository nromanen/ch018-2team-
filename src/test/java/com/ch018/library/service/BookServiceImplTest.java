package com.ch018.library.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context_Dao_Service.xml"})
@WebAppConfiguration
public class BookServiceImplTest {

	@Autowired
	BookDao bookService;
	
	@Test
	public void saveTest() throws Exception {
		
		Book book = new Book();
		book.setTitle("title");
		System.out.println(bookService);
		bookService.save(book);
		
		Book resp = bookService.getBookById(1);
		
		assertEquals(book.getTitle(), resp.getTitle());
	}
	
	
}
