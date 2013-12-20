/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.main;

import java.sql.SQLException;
import java.util.Date;

import com.ch018.library.DAO.BookDAOImpl;
import com.ch018.library.DAO.OrdersDAOImpl;
import com.ch018.library.DAO.PersonDaoImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;


/**
 *
 * @author admin
 */
public class NewClass {
    
    public static void main(String[] args) throws SQLException {
    	
    	BookDAOImpl bookDAO = new BookDAOImpl();
    	PersonDaoImpl personDAO = new PersonDaoImpl();
    	OrdersDAOImpl ordersDAO = new OrdersDAOImpl();
    	
    	Book book = new Book();
    	Person person = new Person();
    	Orders order = new Orders();
    	Date date = new Date();
    	
        book.setTitle("Good Code");
        bookDAO.addBook(book);
        
        person.setName("Reader");
        personDAO.save(person);
        
        order.setBook(book);
        order.setPerson(person);
        order.setDate(date);
        ordersDAO.addNewOrder(order);
        
    }
    
}
