package com.ch018.library.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class BookDaoImpl implements BookDao {


	
    static Logger log = LogManager.getLogger(BookDaoImpl.class);

        
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
    public List<Book> getBooksComplex(String query) {
        
        query = "%" + query + "%";
        return factory.getCurrentSession().createQuery("from Book b where b.title like :q"
                + " OR b.authors like :q"
                + " OR b.publisher like :q"
                + " OR b.genre.description like :q").
                setString("q", query).list();
    }
    
    @Override
    public List<Book> getBooksComplex(Comparator<Book> comparator, String... query) {
        
        Set<Book> books = new TreeSet<>(comparator);
        for(int i = 0; i < query.length; i++){
            books.addAll(getBooksComplex(query[i]));
        }
        
        return new ArrayList<>(books);
    }


        
    
	
}
