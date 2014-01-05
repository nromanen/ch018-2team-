package com.ch018.library.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

public interface OrdersDao {


        void save(Orders order);
        void delete(Orders order);
        void update(int id, Date newDate);
        List<Orders> getAll();
        List<Orders> getOrderByPerson(Person person);
        List<Orders> getOrderByBook(Book book);
        List<Orders> getOrderByDate(Date date);
        Orders getOrderIdByPersonIdBookId(int pId, int bId);
        Orders getOrderByID(int id);
        List<Orders> search(String request);
        List<Orders> getOrdersToday();
        List<Orders> getOrdersInHour();

        
        int getBookIdByPerson(Person person);

        
}
