/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
/**
 *
 * @author Edd Arazian
 */
@Repository
public class WishListDaoImpl implements WishListDao {
    
		private final Logger logger = LoggerFactory.getLogger(WishListDaoImpl.class);
	
        @Autowired
        private SessionFactory factory;

        @Override
        public void save(WishList wish) {
            factory.getCurrentSession().save(wish);
        }
        
        @Override
        public void delete(WishList wish) {
            factory.getCurrentSession().delete(wish);
        }
        
        @Override
        public void update(WishList wish) {
            factory.getCurrentSession().update(wish);
        }
        
        @Override
        public List<WishList> getAll() {
            return factory.getCurrentSession().createCriteria(WishList.class).list();
        }
        
        @Override
        public List<WishList> getWishByPerson(Person person) {
            return factory.getCurrentSession().createCriteria(WishList.class).add(Restrictions.eq("person", person)).list();
        }
        
        @Override
        public List<WishList> getWishByBook(Book book) {
            return factory.getCurrentSession().createCriteria(WishList.class).add(Restrictions.eq("book", book)).list();
        }
        
        @Override
        public WishList getWishByID(int id) {
        	WishList wishlist = null;
        	Criteria criteria = factory.getCurrentSession().createCriteria(WishList.class);
        	criteria.add(Restrictions.eq("id", id));
        	
        	try {
        		wishlist = (WishList) criteria.uniqueResult();
        	} catch (Exception e) {
        		logger.error(e.getMessage());
        	}
        	
            return wishlist;
        }
        
        @Override
        public WishList getWishByPersonBook(Person person, Book book) {
        	WishList wishlist = null;
        	Criteria criteria = factory.getCurrentSession().createCriteria(WishList.class);
        	criteria.add(Restrictions.eq("person", person));
        	criteria.add(Restrictions.eq("book", book));
        	
        	try{
                WishList wish = (WishList) criteria.uniqueResult();
            } catch(Exception e) {
            	logger.error(e.getMessage());
            }
        	
        	return wishlist;
        }
        
}
