package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        Date getMinReturnDate(Book book);
        BooksInUse getBookInUseById(int id);
        boolean isPersonHaveBook(Person person, Book book);
        void getBookBack(BooksInUse bookInUse);
        List<BooksInUse> getBooksInUseByReturnDateLe(Date date);
        List<BooksInUse> getBooksInUseByBookTitle(String title);
        List<BooksInUse> getBooksInUseByPersonSurname(String surname);
        List<BooksInUse> getBooksInUseByPersonSurnameAndBookTitle(List<BooksInUse> list,String surname);


}
