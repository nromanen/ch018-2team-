package com.ch018.library.DAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

public interface OrdersDao {

        public void save(Orders order);
        public void delete(Orders order);
        public void update(int id, Orders order);
        public List<Orders> getAll();
        public List<Orders> getOrderByPerson(Person person);
        public List<Orders> getOrderByBook(Book book);
        public List<Orders> getOrderByDate(Date date);
        public Orders getOrderIdByPersonIdBookId(int pId, int bId);
        public Orders getOrderByID(int id);
        
}
