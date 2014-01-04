/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 *
 * @author Edd Arazian
 */
@Service
public interface BookInUseService {
    
    void save(BooksInUse booksInUse);
    void delete(BooksInUse booksInUse);
    void update(BooksInUse booksInUse);
    List<BooksInUse> getAll();
    List<BooksInUse> getBooksInUseByPerson(Person person);
    List<BooksInUse> getBooksInUseByBook(Book book);
    List<BooksInUse> getBooksInUseByIssueDate(Date issue);
    List<BooksInUse> getBooksInUseByReturnDate(Date issue);
    Date getMinOrderDate(Book book);

    List<Date> getBooksInUseToReturnDate();
    BooksInUse getBookInUseById(int id);
    List<Date> getBooksInUseToIssueToday();

    boolean isPersonHaveBook(Person person, Book book);
    

}
