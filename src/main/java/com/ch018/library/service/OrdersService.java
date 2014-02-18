package com.ch018.library.service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.OrderDays;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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
         Date getMinOrderDate(Book book) throws Exception;
         int getCorrectAmountOfOrderDays(Book book, Date orderDate) throws Exception;
         void addOrder(Person person, int bookId, Date orderDate) throws Exception;
         Orders editOrder(Person person, int orderId, Date orderDate) throws Exception;
         void issue(Orders order, int term);
         List<Orders> getOrdersByBookTitle(List<Orders> orders, String title);
         List<Orders> getOrdersByPersonSurname(List<Orders> orders,String surname);
         List<Orders> getOrdersByOrdersId(List<Orders> orders,int id);
         List<Orders> sortOrdersBySurname();
         List<Orders> getOrdersForPeriodFromMonth(Book book, Date date);

}
