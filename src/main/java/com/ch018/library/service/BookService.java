package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {
             void save(Book book);
             void delete(Book book);
             void update(Book book);
             void update(Book book, Integer gid);
             List<Book> getAll();
             Book getBookById(int id);
             List<Book> getBooksByTitle(String title);
             List<Book> getBooksByAuthors(String authors);
             List<Book> getBooksByYear(int year);
             List<Book> getBooksByGenre(Genre genre);
             List<Book> getBooksByPublisher(String publisher);
             List<Book> getBooksByPagesEq(int pages);
             List<Book> advancedSearch(Book book);
             List<Book> simpleSearch(String query);
             Map<BooksInUse, Integer> getHolders(Book book);
             Integer getMinIntegerField(String field);
             Integer getMaxIntegerField(String field);
             List<Book> getLastByField(String field, int quantity);
             List<Book> getRecommended(int quantity);
             long getBooksCount();
             List<Book> getAllPagin(int n);
}
