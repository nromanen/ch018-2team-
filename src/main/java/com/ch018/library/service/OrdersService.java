package com.ch018.library.service;


import java.util.Date;
import java.util.List;
import com.ch018.library.DAO.OrdersDao;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

public interface OrdersService {

        public void save(Orders order);
        public void delete(Orders order);
        public void update(Orders order);
        public List<Orders> getAll();
        public List<Orders> getOrderByPerson(Person person);
        public List<Orders> getOrderByBook(Book book);
        public List<Orders> getOrderByDate(Date date);
        public Orders getOrderByID(int id); 

}
