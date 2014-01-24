package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.helper.AdvancedSearchQuery;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
import com.ch018.library.helper.SearchParams;
import com.ch018.library.helper.SimpleSearchQuery;



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
        List<Book> getBooksComplex(SimpleSearchQuery searchQuery, SearchParams searchParams);
        List<Book> getBooksByPublisher(String publisher);
        List<Book> getBooksByPagesEq(int pages);
        List<Book> getBooksByGenre(Genre genre);
        List<Book> advancedSearch(Book book);
        List<Book> simpleSearch(String query);
        List<Book> getBooksComplexByParams(AdvancedSearchQuery advancedSearchQuery, SearchParams searchParams);
}
