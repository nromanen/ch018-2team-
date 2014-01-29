package com.ch018.library.service;

import com.ch018.library.DAO.BooksInUseDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Edd Arazian
 */
@Service
public class BookInUseServiceImpl implements BookInUseService {

		@Autowired
		private BooksInUseDao useDao;
		@Autowired
		private PersonService personService;
		@Autowired
		private BookService bookService;
	
		private final Logger logger = LoggerFactory
				.getLogger(BookInUseServiceImpl.class);
	
		@Override
		@Transactional
		public void save(BooksInUse booksInUse) {
			useDao.save(booksInUse);
		}
	
		@Override
		@Transactional
		public void delete(BooksInUse booksInUse) {
			useDao.delete(booksInUse);
		}
	
		@Override
		@Transactional
		public void update(BooksInUse booksInUse) {
			useDao.update(booksInUse);
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getAll() {
			return useDao.getAll();
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getBooksInUseByPerson(Person person) {
			return useDao.getBooksInUseByPerson(person);
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getBooksInUseByBook(Book book) {
			return useDao.getBooksInUseByBook(book);
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getBooksInUseByIssueDate(Date issue) {
			return useDao.getBooksInUseByIssueDate(issue);
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
			return useDao.getBooksInUseByReturnDate(returnDate);
		}
	
		@Override
		@Transactional
		public Date getMinReturnDate(Book book) {
			return useDao.getMinReturnDate(book);
		}
	
		@Override
		public BooksInUse getBookInUseById(int id) {
			return useDao.getBookInUseById(id);
		}
	
		@Override
		@Transactional
		public boolean isPersonHaveBook(Person person, Book book) {
			return useDao.isPersonHaveBook(person, book);
		}
	
		@Override
		@Transactional
		public void getBookBack(BooksInUse bookInUse) {
	
			Date now = new Date();
	
			Person person = bookInUse.getPerson();
			int booksReturnedIntime = person.getTimelyReturn();
			int booksReturnedNotIntime = person.getUntimekyReturn();
			int booksOnHands = 0;
	
			if (now.before(bookInUse.getReturnDate())) {
				booksReturnedIntime += 1;
				person.setTimelyReturn(booksReturnedIntime);
			} else if (now.after(bookInUse.getReturnDate())) {
				booksReturnedNotIntime += 1;
				person.setUntimekyReturn(booksReturnedNotIntime);
			}
	
			booksOnHands = person.getMultiBook();
			booksOnHands += 1;
			person.setMultiBook(booksOnHands);
			personService.update(person);
			personService.countRating(person);
			
			Book book = bookInUse.getBook();
			int quantity = book.getCurrentQuantity();
			quantity += 1;
			book.setCurrentQuantity(quantity);
			bookService.update(book);
	
			delete(bookInUse);
	
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getBooksInUseByReturnDateLe(Date date) {
			logger.info("in service");
			return useDao.getBooksInUseByReturnDateLe(date);
	
		}



}
