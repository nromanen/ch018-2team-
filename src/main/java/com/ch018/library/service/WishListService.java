
package com.ch018.library.service;


import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import java.util.List;

/**
 *
 * @author Edd Arazian
 */
public interface WishListService {
    
        void save(WishList wish);
        void delete(WishList wish);
        void update(WishList wish);
        List<WishList> getAll();
        List<WishList> getWishByPerson(Person person);
        List<WishList> getWishByBook(Book book);
        WishList getWishByID(int id);
        
        
    
}
