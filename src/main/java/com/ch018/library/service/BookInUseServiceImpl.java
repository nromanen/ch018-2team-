/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.DAO.BooksInUseDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edd Arazian
 */
@Service
public class BookInUseServiceImpl implements BookInUseService {

    @Autowired
    BooksInUseDao useDao;
    
    @Override
    @Transactional
    public void save(BooksInUse booksInUse) {
        useDao.save(booksInUse);
    }

    @Override
    @Transactional
    public void delete(BooksInUse booksInUse) {
        useDao.delete(booksInUse);
    }

    @Override
    @Transactional
    public void update(BooksInUse booksInUse) {
        useDao.update(booksInUse);
    }

    @Override
    @Transactional
    public List<BooksInUse> getAll() {
        return useDao.getAll();
    }

    @Override
    @Transactional
    public List<BooksInUse> getBooksInUseByPerson(Person person) {
        return useDao.getBooksInUseByPerson(person);
    }

    @Override
    @Transactional
    public List<BooksInUse> getBooksInUseByBook(Book book) {
        return useDao.getBooksInUseByBook(book);
    }

    @Override
    @Transactional
    public List<BooksInUse> getBooksInUseByIssueDate(Date issue) {
        return useDao.getBooksInUseByIssueDate(issue);
    }

    @Override
    @Transactional
    public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
        return useDao.getBooksInUseByReturnDate(returnDate);
    }

    @Override
    @Transactional
    public Date getBookWithLastDate(Book book){
        
        return useDao.getBookWithLastDate(book);
         
    }
    
    
    
    
}
