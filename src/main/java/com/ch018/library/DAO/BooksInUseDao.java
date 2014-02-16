package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Edd Arazian
 */
@Repository
public interface BooksInUseDao {
        void save(BooksInUse booksInUse);
        void delete(BooksInUse booksInUse);
        void update(BooksInUse booksInUse);
        BooksInUse getBookInUseById(int id);
        List<BooksInUse> getAll();
        List<BooksInUse> getBooksInUseByPerson(Person person);
        List<BooksInUse> getBooksInUseByBook(Book book);
        List<BooksInUse> getBooksInUseByReturnDate(Date returnDateFrom);
        Date getMinReturnDate(Book book);
        boolean isPersonHaveBook(Person person, Book book); 
        List<BooksInUse> getBooksInUseByReturnDateLe(Date date);
        long getBooksInUseCountForPerson(Person person);
}
