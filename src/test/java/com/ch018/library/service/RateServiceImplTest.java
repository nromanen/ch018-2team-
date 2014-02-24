package com.ch018.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context_for_service.xml", "classpath:root-context_for_service.xml"})
@WebAppConfiguration
public class RateServiceImplTest {

		private Logger logger = LoggerFactory.getLogger(RateServiceImplTest.class);
		
		private Person person;
		
		private Book book;
		
		private Book book2;
		
		private Rate rate;
		
		private Calendar today;
		
		@Autowired
		RateService rateService;
		
		@Autowired
		BookService bookService;
		
		@Autowired
		private MyUserDetailsService userDetailsService;
		
		
		
		@Before
		public void setup() {
			
			today = Calendar.getInstance();
			
			person = new Person();
			person.setPid(1);
			person.setName("user1");
			person.setSurname("user1");
			person.setEmail("user1@mail.com");
			person.setPassword("$2a$10$wMLrI4F8SXwEwW4HaLucW.boXEC1.iGpduJrVC7mIr3mUHrjc4n9a");
			person.setBooksAllowed(10);
			person.setBooksOnHands(0);
			person.setCellphone("000-000-0000");
			person.setFailedOrders(0);
			person.setGeneralRating(0);
			person.setMailConfirm(true);
			person.setConfirmationKey("1111111111");
			person.setPersonRole("ROLE_USER");
			person.setSms(false);
			person.setTimelyReturn(0);
			person.setUntimekyReturn(0);
			
			book = new Book();
			book.setbId(1);
			book.setTitle("test1");
			book.setShelf(1);
			book.setBookcase(1);
			book.setYear(2000);
			book.setPages(500);
			book.setGeneralQuantity(2);
			
			book2 = new Book();
			book2.setbId(2);
			book2.setTitle("test2");
			book2.setShelf(1);
			book2.setBookcase(1);
			book2.setYear(2000);
			book2.setPages(500);
			book2.setGeneralQuantity(2);
			
			rate = new Rate();
			rate.setId(1);
			rate.setPerson(person);
			rate.setBook(book);
			rate.setScore(4F);
			rate.setMessage("Goood!!!");
			rate.setRateDate(today.getTime());
			
			setDatabase(person, book, rate);
			
			
		}
		
		@After
		public void after() {
			flush();
		}
		
		@Test
		public void getRateTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			Rate nrate = rateService.getRate(person, book);
			
			assertEquals(rate.getScore(), nrate.getScore());
			
		}
		
		@Test
		public void addRateTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
		    Rate anotherRate = new Rate();
		    anotherRate.setId(2);
		    anotherRate.setPerson(person);
		    anotherRate.setBook(book2);
		    anotherRate.setScore(3F);
		    
			rateService.addRate(anotherRate, book2.getbId());
			assertTrue(anotherRate.getScore() == getScore(person, book2));
			
		}
		
		@Test(expected = Exception.class)
		public void addRateNullTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
	
			rateService.addRate(null, null);
			
		}
		
		@Test
		public void addRateAlreadyRatedTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
	
		    long beforeCount = rateService.getRatesCount();
		    
		    Rate anotherRate = new Rate();
		    anotherRate.setId(2);
		    anotherRate.setPerson(person);
		    anotherRate.setBook(book2);
		    anotherRate.setScore(3F);
		    
		    rateService.addRate(anotherRate, book2.getbId());
		    
		    long afterCount = rateService.getRatesCount();
		    
		    assertEquals(beforeCount, afterCount - 1);
		    
			
			
		}
		
		
		private float getScore(Person person, Book book) {
			Connection connection;
			PreparedStatement preparedStmt;
			float score = 0F;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				String getScore = "SELECT score FROM rate WHERE personid = ? and bookid = ?";
				preparedStmt = connection.prepareStatement(getScore);
				preparedStmt.setInt(1, person.getPid());
				preparedStmt.setInt(2, book.getbId());
				
				ResultSet result = preparedStmt.executeQuery();
				
				while(result.next()) {
					score = result.getFloat("score");
				}
				
				
			} catch (Exception e) {
				logger.error("DB {}", e);
			}
				
			return score;
		}
		
		
		private void setDatabase(Person person, Book book, Rate rate) {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("delete from rate");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from person");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from book");
				preparedStmt.execute();
				
				String userCreateQuery = "INSERT INTO `librarytest2`.`person` (`personId`, `booksAllowed`, `booksOnHands`, `cellphone`,"
	            		+ " `email`, `failedorders`, `generalratio`, `mailConfirm`, `name`, `password`,"
	            		+ " `personRole`, `sms`, `surname`, `timelyreturn`, `untimelyreturn`, `confirmationKey`)"
	            		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(userCreateQuery);
				preparedStmt.setInt(1, person.getPid());
				preparedStmt.setInt(2, person.getBooksAllowed());
				preparedStmt.setInt(3, person.getBooksOnHands());
				preparedStmt.setString(4, person.getCellphone());
				preparedStmt.setString(5, person.getEmail());
				preparedStmt.setInt(6, person.getFailedOrders());
				preparedStmt.setDouble(7, person.getGeneralRating());
				preparedStmt.setBoolean(8, person.isMailConfirm());
				preparedStmt.setString(9, person.getName());
				preparedStmt.setString(10, person.getPassword());
				preparedStmt.setString(11, person.getPersonRole());
				preparedStmt.setBoolean(12, person.isSms());
				preparedStmt.setString(13, person.getSurname());
				preparedStmt.setInt(14, person.getTimelyReturn());
				preparedStmt.setInt(15, person.getUntimekyReturn());
				preparedStmt.setString(16, person.getConfirmationKey());
	            
				preparedStmt.execute();
				
				String saveTestBook = "insert into book(title, shelf, bookcase, yearPublic, pages, generalQuantity, rating, votes, bookid) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(saveTestBook);
				preparedStmt.setString(1, book.getTitle());
				preparedStmt.setInt(2, book.getShelf());
				preparedStmt.setInt(3, book.getBookcase());
				preparedStmt.setInt(4, book.getYear());
				preparedStmt.setInt(5, book.getPages());
				preparedStmt.setInt(6, book.getGeneralQuantity());
				preparedStmt.setFloat(7, book.getRating());
				preparedStmt.setInt(8, book.getVotes());
				preparedStmt.setInt(9, book.getbId());
				
				preparedStmt.execute();
				
				preparedStmt = connection.prepareStatement(saveTestBook);
				preparedStmt.setString(1, book2.getTitle());
				preparedStmt.setInt(2, book2.getShelf());
				preparedStmt.setInt(3, book2.getBookcase());
				preparedStmt.setInt(4, book2.getYear());
				preparedStmt.setInt(5, book2.getPages());
				preparedStmt.setInt(6, book2.getGeneralQuantity());
				preparedStmt.setFloat(7, book2.getRating());
				preparedStmt.setInt(8, book2.getVotes());
				preparedStmt.setInt(9, book2.getbId());
				
				preparedStmt.execute();
				
				String saveRate = "INSERT INTO rate (`id`, `message`, `rateDate`, `score`, `bookId`, `personId`) VALUES (?, ?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(saveRate);
				preparedStmt.setInt(1, rate.getId());
				preparedStmt.setString(2, rate.getMessage());
				preparedStmt.setDate(3, new java.sql.Date(today.getTime().getTime()));
				preparedStmt.setFloat(4, rate.getScore());
				preparedStmt.setInt(5, rate.getBook().getbId());
				preparedStmt.setInt(6, rate.getPerson().getPid());
				
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
				preparedStmt = connection.prepareStatement("delete from rate");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from person");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from book");
				preparedStmt.execute();
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
		}
}
