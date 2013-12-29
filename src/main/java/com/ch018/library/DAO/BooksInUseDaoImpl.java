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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {

    static Logger log = LogManager.getLogger(BooksInUseDaoImpl.class);
    
    @Autowired
    SessionFactory factory;
    
    @Override
    public void save(BooksInUse booksInUse) {
            factory.getCurrentSession().save(booksInUse);
    }

    @Override
    public void delete(BooksInUse booksInUse) {
        
            factory.getCurrentSession().delete(booksInUse);

    }

    @Override
    public void update(BooksInUse booksInUse) {
            factory.getCurrentSession().update(booksInUse);
    }

    @Override
    public List<BooksInUse> getAll() {
            return  factory.getCurrentSession().createCriteria(BooksInUse.class).list();
          
    }

    @Override
    public List<BooksInUse> getBooksInUseByPerson(Person person) {
    
            return  factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("person", person)).list();
           
    }

    @Override
    public List<BooksInUse> getBooksInUseByBook(Book book) {
    
            return  factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("book", book)).list();
            
    }

    @Override
    public List<BooksInUse> getBooksInUseByIssueDate(Date issue) {
        
            return factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("issueDate", issue)).list();
           
    }

    @Override
    public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
        
            return  factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("returnDate", returnDate)).list();
            
    }

    @Override
    public Date getBookWithLastDate(Book book){
       
        return (Date) factory.getCurrentSession().createSQLQuery("select min(return_date) from booksinuse where bid = :id")
                .setString("id", String.valueOf(book.getbId())).list().get(0);
 
     
    }

    @Override
    public boolean isPersonHaveBook(Person person, Book book) {
        try{
        BooksInUse use = (BooksInUse) factory.getCurrentSession().createQuery("from BooksInUse where person = :p AND book = :b")
                .setParameter("p", person).setParameter("b", book).list().get(0);
        return use == null ? false : true;
        }catch(Exception e){
            return false;
        }
    }
    
    
    
    
    
}
