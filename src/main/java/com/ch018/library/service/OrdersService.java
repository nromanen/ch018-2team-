package com.ch018.library.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

public interface OrdersService {

	public void addNewOrder(Orders order) throws SQLException;
	public void deleteOrder(Orders order) throws SQLException;
	public void updateOrder(Orders order) throws SQLException;
	public List<Orders> getAllOrders() throws SQLException;
	public List<Orders> getOrderByPerson(Person person) throws SQLException;
	public List<Orders> getOrderByBook(Book book) throws SQLException;
	public List<Orders> getOrderByDate(Date date) throws SQLException;
	public Orders getOrderByID(int id) throws SQLException; 
}
