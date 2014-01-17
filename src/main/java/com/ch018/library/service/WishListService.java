
package com.ch018.library.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
/**
 *
 * @author Edd Arazian
 */
@Service
public interface WishListService {
        void save(WishList wish);
        void delete(WishList wish);
        void update(WishList wish);
        List<WishList> getAll();
        List<WishList> getWishByPerson(Person person);
        List<WishList> getWishByBook(Book book);
        WishList getWishByPersonBook(Person person, Book book);
        WishList getWishByID(int id);
        boolean isPersonWishBook(Person person, Book book); 
}
