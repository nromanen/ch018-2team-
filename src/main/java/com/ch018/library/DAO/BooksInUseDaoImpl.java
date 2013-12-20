/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {

    static Logger log = LogManager.getLogger(BooksInUseDaoImpl.class);
    
    @Override
    public void save(BooksInUse booksInUse) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(booksInUse);
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
    public void delete(BooksInUse booksInUse) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(booksInUse);
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
    public void update(BooksInUse booksInUse) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(booksInUse);
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
    public List<BooksInUse> getAll() {
    
        Session session = null;
        List<BooksInUse> booksInUse = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            booksInUse = session.createCriteria(BooksInUse.class).list();
            System.out.println("!!!!+==============+++++++++++++++++++++++++ " + booksInUse);
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
        return booksInUse;
    }

    @Override
    public List<BooksInUse> getBooksInUseByPerson(Person person) {
    
        Session session = null;
        List<BooksInUse> booksInUse = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            booksInUse = session.createCriteria(BooksInUse.class).add(Restrictions.eq("person", person)).list();
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
        return booksInUse;
    }

    @Override
    public List<BooksInUse> getBooksInUseByBook(Book book) {
    
        Session session = null;
        List<BooksInUse> booksInUse = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            booksInUse = session.createCriteria(BooksInUse.class).add(Restrictions.eq("book", book)).list();
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
        return booksInUse;
    }

    @Override
    public List<BooksInUse> getBooksInUseByIssueDate(Date issue) {
        Session session = null;
        List<BooksInUse> booksInUse = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            booksInUse = session.createCriteria(BooksInUse.class).add(Restrictions.eq("issueDate", issue)).list();
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
        return booksInUse;
    }

    @Override
    public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
        Session session = null;
        List<BooksInUse> booksInUse = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            booksInUse = session.createCriteria(BooksInUse.class).add(Restrictions.eq("returnDate", returnDate)).list();
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
        return booksInUse;
    }
    
    
    
}
