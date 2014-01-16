package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.transform.ResultTransformer;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;

@Repository
public class BookDaoImpl implements BookDao {

		private final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
		@Autowired
		private SessionFactory factory;

		@Override
		public void save(Book book) {
			factory.getCurrentSession().save(book);
		}

		@Override
		public void delete(Book book) {
			factory.getCurrentSession().delete(book);
		}

		@Override
		public void update(Book book) {
			factory.getCurrentSession().update(book);
		}

		@Override
		public List<Book> getAll() {
			return factory.getCurrentSession().createCriteria(Book.class).list();
		}

		@Override
		public Book getBookById(int id) {
			return (Book) factory.getCurrentSession().get(Book.class, id);
		}

		@Override
		public List<Book> getBooksByTitle(String title) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.like("title", "%" + title + "%")).list();
		}

		@Override
		public List<Book> getBooksByAuthors(String authors) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.like("authors", "%" + authors + "%")).list();
		}

		@Override
		public List<Book> getBooksByYear(int year) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("year", year)).list();
		}

		@Override
		public List<Book> getBooksByPublisher(String publisher) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("publisher", publisher)).list();
		}

		@Override
		public List<Book> getBooksByPagesEq(int pages) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("pages", pages)).list();
		}

		@Override
		public List<Book> getBooksByGenre(Genre genre) {
			return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("genre", genre)).list();
		}

		@Override
		public List<Book> advancedSearch(Book book) {
			Session session = factory.openSession();
			
			Criteria criteria = session.createCriteria(Book.class);
			
			if (!book.getTitle().equals("")) {
				criteria.add(Restrictions.eq("title", book.getTitle()));
			}
			if (!book.getAuthors().equals("")) {
				criteria.add(Restrictions.eq("authors", book.getAuthors()));
			}
			if (!book.getGenre().equals("")) {
				criteria.add(Restrictions.eq("genre", book.getGenre()));
			}
			if (book.getYear() != 0) {
				criteria.add(Restrictions.eq("year", book.getYear()));
			}
			if (!book.getPublisher().equals("")) {
				criteria.add(Restrictions.eq("publisher", book.getPublisher()));
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
				
				or.add(Restrictions.like("title", query, MatchMode.ANYWHERE ));
				
				or.add(Restrictions.like("authors", query, MatchMode.ANYWHERE ));
				
				or.add(Restrictions.like("publisher", query, MatchMode.ANYWHERE ));

				criteria.add(or);
				
				List<Book> books = criteria.list();
			   
				return books;
			} else {
				return null;
			}
		}

		@Override
		public Page getBooksComplex(BookSearch bookSearch) {
			Page page = new Page();
			page.setCurrentPageNum(bookSearch.getViewPageNum());
			bookSearch.setBorders();
			String query = "%" + bookSearch.getQuery() + "%";
			Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
			SimpleExpression tExp = Restrictions.like("title", query);
			SimpleExpression aExp = Restrictions.like("authors", query);
			SimpleExpression pExp = Restrictions.like("publisher", query);
			criteria.add(Restrictions.or(tExp, aExp, pExp));
			page.setGeneralItemsQuantity(criteria.list().size());
			page.setGeneralPagesQuantity(page.getGeneralItemsQuantity()/bookSearch.getBooksOnPage());
			if (bookSearch.getOrder())
				criteria.addOrder(Order.desc(bookSearch.getSort()));
			else
				criteria.addOrder(Order.asc(bookSearch.getSort()));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(bookSearch.getLowBorder()).setMaxResults(bookSearch.getHighBorder());
			page.setBooks(criteria.list());
			return page;
		}

		@Override
		public Page getBooksComplexByParams(BookSearch bookSearch) {
			bookSearch.setBorders();
			Page page = new Page();
			page.setCurrentPageNum(bookSearch.getViewPageNum());
			Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
			if (bookSearch.getGenreId()> 0) {
				criteria.add(Restrictions.eq("genre.id", bookSearch.getGenreId()));
			}
			if (!bookSearch.getTitle().equals("")) {
				criteria.add(Restrictions.like("title", "%" + bookSearch.getTitle() + "%"));
			}
			if (!bookSearch.getAuthors().equals("")) {
				criteria.add(Restrictions.like("authors", "%" + bookSearch.getAuthors() + "%"));
			}
			if (!bookSearch.getPublisher().equals("")) {
				criteria.add(Restrictions.like("publisher", "%" + bookSearch.getPublisher() + "%"));
			}
			/*if (bookSearch.getBookPageStart() != null && bookSearch.getBookPageEnd() != null)
				criteria.add(Restrictions.between("pages", bookSearch.getBookPageStart(), bookSearch.getBookPageEnd()));*/
			page.setGeneralItemsQuantity(criteria.list().size());
			if (bookSearch.getOrder())
				criteria.addOrder(Order.desc(bookSearch.getSort()));
			else
				criteria.addOrder(Order.asc(bookSearch.getSort()));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(bookSearch.getLowBorder()).setMaxResults(bookSearch.getHighBorder());
			page.setBooks(criteria.list());
			
			return page;
		}

		@Override
		public Book getBookWith(int id) {
		
			try {
				Book book = (Book) factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.eq("bId", id)).uniqueResult();
				Hibernate.initialize(book.getPersonsOrders());
				return book;
			} catch (Exception e) {
				
			}
			
			return null;
		}
		
    
}
