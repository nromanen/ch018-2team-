/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;
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
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
            Date minDate = (Date) factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("book", book))
                    .setProjection(Projections.projectionList().add(Projections.min("returnDate"))).uniqueResult();
            if(minDate == null)
                return new Date();
            else
                return minDate;
	}
	@Override
	public List<Date> getBooksInUseToReturnDate() {
		Session session = factory.openSession();
		Query query = session.createQuery("SELECT returnDate FROM BooksInUse");
		List<Date> dates = query.list(); 
		
		if(session.isOpen()){
			session.close();
		}
		
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
		Query query = session.createQuery("FROM BooksInUse WHERE "); 
		List<Date> dates = query.list(); 
		
		if (session.isOpen()) {
			session.close();
		}
		
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

	@Override
	public List<Person> getAllUsers() {
		// TODO Auto-generated method stub
		
		String groupByquery = "SELECT person from BooksInUse biu group by biu.person";
		
		Session session = factory.openSession();
		
		Query q = session.createQuery(groupByquery);
		
		List<Person> users = q.list();
		
		if(session.isOpen()){
			session.close();
		}

		return users;
	}
    
}
