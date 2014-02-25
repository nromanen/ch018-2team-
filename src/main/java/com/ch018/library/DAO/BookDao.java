package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.stereotype.Repository;

import java.util.List;



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
        List<Book> getBooksByPublisher(String publisher);
        List<Book> getBooksByPagesEq(int pages);
        List<Book> getBooksByGenre(Genre genre);
        List<Book> advancedSearch(Book book);
        List<Book> simpleSearch(String query);
        Integer getMinIntegerField(String field);
        Integer getMaxIntegerField(String field);
        List<Book> getLastByField(String field, int quantity);
        List<Book> getBooksFromRecommendedList(List<RecommendedItem> items);
        long getBooksCount();
        List<Book> hqlSearch(String title,String year,String pages,String shelf,String cq,String gq,String how, String what,int page,int count);
        
}
