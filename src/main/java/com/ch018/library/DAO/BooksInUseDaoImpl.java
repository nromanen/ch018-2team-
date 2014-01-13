package com.ch018.library.DAO;


import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class BooksInUseDaoImpl implements BooksInUseDao {
        
        private final static int DAY_END_HOUR = 23;
        private final static int DAY_END_MINUTES = 59;
        private final static int DAY_END_SECONDS = 59;
    
        Logger logger = LoggerFactory.getLogger(BooksInUseDaoImpl.class);
    
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
                return  factory.getCurrentSession().createCriteria(BooksInUse.class)
                        .add(Restrictions.ge("returnDate", returnDate))
                        .list();       
        }
        
        @Override
        public Date getMinOrderDate(Book book) {
            Date minDate = (Date) factory.getCurrentSession().createCriteria(BooksInUse.class).add(Restrictions.eq("book", book))
                    .setProjection(Projections.projectionList().add(Projections.min("returnDate"))).uniqueResult();
            if (minDate == null) {
                return new Date();
            } else {
                return minDate;

            }
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

        @Override
        public List<BooksInUse> getBooksInUseByReturnDateLe(Date date) {
        
            try {
                logger.info("before factory");
                return factory.getCurrentSession().createCriteria(BooksInUse.class)
                        .add(Restrictions.le("returnDate", getDateToWithoutTime(date)))
                        .list();
            } catch (Exception e) {
                logger.error("error {} during getBooksInUseByReturnDateLe with date{}", e.getMessage(), date);
                return null;
            }
            
        
        }
        
        private Date getDateFromWithoutTime(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
        
        private Date getDateToWithoutTime(Date date) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, DAY_END_HOUR);
            calendar.set(Calendar.MINUTE, DAY_END_MINUTES);
            calendar.set(Calendar.SECOND, DAY_END_SECONDS);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }
    }