package com.ch018.library.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.DAO.OrdersDao;
import com.ch018.library.DAO.OrdersDaoImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersServiceImpl implements OrdersService{

        @Autowired
        OrdersDao ordersDao;
        
        @Override
        @Transactional
        public void save(Orders order){
                // TODO Auto-generated method stub
                ordersDao.save(order);
        }

        @Override
        @Transactional
        public void delete(Orders order){
                // TODO Auto-generated method stub
                ordersDao.delete(order);
        }

        @Override
        @Transactional
        public void update(int id, Orders order){
                // TODO Auto-generated method stub
                ordersDao.update(id, order);
        }

        @Override
        @Transactional
        public List<Orders> getAll(){
                // TODO Auto-generated method stub
                return ordersDao.getAll();
        }

        @Override
        @Transactional
        public List<Orders> getOrderByPerson(Person person){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByPerson(person);
        }

        @Override
        @Transactional
        public List<Orders> getOrderByBook(Book book){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByBook(book);
        }

        @Override
        @Transactional
        public List<Orders> getOrderByDate(Date date){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByDate(date);
        }

        @Override
        @Transactional
        public Orders getOrderByID(int id){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByID(id);
        }

        @Override
        @Transactional
        public Orders getOrderIdByPersonIdBookId(int pId, int bId) {
                return ordersDao.getOrderIdByPersonIdBookId(pId, bId);
        }
        
        

}