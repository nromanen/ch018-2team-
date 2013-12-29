/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.main;

import java.sql.SQLException;
import java.util.Date;

import com.ch018.library.DAO.BookDaoImpl;
import com.ch018.library.DAO.PersonDaoImpl;
import com.ch018.library.entity.Book;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseServiceImpl;
import com.ch018.library.service.BookServiceImpl;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.PersonServiceImpl;
import com.ch018.library.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author admin
 */
public class NewClass {
    

    public static void main(String[] args){
    	
        
        //BookInUseServiceImpl use = new BookInUseServiceImpl();
        
        Person person = new PersonDaoImpl().getByEmail("user2@mail.com");
        
        
        //List<BooksInUse> uses = use.getBooksInUseByPerson(person);
        System.out.println(person);//uses.get(0).getPerson().getBooksInUse());
        
    }
    
}
