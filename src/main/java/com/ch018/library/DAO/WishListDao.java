/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
/**
 *
 * @author Edd Arazian
 */
@Repository
public interface WishListDao {
        void save(WishList wish);
        void delete(WishList wish);
        void update(WishList wish);
        List<WishList> getAll();
        List<WishList> getWishByPerson(Person person);
        List<WishList> getWishByBook(Book book);
        WishList getWishByPersonBook(Person person, Book book);
        WishList getWishByID(int id);
}
