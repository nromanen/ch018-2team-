/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
            return (WishList) factory.getCurrentSession().get(WishList.class, id);
        }
        @Override
        public WishList getWishByPersonBook(Person person, Book book) {
             return (WishList) factory.getCurrentSession().createCriteria(WishList.class).add(Restrictions.eq("person", person))
                     .add(Restrictions.eq("book", book)).uniqueResult();
        }
        @Override
        public boolean isPersonWishBook(Person person, Book book) {
            try{
                WishList wish = (WishList) factory.getCurrentSession()
                			.createCriteria(WishList.class)
                            .add(Restrictions.eq("person", person))
                            .add(Restrictions.eq("book", book)).list().get(0);
                return wish == null ? false : true;
            } catch(Exception e) {
                return false;
            }

        } 
}
