package com.ch018.library.DAO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;

/**
 * 
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {

		private static final int DAY_END_HOUR = 23;
		private static final int DAY_END_MINUTES = 59;
		private static final int DAY_END_SECONDS = 59;
	
		private Logger logger = LoggerFactory.getLogger(BooksInUseDaoImpl.class);
	
		@Autowired
		private SessionFactory factory;
	
		@Override
		public void save(BooksInUse booksInUse) {
			try {
				factory.getCurrentSession().save(booksInUse);
			} catch (Exception e) {
				logger.error("during save booksInUse {}", booksInUse);
			}
	
		}
	
		@Override
		public void delete(BooksInUse booksInUse) {
			try {
				factory.getCurrentSession().delete(booksInUse);
			} catch (Exception e) {
				logger.error("during delete booksInUse {}", booksInUse);
			}
	
		}
	
		@Override
		public void update(BooksInUse booksInUse) {
			try {
				factory.getCurrentSession().update(booksInUse);
			} catch (Exception e) {
				logger.error("during update booksInUse {}", booksInUse);
			}
	
		}
	
		@Override
		public List<BooksInUse> getAll() {
			return factory.getCurrentSession().createCriteria(BooksInUse.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.list();
	
		}
	
		@Override
		public List<BooksInUse> getBooksInUseByPerson(Person person) {
			return factory.getCurrentSession().createCriteria(BooksInUse.class)
					.add(Restrictions.eq("person", person)).list();
		}
	
		@Override
		public List<BooksInUse> getBooksInUseByBook(Book book) {
			return factory.getCurrentSession().createCriteria(BooksInUse.class)
					.add(Restrictions.eq("book", book)).list();
		}
	
		@Override
		public List<BooksInUse> getBooksInUseByIssueDate(Date issue) {
			return factory.getCurrentSession().createCriteria(BooksInUse.class)
					.add(Restrictions.eq("issueDate", issue)).list();
		}
	
		@Override
		public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
			return factory.getCurrentSession().createCriteria(BooksInUse.class)
					.add(Restrictions.ge("returnDate", returnDate)).list();
		}
	
		@Override
		public Date getMinReturnDate(Book book) {
			Date minDate = (Date) factory
					.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("book", book))
					.setProjection(
							Projections.projectionList().add(
									Projections.min("returnDate"))).uniqueResult();
			if (minDate == null) {
				return new Date();
			} else {
				return minDate;
	
			}
		}
	
		@Override
		public BooksInUse getBookInUseById(int id) {
			Session session = factory.openSession();
			BooksInUse bookInUse = (BooksInUse) session.get(BooksInUse.class, id);
			if (session.isOpen()) {
				session.close();
			}
			return bookInUse;
		}
	
		@Override
		public boolean isPersonHaveBook(Person person, Book book) {
			try {
				BooksInUse use = (BooksInUse) factory.getCurrentSession()
						.createCriteria(BooksInUse.class)
						.add(Restrictions.eq("person", person))
						.add(Restrictions.eq("book", book)).uniqueResult();
				return use == null ? false : true;
			} catch (Exception e) {
				return false;
			}
		}
	
		@Override
		public List<BooksInUse> getBooksInUseByReturnDateLe(Date date) {
	
			try {
				return factory.getCurrentSession().createCriteria(BooksInUse.class)
						.add(Restrictions.le("returnDate", getDateToWithoutTime(date)))
						.list();
			} catch (Exception e) {
				logger.error(
						"error {} during getBooksInUseByReturnDateLe with date{}",
						e.getMessage(), date);
				return null;
			}
	
		}
	
		private Date getDateFromWithoutTime(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
	
		private Date getDateToWithoutTime(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, DAY_END_HOUR);
			calendar.set(Calendar.MINUTE, DAY_END_MINUTES);
			calendar.set(Calendar.SECOND, DAY_END_SECONDS);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
}