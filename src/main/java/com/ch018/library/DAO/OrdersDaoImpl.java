package com.ch018.library.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class OrdersDaoImpl implements OrdersDao{

        static Logger log = LogManager.getLogger(GenreDaoImpl.class);
    
        @Autowired
        SessionFactory factory;
    
        @Override
        public void save(Orders order) {
                factory.getCurrentSession().save(order);
        }

        @Override
        public void delete(Orders order){
                factory.getCurrentSession().delete(order);}

        @Override
        public void update(Orders order){
                factory.getCurrentSession().update(order);
        }

        @Override
        public List<Orders> getAll(){
                return factory.getCurrentSession().createCriteria(Orders.class).list();
                
        }

        @Override //Unchecked
        public List<Orders> getOrderByPerson(Person person){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("person", person)).list();
                
        }

        @Override
        public List<Orders> getOrderByBook(Book book){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("book", book)).list();
        }

        @Override
        public List<Orders> getOrderByDate(Date date){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("date", date)).list();      
        }

        @Override
        public Orders getOrderByID(int id) {
                return (Orders) factory.getCurrentSession().load(Orders.class, id);
               
        }

        
}