package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;



@Repository
public interface BookDao {
        void save(Book book);
        void delete(Book book);
        void update(Book book);
        List<Book> getAll();
        Book getBookById(int id);
        List<Book> getBooksByTitle(String title);
        List<Book> getBooksByAuthors(String authors);
        List<Book> getBooksByYear(int year);
        List<Book> getBooksComplex(String query);
        List<Book> getBooksByPublisher(String publisher);
        List<Book> getBooksByPagesEq(int pages);
        List<Book> getBooksByGenre(Genre genre);
        List<Book> advancedSearch(Book book);
        List<Book> simpleSearch(String query);
        List<Book> getBooksComplexByParams(Integer genreId, String title, String authors, String publisher);
}
