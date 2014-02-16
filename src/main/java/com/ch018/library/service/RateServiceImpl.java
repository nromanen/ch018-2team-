package com.ch018.library.service;

import java.util.Date;

import org.hibernate.AssertionFailure;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.DAO.RateDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;
import com.ch018.library.exceptions.BookAlreadyRatedException;
import com.ch018.library.util.DataModelContainer;

@Service
public class RateServiceImpl implements RateService {

		private final Logger logger = LoggerFactory.getLogger(RateServiceImpl.class);
		
		@Autowired
		RateDao rateDao;
		
		@Autowired
		BookDao bookDao;
		
		@Autowired
		PersonDao personDao;
		
		@Autowired
		DataModelContainer dataModelContainer;
	
		@Override
		@Transactional
		public void save(Rate rate) {
			rateDao.save(rate);
			
		}
	
		@Override
		@Transactional
		public void addRate(Rate rate, Integer bookId) throws BookAlreadyRatedException {
			
			Book book = bookDao.getBookById(bookId);
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Person person = personDao.getByEmail(email);
			
			rate.setBook(book);
			rate.setPerson(person);
			rate.setRateDate(new Date());
			
			try {
				rateDao.save(rate);	
			} catch (ConstraintViolationException e) {
				throw new BookAlreadyRatedException();
			}
			
			if(dataModelContainer.isInit()) {
				dataModelContainer.refresh();
			}

			float oldBookRating = book.getRating();
			int oldBookVotes = book.getVotes();
			
			float currentPersonScore = rate.getScore(); 
			int newBookVotes = oldBookVotes + 1;
			float newBookRating;
			logger.info("oldRate {}, oldVote {}, curRate {}", oldBookRating, oldBookVotes, currentPersonScore);
			
			try {
				newBookRating = (oldBookRating*oldBookVotes + currentPersonScore) / newBookVotes;
				book.setRating(newBookRating);
				book.setVotes(newBookVotes);
				bookDao.update(book);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			
		}
	
		@Override
		@Transactional
		public Rate getRate(Person person, Book book) {
			return rateDao.getRate(person, book);
		}

		@Override
		@Transactional
		public long getRatesCount() {
			
			return rateDao.getRatesCount();
		}

	
		
}
