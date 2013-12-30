/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

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
    List<BooksInUse> getBooksInUseByIssueDate(Date issue);
    List<BooksInUse> getBooksInUseByReturnDate(Date issue);
    Date getBookWithLastDate(Book book);
    List<Date> getBooksInUseToReturnDate();
    List<Date> getBooksInUseToIssue();
}
