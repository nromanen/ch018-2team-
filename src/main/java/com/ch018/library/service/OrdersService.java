package com.ch018.library.service;


import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import org.springframework.stereotype.Service;

@Service
public interface OrdersService {


         void save(Orders order);
         void delete(Orders order);
         void update(int id, Date newDate);
         List<Orders> getAll();
         List<Orders> getOrderByPerson(Person person);
         List<Orders> getOrderByBook(Book book);
         List<Orders> getOrderByDate(Date date);
         Orders getOrderByID(int id); 
         Orders getOrderIdByPersonIdBookId(int pId, int bId);

         List<Orders> search(String request);
         List<Orders> getOrdersToday();
         List<Orders> getOrdersInHour();

         int getBookIdByPerson(Person person);
         
         boolean isPersonOrderedBook(Person person, Book book);
         boolean isLimitReached(Person person);

}
