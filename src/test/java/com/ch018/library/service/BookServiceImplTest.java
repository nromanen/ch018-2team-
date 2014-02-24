package com.ch018.library.service;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context_for_service.xml", "classpath:root-context_for_service.xml"})
@WebAppConfiguration
public class BookServiceImplTest {

		private final Logger logger = LoggerFactory.getLogger(BookServiceImplTest.class);
		
		@Autowired
		BookService bookService;
		
		@Autowired
		SessionFactory factory;
		
		private Book book;
		
		private Book testBook;
		
		@Before
		public void setup() {
			
			book = new Book();
			book.setbId(1);
			book.setTitle("test1");
			book.setShelf(1);
			book.setBookcase(1);
			book.setYear(2000);
			book.setPages(500);
			book.setGeneralQuantity(2);
			
			testBook = new Book();
			book.setbId(2);
			testBook.setTitle("test2");
			testBook.setShelf(1);
			testBook.setBookcase(1);
			testBook.setYear(2000);
			testBook.setPages(500);
			testBook.setGeneralQuantity(2);
			
			
			setDatabase(book);
			
			
			
		}
		
		@After
		public void after() {
			flush();
		}
		
		@Test
		public void saveTest() throws Exception {
			
			int countBeforeSave = getRowCount();	
			bookService.save(testBook);
			int countAfterSave = getRowCount();	
			assertEquals(countBeforeSave + 1, countAfterSave);
		}
		
		@Test
		public void deleteTest() throws Exception {
			int countBeforeSave = getRowCount();	
			bookService.delete(book);
			int countAfterSave = getRowCount();	
			assertEquals(countBeforeSave - 1, countAfterSave);
		}
		
		@Test
		public void getBookByTitleTest() {
			List<Book> books = new ArrayList<>();
			books = bookService.getBooksByTitle(book.getTitle());
			
			assertEquals(books.size(), 1);
		}
		
		
		@Test
		public void getUnknownBookByTitleTest() {
			List<Book> books = new ArrayList<>();
			books = bookService.getBooksByTitle("sfasaf");
			
			assertEquals(books.size(), 0);
		}
		
		private int getRowCount() {
			Connection connection;
			PreparedStatement preparedStmt;
			int count = 0;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("SELECT count(*) as count FROM book");
				
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next())
					count = rs.getInt("count");	
				
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
			
			return count;
		}
		
		private void setDatabase(Book book) {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("delete from book");
				preparedStmt.execute();
				
				String saveTestBook = "insert into book(title, shelf, bookcase, yearPublic, pages, generalQuantity, bookid) values (?, ?, ?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(saveTestBook);
				preparedStmt.setString(1, book.getTitle());
				preparedStmt.setInt(2, book.getShelf());
				preparedStmt.setInt(3, book.getBookcase());
				preparedStmt.setInt(4, book.getYear());
				preparedStmt.setInt(5, book.getPages());
				preparedStmt.setInt(6, book.getGeneralQuantity());
				preparedStmt.setInt(7, book.getbId());
				
				preparedStmt.execute();
				
				
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
			
		}
	
		
		private void flush() {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("delete from book");
				preparedStmt.execute();
				
				
				
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
			
		}
	
}
