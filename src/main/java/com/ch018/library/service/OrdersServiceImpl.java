package com.ch018.library.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.ch018.library.helper.OrderDays;

/**
 * 
 * @author Edd Arazain
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	
		private static final int MAX_DAYS = 14;
		private static final long MILLIS_IN_DAY = 24 * 3600 * 1000;
		private static final long DIFF_TIME_IN_MILLIS = 60 * 60 * 1000;
		private static final int ADDITIONAL_DAY_IF_SAT = 2;
	
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
	
			person.setMultiBook(--booksOnHands);
	
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
			mailService.sendEmailBookIssued("librairancv111@gmail.com",
					"dmitry.sankovsky@gmail.com", "Book Orders", order, person,
					book, bookInUse, term);
		}
	
		public boolean isAvailable() {
	
			return false;
		}
	
		@Override
		@Transactional
		public OrderDays getMinOrderDate(Book book) {
	
			int currentQuantity = book.getCurrentQuantity();
			int generalQuantity = book.getGeneralQuantity();
			long days = MAX_DAYS;
			Date minDate = new Date();
			Calendar calendar = Calendar.getInstance();
			List<Orders> orders = ordersDao.getOrderByBook(book);
			orders = excludeSelfOrders(orders);
			
			if(generalQuantity <= 0)
				return new OrderDays(new Date(), 0);
			
			if (currentQuantity > 0 && orders.size() < currentQuantity) {
				calendar.setTime(minDate);
	
				return new OrderDays(calendar.getTime(), MAX_DAYS);
			} 
			else if (currentQuantity <= 0 || orders.size() >= currentQuantity) {
				minDate = booksInUseService.getMinReturnDate(book);
			}
	
			for (Orders order : orders) {
				days = (order.getOrderDate().getTime() - minDate.getTime())
						/ MILLIS_IN_DAY;
				if (days > 1)
					break;
				else {
					minDate = new Date(order.getOrderDate().getTime()
							+ order.getDaysAmount() * MILLIS_IN_DAY);
				}
			}
			calendar.setTime(minDate);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			return new OrderDays(minDate, days, orders);
	
		}
	
		@Override
		@Transactional
		public int getCorrectAmountOfOrderDays(Book book, Date orderDate)
				throws Exception {
			if (orderDate.getTime() < (new Date().getTime() - DIFF_TIME_IN_MILLIS))
				throw new Exception("Incorrect Date Choosen");
			List<Orders> orders = ordersDao.getOrderByBook(book);
			logger.info("orders {}", orders);
			orders = excludeSelfOrders(orders);
			logger.info("orders after {}", orders);
			int days = MAX_DAYS;
			long currentOrderDateInMillis = orderDate.getTime();
			logger.info("current order {}", new Date(currentOrderDateInMillis));
			for (Orders order : orders) {
				long orderDateInMillis = order.getOrderDate().getTime();
				if (currentOrderDateInMillis < orderDateInMillis) {
					logger.info("current {}, order {}", new Date(currentOrderDateInMillis), new Date(orderDateInMillis));
					Double daysRes = (double) (orderDateInMillis - currentOrderDateInMillis)
							/ MILLIS_IN_DAY;
					days = (int) Math.round(daysRes);
					if (days <= 0)
						throw new Exception("Incorrect Date Choosen");
					else if (days > MAX_DAYS)
						days = MAX_DAYS;
					break;
				}
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
			int days;
			try {
				days = getCorrectAmountOfOrderDays(book, orderDate);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			Orders order = new Orders(person, book, orderDate, days);
			save(order);
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
			int orderDays;
			try {
				orderDays = getCorrectAmountOfOrderDays(book, orderDate);
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
			order.setOrderDate(orderDate);
			order.setDaysAmount(orderDays);
			order.setChanged(false);
			update(order);
			logger.info("person {} edit order for book {} to date {} for {}  days",
					person, book, orderDate, orderDays);
			return order;
	
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

		@Transactional
		private List<Orders> excludeSelfOrders(List<Orders> orders) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Person person = personService.getByEmail(email);
			List<Orders> excluded = new ArrayList<>();
			for(Orders order : orders) {
				if(order.getPerson().getPid() == person.getPid())
					continue;
				excluded.add(order);
			}
			return excluded;
		}

}