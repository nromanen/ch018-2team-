package com.ch018.library.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import org.springframework.stereotype.Repository;
@Repository
public interface OrdersDao {


        void save(Orders order);
        void delete(Orders order);
        void update(int id, Date newDate);
        void update(Orders order);
        List<Orders> getAll();
        List<Orders> getOrderByPerson(Person person);
        List<Orders> getOrderByBook(Book book);
        List<Orders> getOrderByDate(Date date);
        Orders getOrderByID(int id);
        List<Orders> getOrdersToday();
        List<Orders> getOrdersInHour();
        int getBookIdByPerson(Person person);
        boolean isPersonOrderedBook(Person person, Book book);
        List<Orders> getOrdersForChanging(Book book, Date returnDate);       
}
