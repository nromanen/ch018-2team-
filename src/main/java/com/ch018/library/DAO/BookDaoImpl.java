package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Orders;
import com.ch018.library.util.Constants;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.SearchParamsBook;
import com.ch018.library.util.SearchParamsPerson;

@Repository
public class BookDaoImpl implements BookDao {

		private final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
	
		@Autowired
		private SessionFactory factory;
		
		
		@Override
		public void save(Book book) {
			try {
				factory.getCurrentSession().save(book);
			} catch (Exception e) {
				logger.error("error during save book {}, error {}", book, e.getMessage());
			}
		}
	
		@Override
		public void delete(Book book) {
			try {
				factory.getCurrentSession().delete(book);
			} catch (Exception e) {
				logger.error("error during delete book {}", book);
			}
		}
	
		@Override
		public void update(Book book) {
			try {
				factory.getCurrentSession().update(book);
			} catch (Exception e) {
				logger.error("error during update book {}", e.getMessage());
			}
	
		}
	
		@Override
		public List<Book> getAll() {
			return factory.getCurrentSession().createCriteria(Book.class).list();
		}
	
		@Override
		public Book getBookById(int id) {
			Session session = factory.getCurrentSession();
			Book book = (Book) session.createCriteria(Book.class).add(Restrictions.eq("bId", id)).uniqueResult();
			session.clear();
			return book;
		}
	
		@Override
		public List<Book> getBooksByTitle(String title) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.like("title", "%" + title + "%")).list();
		}
	
		@Override
		public List<Book> getBooksByAuthors(String authors) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.like("authors", "%" + authors + "%")).list();
		}
	
		@Override
		public List<Book> getBooksByYear(int year) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.eq("year", year)).list();
		}
	
		@Override
		public List<Book> getBooksByPublisher(String publisher) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.eq("publisher", publisher)).list();
		}
	
		@Override
		public List<Book> getBooksByPagesEq(int pages) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.eq("pages", pages)).list();
		}
	
		@Override
		public List<Book> getBooksByGenre(Genre genre) {
			return factory.getCurrentSession().createCriteria(Book.class)
					.add(Restrictions.eq("genre", genre)).list();
		}
	
		@Override
		public List<Book> advancedSearch(Book book) {
			Session session = factory.openSession();
	
			Criteria criteria = session.createCriteria(Book.class);
	
			if (!book.getTitle().equals("")) {
				criteria.add(Restrictions.like("title", book.getTitle(), MatchMode.ANYWHERE));
			}
			if (!book.getAuthors().equals("")) {
				criteria.add(Restrictions.like("authors", book.getAuthors(), MatchMode.ANYWHERE));
			}
			if (!(book.getGenre() == null)) {
				//criteria.add(Restrictions.eq("genre", book.getGenre()));
			}
			if (book.getYear() != 0) {
				criteria.add(Restrictions.eq("year", book.getYear()));
			}
			if (!book.getPublisher().equals("")) {
				criteria.add(Restrictions.like("publisher", book.getPublisher(), MatchMode.ANYWHERE));
			}
			if (book.getPages() != 0) {
				criteria.add(Restrictions.eq("pages", book.getPages()));
			}
	
			List<Book> books = criteria.list();
	
			return books;
		}
	
		@Override
		public List<Book> simpleSearch(String query) {
			// TODO Auto-generated method stub
			if (!query.equals("")) {
	
				Session session = factory.openSession();
	
				Criteria criteria = session.createCriteria(Book.class);
	
				Disjunction or = Restrictions.disjunction();
	
				or.add(Restrictions.like("title", query, MatchMode.ANYWHERE));
	
				or.add(Restrictions.like("authors", query, MatchMode.ANYWHERE));
	
				or.add(Restrictions.like("publisher", query, MatchMode.ANYWHERE));
	
				criteria.add(or);
	
				List<Book> books = criteria.list();
	
				return books;
			} else {
				return null;
			}
		}
	
	
		@Override
		public Integer getMinIntegerField(String field) {
			
			Integer minPages = (Integer) factory.getCurrentSession()
					.createCriteria(Book.class)
					.setProjection(Projections.projectionList()
					.add(Projections.min(field))).uniqueResult();
			return minPages;
			
		}
		
		@Override
        public Integer getMaxIntegerField(String field) {
        	Integer maxPages = (Integer) factory.getCurrentSession()
					.createCriteria(Book.class)
					.setProjection(Projections.projectionList()
					.add(Projections.max(field))).uniqueResult();
			return maxPages;
        }

		@Override
		public List<Book> getLastByField(String field, int quantity) {
			Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
			List<Book> books = criteria.addOrder(Order.desc(field)).setMaxResults(quantity).list();
			return books;
		}

		@Override
		public List<Book> getBooksFromRecommendedList(List<RecommendedItem> items) {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
			Disjunction orDisjunction = Restrictions.disjunction();
			
			for(RecommendedItem item : items) {
				orDisjunction.add(Restrictions.eq("bId", (int) item.getItemID()));
			}
			
			criteria.add(orDisjunction);
			
			List<Book> books = criteria.list();
			
			logger.info("recommended books = {}", books);

			
			return books;
			
		}

		@Override
		public long getBooksCount() {
			Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
			criteria.setProjection(Projections.rowCount());
			
			return (long) criteria.uniqueResult();
		}

		
		
}
