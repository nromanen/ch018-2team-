package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;

@Service
public interface BookService {
	void save(Book book);
        void delete(Book book);
        void update(Book book);
	List<Book> getAll();
	Book getBookById(int id);
	List<Book> getBooksByTitle(String title);
	List<Book> getBooksByAuthors(String authors);
	List<Book> getBooksByYear(int year);

   
        List<Book> getBooksByGenre(Genre genre);
        List<Book> getBooksComplex(String query);

    List<Book> getBooksByPublisher(String publisher);
    List<Book> getBooksByPagesEq(int pages);
   
    List<Book> advancedSearch(Book book);
    List<Book> simpleSearch(String query);

        
}
