package com.ch018.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.util.SearchParams;
import com.ch018.library.validation.BookEditValidator;

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
             List<Book> getBooksComplex(SearchParams searchParams);
             List<Book> getBooksByPublisher(String publisher);
             List<Book> getBooksByPagesEq(int pages);
             List<Book> advancedSearch(Book book);
             List<Book> simpleSearch(String query);
             Map<BooksInUse, Integer> getHolders(Book book);
             void update(Book book, int genreId);
             HashMap<Book, String> getAllByLocale(Locale locale);
             HashMap<Book, String> getBooksByLocale(List<Book> book, Locale locale);
             HashMap<Book, String> getBookByLocale(Book book, Locale locale);
             Integer getMinIntegerField(String field);
             Integer getMaxIntegerField(String field);
             List<Book> getLastByField(String field, int quantity);
}
