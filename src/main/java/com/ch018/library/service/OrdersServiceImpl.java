package com.ch018.library.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.DAO.BooksInUseDao;
import com.ch018.library.DAO.OrdersDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.BookUnavailableException;
import com.ch018.library.exceptions.IncorrectDateException;
import com.ch018.library.exceptions.TooManyOrdersException;

/**
 * 
 * @author Edd Arazain
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	
		private static final int MAX_DAYS = 14;
		private static final long MILLIS_IN_DAY = 24 * 3600 * 1000;
		private static final long DIFF_TIME_IN_MILLIS = 3600 * 1000;
		private static final int ADDITIONAL_DAY_IF_SAT = 2;
		private static final int MAX_TRIES_FOR_ORDER_FINDING = 12;
		
		private final Logger logger = LoggerFactory
				.getLogger(OrdersServiceImpl.class);
	
		@Autowired
		private OrdersDao ordersDao;
	
		@Autowired
		private WishListService wishService;
	
		@Autowired
		private BooksInUseDao useDao;
	
		@Autowired
		private MailService mailService;
	
		@Autowired
		private PersonService personService;
	
		@Autowired
		private BookService bookService;
	
		@Autowired
		private BookInUseService booksInUseService;
	
		@Autowired
		private BookDao bookDao;
	
		@Autowired
		private PersonDao personDao;
		
		@Autowired
		private SmsService smsService;
	
		@Override
		@Transactional
		public void save(Orders order) {
			ordersDao.save(order);
			Book book = order.getBook();
			int ordersQuantity = book.getOrdersQuantity() + 1;
			book.setOrdersQuantity(ordersQuantity);
			bookDao.update(book);
			if (wishService.isPersonWishBook(order.getPerson(), order.getBook()))
				wishService.delete(wishService.getWishByPersonBook(
						order.getPerson(), order.getBook()));
		}
	
		@Override
		@Transactional
		public void delete(Orders order) {
			ordersDao.delete(order);
		}
	
		@Override
		@Transactional
		public void update(int id, Date newDate) {
			ordersDao.update(id, newDate);
		}
	
		@Override
		@Transactional
		public List<Orders> getAll() {
			return ordersDao.getAll();
		}
	
		@Override
		@Transactional
		public List<Orders> getOrderByPerson(Person person) {
			return ordersDao.getOrderByPerson(person);
		}
	
		@Override
		@Transactional
		public List<Orders> getOrderByBook(Book book) {
			return ordersDao.getOrderByBook(book);
		}
	
		@Override
		@Transactional
		public List<Orders> getOrderByDate(Date date) {
			return ordersDao.getOrderByDate(date);
		}
	
		@Override
		@Transactional
		public Orders getOrderByID(int id) {
			return ordersDao.getOrderByID(id);
		}
	
		@Override
		@Transactional
		public List<Orders> getOrdersToday() {
			return ordersDao.getOrdersToday();
		}
	
		@Override
		@Transactional
		public List<Orders> getOrdersInHour() {
			return ordersDao.getOrdersInHour();
		}
	
		@Override
		@Transactional
		public int getBookIdByPerson(Person person) {
			return ordersDao.getBookIdByPerson(person);
		}
	
		@Override
		@Transactional
		public boolean isPersonOrderedBook(Person person, Book book) {
			return ordersDao.isPersonOrderedBook(person, book);
		}
	
		@Override
		@Transactional
		public boolean isLimitReached(Person person) {
			int ordersCount = ordersDao.getOrderByPerson(person).size();
			int useCount = useDao.getBooksInUseByPerson(person).size();
	
			if (person.getBooksAllowed() > ordersCount + useCount)
				return false;
			else
				return true;
		}
	
	
		@Override
		@Transactional
		public void issue(Orders order, int term) {
			Person person = order.getPerson();
	
			int booksOnHands = person.getMultiBook();
	
			person.setMultiBook(++booksOnHands);
	
			personService.update(person);
	
			Book book = order.getBook();
	
			int currentQuantity = book.getCurrentQuantity();
	
			currentQuantity -= 1;
	
			book.setCurrentQuantity(currentQuantity);
	
			bookService.update(book);
	
			BooksInUse bookInUse = new BooksInUse();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, term);
			Date date = calendar.getTime();
	
			bookInUse.setBook(order.getBook());
			bookInUse.setPerson(order.getPerson());
			bookInUse.setReturnDate(date);
			booksInUseService.save(bookInUse);
			mailService.sendEmailBookIssued("springytest@gmail.com",
					"etenzor@gmail.com", "Book Orders", order, person,
					book, bookInUse, term);
		}
	
		/**
		 * Return minimal order date for specific book.
		 * Minimal date depends on book current  quantity and orders
		 * quantity. Method compare that values, based on that
		 * compare we get temporal min date. If current quantity
		 * grater 0 and orders amount, method return current date.
		 * Otherwise method makes call method which return minimal
		 * return date of book. Based on that date we get orders
		 * for period which contain, half of previous month, current
		 * month and half of next month, In while loop which
		 * limited with MAX_TRIES_FOR_ORDER_FINDING, method calculate days
		 * amount based on min order date and order date, if days amount > 1
		 * it return min date, otherwise, it calculate new min date by adding
		 * amount of orders date to order date, then repeat previous step with
		 * new min date. 
		 * 
		 * @param Book book - book object for getting orders
		 * 
		 * @throws BookUnavailableException if general quantity of book <= 0
		 * 
		 * @throws TooManyOrdersException if loop end without finding min date
		 * 
		 */
		@Override
		@Transactional
		public Date getMinOrderDate(Book book) throws Exception {
	
			int currentQuantity = book.getCurrentQuantity();
			int generalQuantity = book.getGeneralQuantity();
			
			long days = MAX_DAYS;
			Date minDate = correctDate(new Date());
			Calendar calendar = Calendar.getInstance();
			
			List<Orders> orders = null;
			long ordersAmount = ordersDao.getOrdersCount(book);
			
			if(generalQuantity <= 0)
				throw new BookUnavailableException();
			
			if (currentQuantity > 0 && ordersAmount < currentQuantity) {
				return minDate;
			} 
			else if (currentQuantity <= 0) {
				minDate = booksInUseService.getMinReturnDate(book);
			}
			int loopCount = 0;
			calendar.setTime(minDate);
			
			while(loopCount++ < MAX_TRIES_FOR_ORDER_FINDING) {
				orders = getOrdersForPeriodFromMonth(book, calendar.getTime());
				if(orders.isEmpty()) {
					return minDate;
				}
				for (Orders order : orders) {
					days = (order.getOrderDate().getTime() - minDate.getTime())
							/ MILLIS_IN_DAY;
					if (days >= 1) 
						return minDate;
					else {
						minDate = new Date(order.getOrderDate().getTime()
								+ order.getDaysAmount() * MILLIS_IN_DAY);
					}
				}
			calendar.add(Calendar.MONTH, 2);
			}
			
			throw new TooManyOrdersException();
	
		}
	
		@Override
		@Transactional
		public int getCorrectAmountOfOrderDays(Book book, Date orderDate)
													throws Exception {
			if (orderDate.getTime() < (new Date().getTime() + DIFF_TIME_IN_MILLIS))
				throw new IncorrectDateException();
			List<Orders> orders = getOrdersForPeriodFromMonth(book, orderDate);

			int days = MAX_DAYS;
			long currentOrderDateInMillis = orderDate.getTime();

			for (Orders order : orders) {
				long orderDateInMillis = order.getOrderDate().getTime();
				
				if (currentOrderDateInMillis < orderDateInMillis ) {

					Double daysRes = (double) (orderDateInMillis - currentOrderDateInMillis)
							/ MILLIS_IN_DAY;
					days = (int) Math.round(daysRes);
					if (days <= 0)
						throw new IncorrectDateException();
					else if (days > MAX_DAYS) 
						days = MAX_DAYS;
					break;
				} else if(currentOrderDateInMillis > orderDateInMillis
							&& (currentOrderDateInMillis < orderDateInMillis + order.getDaysAmount()*MILLIS_IN_DAY))
					throw new IncorrectDateException();
			}
			return checkForWeekend(orderDate, days);
		}
	
		@Override
		@Transactional
		public void update(Orders order) {
			ordersDao.update(order);
		}
	
		@Override
		@Transactional
		public void addOrder(Person person, int bookId, Date orderDate)
				throws Exception {
			Book book = bookService.getBookById(bookId);
			orderDate = correctDate(orderDate);
			int days;
			try {
				days = getCorrectAmountOfOrderDays(book, orderDate);
			} catch (IncorrectDateException e) {
				throw new IncorrectDateException();
			} catch (Exception e) {
				throw new Exception();
			}
			Orders order = new Orders(person, book, orderDate, days);
			try {
				save(order);
			} catch (ConstraintViolationException e) {
				throw new HibernateException("You already ordered this book");
			}
			logger.info("person {} order book {} to date {} for {} days", person,
					book, orderDate, days);
			mailService.sendMailWithOrder("springytest@gmail.com",
					"etenzor@gmail.com", "order", order);
			if(person.isSms())
				smsService.sendSms("new order for: " + order.getBook().getTitle());
		}
	
		@Override
		@Transactional
		public Orders editOrder(Person person, int orderId, Date orderDate)
				throws Exception {
	
			Orders order = getOrderByID(orderId);
			Book book = order.getBook();
			orderDate = correctDate(orderDate);
			int orderDays;
			try {
				orderDays = getCorrectAmountOfOrderDays(book, orderDate);
			} catch (IncorrectDateException e) {
				throw new IncorrectDateException();
			} catch (Exception e) {
				throw new Exception();
			}
			order.setOrderDate(orderDate);
			order.setDaysAmount(orderDays);
			order.setChanged(false);
			update(order);
			logger.info("person {} edit order for book {} to date {} for {}  days",
					person, book, orderDate, orderDays);
			return order;
	
		}
		
		private Date correctDate(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			logger.info("shifted date before = {}", calendar.getTime());
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				calendar.add(Calendar.DATE, 2);
			else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				calendar.add(Calendar.DATE, 1);
			if(calendar.get(Calendar.HOUR_OF_DAY) > 16 || calendar.get(Calendar.HOUR_OF_DAY) < 8)
				calendar.set(Calendar.HOUR_OF_DAY, 11);
			logger.info("shifted date = {}", calendar.getTime());
			return calendar.getTime();
		}
	
		private int checkForWeekend(Date orderDate, int days) {
	
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(orderDate);
			calendar.add(Calendar.DAY_OF_YEAR, days);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				days += ADDITIONAL_DAY_IF_SAT;
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				days++;
			}
			return days;
		}
	
		@Override
		@Transactional
		public List<Orders> getOrdersByPersonSurname(List<Orders> orders,
				String surname) {
			List<Person> persons = new ArrayList<Person>();
			List<Orders> answerList = new ArrayList<Orders>();
			persons = personDao.getPersonsBySurname(surname);
			for (Person ps : persons)
				for (Orders or : orders) {
					if (or.getPerson().getPid() == ps.getPid())
						answerList.add(or);
				}
			return answerList;
		}
	
		@Override
		@Transactional
		public List<Orders> getOrdersByBookTitle(List<Orders> orders, String title) {
	
			List<Orders> answerList = new ArrayList<Orders>();
			List<Book> books = new ArrayList<Book>();
			books = bookDao.getBooksByTitle(title);
			for (Book book : books) 
				for (Orders or : orders) {
					if (or.getBook().getbId() == book.getbId())
						answerList.add(or);
				}
			return answerList;
		}
	
		@Override
		@Transactional
		public List<Orders> getOrdersByOrdersId(List<Orders> orders, int id) {
			List<Orders> answerList = new ArrayList<Orders>();
			for (Orders or : orders)
				if (or.getId() == id)
					answerList.add(or);
			return answerList;
		}

		@Override
		@Transactional
		public List<Orders> getOrdersForPeriodFromMonth(Book book, Date date) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Person person = personService.getByEmail(email);
			Calendar current = Calendar.getInstance();
			Calendar choosenMonth = Calendar.getInstance();
			Calendar previousMonth = Calendar.getInstance();
			Calendar nextMonth = Calendar.getInstance();
			
			choosenMonth.setTime(date);
			choosenMonth.set(Calendar.DATE, 15);
			previousMonth = (Calendar) choosenMonth.clone();
			previousMonth.add(Calendar.MONTH, -1);


			nextMonth = (Calendar) choosenMonth.clone();
			nextMonth.add(Calendar.MONTH, 1);
			
			Date firstDate = previousMonth.getTime();
			Date secondDate = nextMonth.getTime();
			logger.info("first date {} second date {}", firstDate, secondDate);
			return ordersDao.getOrdersBetweenDatesWithoutPerson(person, book, firstDate, secondDate);
			
		}

		
		
		
}