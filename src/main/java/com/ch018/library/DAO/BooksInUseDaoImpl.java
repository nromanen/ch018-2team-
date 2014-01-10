package com.ch018.library.DAO;


import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import org.hibernate.Query;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {
    @Autowired
    private SessionFactory factory;   
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
    public Date getMinOrderDate(Book book) { 
            Date minDate = (Date) factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("book", book))
                    .setProjection(Projections.projectionList().add(Projections.min("returnDate"))).uniqueResult();
            if(minDate == null)
                return new Date();
            else
                return minDate;
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
            BooksInUse use = (BooksInUse) factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("person", person))
                    .add(Restrictions.eq("book", book)).uniqueResult();
            return use == null ? false : true;
        } catch (Exception e) {
            return false;
        }
    }

    
}
