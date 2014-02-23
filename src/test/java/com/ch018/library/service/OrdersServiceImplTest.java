package com.ch018.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
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
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.BookUnavailableException;
import com.ch018.library.exceptions.IncorrectDateException;
import com.ch018.library.util.Constans;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context_for_service.xml", "classpath:root-context_for_service.xml"})
@WebAppConfiguration
public class OrdersServiceImplTest {

		
		private Logger logger = LoggerFactory.getLogger(RateServiceImplTest.class);
		
		private Person person;
		
		private Person person1;
		
		private Book book;
		
		private Book book2;
		
		private Orders order1;
		
		private Date orderDate1;
		
		private Date orderDate2;
		
		private Date returnDate1;
		
		private Date returnDate2;
		
		private int term1;

		private int todayDay;
		
		private int todayMonth;
		
		private int todayYear;
		
		private Calendar today;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		@Autowired
		BookService bookService;
		
		@Autowired
		OrdersService orderService;
		
		@Autowired
		BookInUseService useService;
		
		@Autowired
		private MyUserDetailsService userDetailsService;
		
		
		
		@Before
		public void setup() {
			
			Date now = new Date();
			OrdersServiceImpl serviceImpl = new OrdersServiceImpl();
			now = serviceImpl.correctDate(now);
			
			today = Calendar.getInstance();
			today.setTime(now);
			todayDay = today.get(Calendar.DATE);
			todayMonth = today.get(Calendar.MONTH);
			todayYear = today.get(Calendar.YEAR);
			
			
			
			
			String ordDate1 = "2014-02-23";
			String ordDate2 = "2014-03-01";
			String retDate1 = "2014-03-19";
			String retDate2 = "2014-03-04";
			
			try {
				orderDate1 = format.parse(ordDate1);
				orderDate2 = format.parse(ordDate2);
				returnDate1 = format.parse(retDate1);
				returnDate2 = format.parse(retDate2);
			} catch (Exception e) {
				logger.error("Date parse {}", e);
			}
			
			
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
			
			person1 = new Person();
			person1.setPid(2);
			person1.setName("user2");
			person1.setSurname("user2");
			person1.setEmail("user2@mail.com");
			person1.setPassword("$2a$10$wMLrI4F8SXwEwW4HaLucW.boXEC1.iGpduJrVC7mIr3mUHrjc4n9a");
			person1.setBooksAllowed(10);
			person1.setBooksOnHands(0);
			person1.setCellphone("000-000-0000");
			person1.setFailedOrders(0);
			person1.setGeneralRating(0);
			person1.setMailConfirm(true);
			person1.setConfirmationKey("1111111111");
			person1.setPersonRole("ROLE_USER");
			person1.setSms(false);
			person1.setTimelyReturn(0);
			person1.setUntimekyReturn(0);
			
			book = new Book();
			book.setbId(1);
			book.setTitle("test1");
			book.setShelf(1);
			book.setBookcase(1);
			book.setYear(2000);
			book.setPages(500);
			book.setGeneralQuantity(2);
			book.setCurrentQuantity(1);
			
			book2 = new Book();
			book2.setbId(2);
			book2.setTitle("test2");
			book2.setShelf(1);
			book2.setBookcase(1);
			book2.setYear(2000);
			book2.setPages(500);
			book2.setGeneralQuantity(2);
			book2.setCurrentQuantity(1);

			order1 = new Orders();
			order1.setId(1);
			order1.setPerson(person);
			order1.setBook(book);
			order1.setOrderDate(orderDate1);
			order1.setReturnDate(returnDate1);
			
			term1 = Math.round((order1.getReturnDate().getTime() - order1.getOrderDate().getTime()) / Constans.MILLIS_IN_DAY); 
			
			setDatabase(person, book, order1);	
			
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		
		@After
		public void after() {
			flush();
			SecurityContextHolder.getContext().setAuthentication(null);
		}
	
		@Test
		public void issueTest() throws Exception {
			
			int booksOnHandBefore = getBooksOnHands(person);
			int bookCurrQuanityBefore = getBookCurrentQuantity(book);
			int useBefore = getRowCount();
			
			orderService.issue(order1, term1);
			
			int booksOnHandAfter = getBooksOnHands(person);
			int bookCurrQuanityAfter = getBookCurrentQuantity(book);
			int useAfter = getRowCount();
			
			assertTrue(booksOnHandBefore == booksOnHandAfter - 1 && bookCurrQuanityBefore == bookCurrQuanityAfter + 1
							&& useBefore == useAfter - 1);
			
		}
		
		@Test(expected = Exception.class)
		public void issueOrderNullTest() throws Exception {
			orderService.issue(null, term1);
		}
		
		@Test
		public void getMinOrderDate() throws Exception {

			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			Date date = orderService.getMinOrderDate(book);
			
			Calendar minDate = Calendar.getInstance();
			minDate.setTime(date);
			
			System.out.println(minDate.get(Calendar.DATE) + " " +  minDate.get(Calendar.MONTH) + " "
					+ minDate.get(Calendar.YEAR));
			
			
			assertTrue(minDate.get(Calendar.DATE) == todayDay && minDate.get(Calendar.MONTH) == todayMonth
						&& minDate.get(Calendar.YEAR) == todayYear);
			
			
		}
		
		@Test(expected = BookUnavailableException.class)
		public void getMinOrderDateGeneralZeroTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			book.setGeneralQuantity(0);
			orderService.getMinOrderDate(book);
		}
		
		@Test(expected = BookUnavailableException.class)
		public void getMinOrderDateCurZeroNoInUse() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			book.setCurrentQuantity(0);
			orderService.getMinOrderDate(book);
		}
		
		@Test
		public void getMinOrderDateCurZero() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			book.setCurrentQuantity(0);
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 10);
			
			BooksInUse use = new BooksInUse();
			use.setPerson(person);
			use.setBook(book);
			use.setReturnDate(calendar.getTime());
			
			setBookInUse(use);
			
			Calendar minDate = Calendar.getInstance();
			minDate.setTime(orderService.getMinOrderDate(book));
			
			assertTrue(minDate.get(Calendar.DATE) == calendar.get(Calendar.DATE) && minDate.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
					&& minDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR));
		}
		
		@Test
		public void getMinOrderDateOrdersGeCurr() throws Exception {
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(returnDate1);
			calendar.add(Calendar.DATE, 1);
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person1.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
			
			book.setCurrentQuantity(1);
			
			Calendar minDate = Calendar.getInstance();
			minDate.setTime(orderService.getMinOrderDate(book));
			
			System.out.println(calendar.getTime() + " " + minDate.getTime());
			
			assertTrue(minDate.get(Calendar.DATE) == calendar.get(Calendar.DATE) && minDate.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
					&& minDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR));
			
		}
		
		@Test
		public void getCorrectAmountOfDaysTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person1.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);
		    
		    order1.setOrderDate(format.parse("2014-02-28"));
		    order1.setReturnDate(format.parse("2014-03-15"));
		    

		    updateOrder(order1);
		    
		    
		    book.setCurrentQuantity(1);
		    
		    Date date = format.parse("2014-02-24");
		    
		    int days = orderService.getCorrectAmountOfOrderDays(book, date);
		    
		    assertEquals(4, days);
		    
			
		}
		
		@Test(expected = IncorrectDateException.class)
		public void getCorrectAmountOfDaysIncorrectDateTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person1.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);		    

		    orderService.getCorrectAmountOfOrderDays(book, orderDate1);

		}
		
		@Test
		public void getCorrectAmountOfDaysCurGtOrdersTest() throws Exception {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername (person1.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);		    
		    
		    book.setCurrentQuantity(2);
		    
		    int days = orderService.getCorrectAmountOfOrderDays(book, orderDate1);
		    
		    assertEquals(Constans.MAX_ORDER_DAYS, days);

		}
		
		
		@Test
		public void addOrderTest() throws Exception {
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);	
		   
		    int ordersBefore = getOrdersRowCount();
		    
		    orderService.addOrder(person, book2.getbId(), orderDate1);
		    
		    int ordersAfter = getOrdersRowCount();
		    
		    assertEquals(ordersBefore, ordersAfter - 1); 
		   
		}
		
		@Test(expected = HibernateException.class)
		public void addOrderTwiceTest() throws Exception {
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);	
		    
		    orderService.addOrder(person, book.getbId(), orderDate1);

		   
		}
		
		@Test(expected = IncorrectDateException.class)
		public void addOrderIncorrectDateTest() throws Exception {
			UserDetails userDetails = userDetailsService.loadUserByUsername (person1.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);	
		    
		    orderService.addOrder(person1, book.getbId(), orderDate1);

		   
		}
		
		@Test
		public void editOrderTest() throws Exception {
			UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
			Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		    SecurityContextHolder.getContext().setAuthentication(authToken);	
		    
		    Date date = format.parse("2014-03-17");
		    
		    Calendar newDate = Calendar.getInstance();
		    newDate.setTime(date);
		    
		    orderService.editOrder(person, order1.getId(), date);
		    
		    Orders afterEdit = orderService.getOrderByID(order1.getId());
		    
		    Calendar calendarAfter = Calendar.getInstance();
		    calendarAfter.setTime(afterEdit.getOrderDate());
		    System.out.println(date + " " + afterEdit.getOrderDate());
		    assertTrue(calendarAfter.get(Calendar.DATE) == newDate.get(Calendar.DATE) && calendarAfter.get(Calendar.MONTH) == newDate.get(Calendar.MONTH)
					&& calendarAfter.get(Calendar.YEAR) == newDate.get(Calendar.YEAR));
		}
		
		
		private void setDatabase(Person person, Book book, Orders order) {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("delete from booksinuse");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from orders");
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
				
				preparedStmt = connection.prepareStatement(userCreateQuery);
				preparedStmt.setInt(1, person1.getPid());
				preparedStmt.setInt(2, person1.getBooksAllowed());
				preparedStmt.setInt(3, person1.getBooksOnHands());
				preparedStmt.setString(4, person1.getCellphone());
				preparedStmt.setString(5, person1.getEmail());
				preparedStmt.setInt(6, person1.getFailedOrders());
				preparedStmt.setDouble(7, person1.getGeneralRating());
				preparedStmt.setBoolean(8, person1.isMailConfirm());
				preparedStmt.setString(9, person1.getName());
				preparedStmt.setString(10, person1.getPassword());
				preparedStmt.setString(11, person1.getPersonRole());
				preparedStmt.setBoolean(12, person1.isSms());
				preparedStmt.setString(13, person1.getSurname());
				preparedStmt.setInt(14, person1.getTimelyReturn());
				preparedStmt.setInt(15, person1.getUntimekyReturn());
				preparedStmt.setString(16, person1.getConfirmationKey());
	            
				preparedStmt.execute();
				
				String saveTestBook = "insert into book(title, shelf, bookcase, yearPublic, pages, generalQuantity, rating, votes, bookid, currentQuantity) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				preparedStmt.setInt(10, book.getCurrentQuantity());
				
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
				preparedStmt.setInt(10, book2.getCurrentQuantity());
				
				preparedStmt.execute();
				
				String saveOrder = "INSERT INTO `librarytest2`.`orders` (`id`, `orderDate`, `returnDate`, `bookId`, `personId`) VALUES (?, ?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(saveOrder);
				preparedStmt.setInt(1, order.getId());
				preparedStmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
				preparedStmt.setDate(3, new java.sql.Date(order.getReturnDate().getTime()));
				preparedStmt.setInt(4, order.getBook().getbId());
				preparedStmt.setInt(5, order.getPerson().getPid());
				
				preparedStmt.execute();
				
				
			} catch (Exception e) {
				logger.error("DB wr {}", e);
			}
			
		}
		
		private void flush() {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("delete from booksinuse");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from orders");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from person");
				preparedStmt.execute();
				preparedStmt = connection.prepareStatement("delete from book");
				preparedStmt.execute();
			} catch (Exception e) {
				logger.error("DB flush {}", e.getMessage());
			}
		}
		
		private int getBooksOnHands(Person person) {
			Connection connection;
			PreparedStatement preparedStmt;
			int amount = 0;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				String getBooks = "SELECT booksonhands FROM person WHERE personid = ?";
				preparedStmt = connection.prepareStatement(getBooks);
				preparedStmt.setInt(1, person.getPid());
				
				ResultSet result = preparedStmt.executeQuery();
				
				while(result.next()) {
					amount = result.getInt("booksonhands");
				}
				
				
			} catch (Exception e) {
				logger.error("DB {}", e);
			}
				
			return amount;
		}
		
		private int getBookCurrentQuantity(Book book) {
			Connection connection;
			PreparedStatement preparedStmt;
			int amount = 0;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				String getBooks = "SELECT currentquantity FROM book WHERE bookid = ?";
				preparedStmt = connection.prepareStatement(getBooks);
				preparedStmt.setInt(1, book.getbId());
				
				ResultSet result = preparedStmt.executeQuery();
				
				while(result.next()) {
					amount = result.getInt("currentquantity");
				}
				
				
			} catch (Exception e) {
				logger.error("DB {}", e);
			}
				
			return amount;
		}
		
		private void setBookInUse(BooksInUse use) {
			Connection connection;
			PreparedStatement preparedStmt;
			
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				
				String saveOrder = "INSERT INTO `librarytest2`.`booksinuse` (`id`, `returnDate`, `bookId`, `personId`)  VALUES (?, ?, ?, ?)";
				preparedStmt = connection.prepareStatement(saveOrder);
				preparedStmt.setInt(1, use.getId());
				preparedStmt.setDate(2, new java.sql.Date(use.getReturnDate().getTime()));
				preparedStmt.setInt(3, use.getBook().getbId());
				preparedStmt.setInt(4, use.getPerson().getPid());
				
				preparedStmt.execute();
				
				
			} catch (Exception e) {
				logger.error("DB wr {}", e);
			}
		}
		
		private void updateOrder(Orders order) {
			Connection connection;
			PreparedStatement preparedStmt;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				
				String updateOrder = "UPDATE orders SET `orderDate`= ?, `returnDate`= ? WHERE `id`= ?";
				preparedStmt = connection.prepareStatement(updateOrder);
				preparedStmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
				preparedStmt.setDate(2, new java.sql.Date(order.getReturnDate().getTime()));
				preparedStmt.setInt(3, order.getId());
				System.out.println("STAT " + preparedStmt);
				preparedStmt.execute();
				
				
			} catch (Exception e) {
				logger.error("DB wr {}", e);
			}
		}
		
		private int getRowCount() {
			Connection connection;
			PreparedStatement preparedStmt;
			int count = 0;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("SELECT count(*) as count FROM booksinuse");
				
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next())
					count = rs.getInt("count");	
				
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
			
			return count;
		}
		
		private int getOrdersRowCount() {
			Connection connection;
			PreparedStatement preparedStmt;
			int count = 0;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
				preparedStmt = connection.prepareStatement("SELECT count(*) as count FROM orders");
				
				ResultSet rs = preparedStmt.executeQuery();
				
				while(rs.next())
					count = rs.getInt("count");	
				
			} catch (Exception e) {
				logger.error("DB wr {}", e.getMessage());
			}
			
			return count;
		}
		
}
