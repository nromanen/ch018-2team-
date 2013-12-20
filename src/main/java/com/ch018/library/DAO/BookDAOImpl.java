package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.util.HibernateUtil;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import sun.java2d.loops.DrawGlyphListAA;

@Repository
public class BookDAOImpl implements BookDAO {


	
    static Logger log = LogManager.getLogger(BookDAOImpl.class);

        

    @Override
    public void save(Book book) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
    }

    @Override
    public void delete(Book book) {
    
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(book);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        
    }

    @Override
    public void update(Book book) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
    }

    @Override
    public List<Book> getAll() {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Session session = null;
        Book book = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            book = (Book) session.get(Book.class, id);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return book;
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.like("title", title)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthors(String authors) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.like("author", authors)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksByYear(int year) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.eq("year", year)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.eq("publisher", publisher)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksByPagesEq(int pages) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.eq("pages", pages)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        Session session = null;
        List<Book> books = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            books = session.createCriteria(Book.class).add(Restrictions.eq("genre", genre)).list();
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
        return books;
    }
        
    
	
}
