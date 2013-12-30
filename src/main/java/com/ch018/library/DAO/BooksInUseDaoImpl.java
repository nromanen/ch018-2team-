/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	public List<Date> getBooksInUseToReturnDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session session = factory.openSession();
		Query query = session.createQuery("SELECT returnDate FROM BooksInUse");//Session is still open???
		List<Date> dates = query.list(); 
		return dates;
	}

	@Override
	public BooksInUse getBookInUseById(int id) {
		Session session = factory.openSession();
		return (BooksInUse) session.get(BooksInUse.class, id);//Session is still open???
	}

	@Override
	public List<Date> getBooksInUseToIssue() {

		Session session = factory.openSession();  
		Query query = session.createQuery("SELECT issueDate FROM BooksInUse"); //Session is still open???
		List<Date> dates = query.list(); 
		
		return dates;
	}
    
    
    
    
    
}
