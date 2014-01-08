/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import org.hibernate.Criteria;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {

   
    
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
    public Date getMinOrderDate(Book book){
       
        
        
            Query query = factory.getCurrentSession().createSQLQuery("select min(return_date) from booksinuse where bid = :id")
                .setString("id", String.valueOf(book.getbId()));
            Date minDate;
            if((minDate = (Date)query.uniqueResult()) == null)
                return new Date();
            else
                return minDate;
        
 
     
    }


	@Override
	public List<Date> getBooksInUseToReturnDate() {
		
		Session session = factory.openSession();
		Query query = session.createQuery("SELECT returnDate FROM BooksInUse");//Session is still open???
		List<Date> dates = query.list(); 
		return dates;
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
	public List<Date> getBooksInUseToIssueToday() {
		
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		begin.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
		
		
		Session session = factory.openSession();  
		Query query = session.createQuery("FROM BooksInUse WHERE "); //Session is still open???
		List<Date> dates = query.list(); 
		
		return dates;
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
