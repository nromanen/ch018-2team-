package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;

    @Override
    @Transactional
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookDAO.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthors(String authors) {
        return bookDAO.getBooksByAuthors(authors);
    }

    @Override
    public List<Book> getBooksByYear(int year) {
        return bookDAO.getBooksByYear(year);
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return bookDAO.getBooksByPublisher(publisher);
    }

    @Override
    public List<Book> getBooksByPagesEq(int pages) {
        return bookDAO.getBooksByPagesEq(pages);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDAO.getBooksByGenre(genre);
    }
	
        
        
        

	

}
