/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.WishListDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.exceptions.DeleteSecurityViolationException;
/**
 *
 * @author Edd Arazian
 */
@Service
public class WishListServiceImpl implements WishListService {
	 
	    @Autowired
	    private WishListDao wishDao;
	
	    @Override
	    @Transactional
	    public void save(WishList wish) {
	        wishDao.save(wish);
	    }
	
	    @Override
	    @Transactional
	    public void delete(WishList wish) {
	        wishDao.delete(wish);
	    }
	
	    @Override
	    @Transactional
	    public void update(WishList wish) {
	        wishDao.update(wish);
	    }
	
	    @Override
	    @Transactional(readOnly = true)
	    public List<WishList> getAll() {
	        return wishDao.getAll();
	    }
	
	    @Override
	    @Transactional(readOnly = true)
	    public List<WishList> getWishByPerson(Person person) {
	        return wishDao.getWishByPerson(person);    
	    }
	
	    @Override
	    @Transactional(readOnly = true)
	    public List<WishList> getWishByBook(Book book) {
	        return wishDao.getWishByBook(book);
	    }
	
	    @Override
	    @Transactional(readOnly = true)
	    public WishList getWishByID(int id) {
	        return wishDao.getWishByID(id);
	    }
	
	    @Override
	    @Transactional
	    public WishList getWishByPersonBook(Person person, Book book) {
	        return wishDao.getWishByPersonBook(person, book);
	    }
	
	    @Override
	    @Transactional
	    public boolean isPersonWishBook(Person person, Book book) {
	    	WishList wishlist = getWishByPersonBook(person, book);
	        return wishlist != null ? true : false;
	    }

		@Override
		@Transactional
		public void remove(WishList wish) throws DeleteSecurityViolationException {
			String realUser = SecurityContextHolder.getContext().getAuthentication().getName();
			String userFromWish = wish.getPerson().getEmail();
			if(realUser.equals(userFromWish))
				delete(wish);
			else
				throw new DeleteSecurityViolationException();
			
		}
	    
	    
}
