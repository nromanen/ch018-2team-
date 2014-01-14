package com.ch018.library.service;


import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.OrderDays;
import java.util.Map;
import org.springframework.stereotype.Service;
/**
 *
 * @author Edd Arazain
 */
@Service
public interface OrdersService {
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
         boolean isLimitReached(Person person);
         void checkPersonOrders(Book book, Date returnDate);
         void issue(Orders order);
         OrderDays getMinOrderDate(Book book);
         OrderDays checkOrderDate(Book book, Date orderDate) throws Exception ;
}
