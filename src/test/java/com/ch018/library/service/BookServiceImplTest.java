package com.ch018.library.service;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
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
		book.setTitle("test1");
		book.setShelf(1);
		book.setBookcase(1);
		book.setYear(2000);
		book.setPages(500);
		book.setGeneralQuantity(2);
		
		testBook = new Book();
		testBook.setTitle("test2");
		testBook.setShelf(1);
		testBook.setBookcase(1);
		testBook.setYear(2000);
		testBook.setPages(500);
		testBook.setGeneralQuantity(2);
		
		Connection connection;
		Statement stmt;
		PreparedStatement preparedStmt;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
			stmt = connection.createStatement();
			stmt.execute("delete from book");
			logger.info("Delete All query executed");
			String saveTestBook = "insert into book(title, shelf, bookcase, yearPublic, pages, generalQuantity) values (?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setString(1, "test");
			preparedStmt.setInt(2, 1);
			preparedStmt.setInt(3, 1);
			preparedStmt.setInt(4, 2000);
			preparedStmt.setInt(5, 500);
			preparedStmt.setInt(6, 2);
			
			preparedStmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	@Test
	public void saveTest() throws Exception {
		
		long countBeforeSave = bookService.getBooksCount();	
		bookService.save(book);
		long countAfterSave = bookService.getBooksCount();	
		assertEquals(countBeforeSave + 1, countAfterSave);
	}
	
	@Test
	public void deleteTest() throws Exception {
		long countBeforeSave = bookService.getBooksCount();	
		List<Book> books = bookService.getBooksByTitle("test");
		for(Book book : books)
			bookService.delete(book);
		long countAfterSave = bookService.getBooksCount();	
		assertEquals(countBeforeSave - 1, countAfterSave);
	}
	
	@Test
	public void getBookByTitleTest() {
		List<Book> books = new ArrayList<>();
		books = bookService.getBooksByTitle("test");
		
		assertEquals(books.size(), 1);
	}
	
	
	@Test
	public void getUnknownBookByTitleTest() {
		List<Book> books = new ArrayList<>();
		books = bookService.getBooksByTitle("sfasaf");
		
		assertEquals(books.size(), 0);
	}
	
	
	/*void delete(Book book);
             void update(Book book);
             List<Book> getAll();
             Book getBookById(int id);
             List<Book> getBooksByTitle(String title);
             List<Book> getBooksByAuthors(String authors);
             List<Book> getBooksByYear(int year);
             List<Book> getBooksByGenre(Genre genre);
             List<Book> getBooksByPublisher(String publisher);
             List<Book> getBooksByPagesEq(int pages);
             List<Book> advancedSearch(Book book);
             List<Book> simpleSearch(String query);
             Map<BooksInUse, Integer> getHolders(Book book);
             Integer getMinIntegerField(String field);
             Integer getMaxIntegerField(String field);
             List<Book> getLastByField(String field, int quantity);
             List<Book> getRecommended(int quantity);
             long getBooksCount();*/
	
	
}
