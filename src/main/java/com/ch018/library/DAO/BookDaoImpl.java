package com.ch018.library.DAO;

import java.util.List;


import org.springframework.stereotype.Repository;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import org.hibernate.Query;
import org.hibernate.Session;


import java.util.TreeSet;
import org.hibernate.Query;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class BookDaoImpl implements BookDao {


        
    final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

        
    @Autowired
    SessionFactory factory;

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
            return factory.getCurrentSession().createCriteria(Book.class).add(Restrictions.like("author", authors)).list();
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
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		
		String q =  "from Book where 1=1 ";
		
		if(!book.getTitle().equals("")){
			q += " and title = :title";
		}
		if(!book.getAuthors().equals("")) {
			q += q.endsWith("where") ? " authors = :authors" : " and authors = :authors";
		}
		if(!book.getGenre().equals("")) {
			q += q.endsWith("where") ? " genre = :genre" : " and genre = :genre";
		}
		if(book.getYear() != 0) {
			q += q.endsWith("where") ? " year = :year" : " and year = :year";
		}
		if(!book.getPublisher().equals("")) {
			q += q.endsWith("where") ? " publisher = :publisher" : " and publisher = :publisher";
		}
		if(book.getPages() != 0) {
			q += q.endsWith("where") ? " pages = :pages" : " and pages = :pages";
		}
		
		Query query = session.createQuery(q);
		
		if(!book.getTitle().equals("")){
			query.setParameter("title", book.getTitle());
		}
		if(!book.getAuthors().equals("")) {
			query.setParameter("authors", book.getAuthors());
		}
		if(!book.getGenre().equals("")) {
			query.setParameter("genre", book.getGenre());
		}
		if(book.getYear() != 0) {
			query.setParameter("year", book.getYear());
		}
		if(!book.getPublisher().equals("")) {
			query.setParameter("publisher", book.getPublisher());
		}
		if(book.getPages() != 0) {
			query.setParameter("pages", book.getPages());
		}
		
		List<Book> books = query.list();
		
		/*Book book1 = new Book();
		book1 = books.get(0);
		System.out.println(book1.getTitle() + " " + book1.getAuthors() 
				+ " " + book1.getYear() + "~~~~~~~~~~~~~~");*/
		return books;
	}

	@Override
	public List<Book> simpleSearch(String query) {
		// TODO Auto-generated method stub
		if (!query.equals("")){
		
		Session session = factory.openSession();
		
		Query q = session.createQuery("from Book where title like :parameter or"
										+ " authors like :parameter or "
										+ " publisher like :parameter");
		q.setParameter("parameter", query + "%");
		
		List<Book> books = q.list();
		
		return books;
		}else {
			return null;
		}
	}

    @Override
    public List<Book> getBooksComplex(String query) {
        
        query = "%" + query + "%";
        Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);
        SimpleExpression tExp = Restrictions.like("title", query);
        SimpleExpression aExp = Restrictions.like("authors", query);
        SimpleExpression pExp = Restrictions.like("publisher", query);
        criteria.add(Restrictions.or(tExp, aExp, pExp));
        return criteria.list();
    }
    
    @Override
    public List<Book> getBooksComplexByParams(Integer genreId, String title, String authors, String publisher){
        
        Criteria criteria = factory.getCurrentSession().createCriteria(Book.class);

        if(genreId > 0){
            criteria.add(Restrictions.eq("genre.id", genreId));
        }
        if(!title.equals(""))
            criteria.add(Restrictions.like("title", "%" + title + "%"));
        if(!authors.equals(""))
            criteria.add(Restrictions.like("authors", "%" + authors + "%"));
        if(!publisher.equals(""))
            criteria.add(Restrictions.like("publisher", "%" + publisher + "%"));

        return criteria.list();

        
    }
 


        
    
        
}
