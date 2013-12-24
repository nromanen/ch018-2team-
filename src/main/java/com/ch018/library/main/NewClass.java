/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.main;

import com.ch018.library.DAO.BookDAO;
import com.ch018.library.DAO.BookDAOImpl;
import com.ch018.library.DAO.BooksInUseDao;
import com.ch018.library.DAO.BooksInUseDaoImpl;
import com.ch018.library.DAO.GenreDao;
import com.ch018.library.DAO.GenreDaoImpl;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;


/**
 *
 * @author admin
 */
public class NewClass {
    
    
        public static void main(String[] args){
            try{
            Session sess = HibernateUtil.getSessionFactory().openSession();
            sess.beginTransaction();
            int oid = (int) sess.createSQLQuery("select id from orders where pid = :pid and bid = :bid").
                    setInteger("pid", 2).setInteger("bid", 4).list().get(0);
            System.out.println(oid);
            Orders o = (Orders) sess.get(Orders.class, oid);
            o.setOrderDate(new Date());
                System.out.println(o);
                sess.update(o);
            }catch(Exception e){
                System.out.println(e);
            }
            
        }
    
    }

               
        
        
        
        
    
    

