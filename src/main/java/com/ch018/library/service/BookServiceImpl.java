package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDAO;

    @Override
    @Transactional
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    @Transactional
    public Book getBookById(int id) {
        System.out.println("+++++++++++++====================+++++++++++++++++");
        return bookDAO.getBookById(id);
    }

    @Override
    @Transactional
    public List<Book> getBooksByTitle(String title) {
        return bookDAO.getBooksByTitle(title);
    }

    @Override
    @Transactional
    public List<Book> getBooksByAuthors(String authors) {
        return bookDAO.getBooksByAuthors(authors);
    }

    @Override
    @Transactional
    public List<Book> getBooksByYear(int year) {
        return bookDAO.getBooksByYear(year);
    }

    @Override
    @Transactional
    public List<Book> getBooksByPublisher(String publisher) {
        return bookDAO.getBooksByPublisher(publisher);
    }

    @Override
    @Transactional
    public List<Book> getBooksByPagesEq(int pages) {
        return bookDAO.getBooksByPagesEq(pages);
    }

    @Override
    @Transactional
    public List<Book> getBooksByGenre(Genre genre) {
        return bookDAO.getBooksByGenre(genre);
    }

    @Override
    @Transactional
    public List<Book> getBooksComplex(String query) {
        return bookDAO.getBooksComplex(query);
    }

}

