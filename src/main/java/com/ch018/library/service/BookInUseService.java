package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;

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
        BooksInUse getBookInUseById(int id);
        boolean isPersonHaveBook(Person person, Book book);
        void getBookBack(BooksInUse bookInUse);
}
