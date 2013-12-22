/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Edd Arazian
 */
public interface WishListDao {
    
        public void save(WishList wish);
        public void delete(WishList wish);
        public void update(WishList wish);
        public List<WishList> getAll();
        public List<WishList> getOrderByPerson(Person person);
        public List<WishList> getOrderByBook(Book book);
        public WishList getOrderByID(int id);
    
}
