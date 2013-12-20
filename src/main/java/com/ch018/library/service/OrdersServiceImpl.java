package com.ch018.library.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.DAO.OrdersDAOImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

@Service
public class OrdersServiceImpl implements OrdersService{

	private OrdersDAO ordersDAO = new OrdersDAOImpl();
	
	@Override
	public void addNewOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		ordersDAO.addNewOrder(order);
	}

	@Override
	public void deleteOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		ordersDAO.deleteOrder(order);
	}

	@Override
	public void updateOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		ordersDAO.updateOrder(order);
	}

	@Override
	public List<Orders> getAllOrders() throws SQLException {
		// TODO Auto-generated method stub
		return ordersDAO.getAllOrders();
	}

	@Override
	public List<Orders> getOrderByPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		return ordersDAO.getOrderByPerson(person);
	}

	@Override
	public List<Orders> getOrderByBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return ordersDAO.getOrderByBook(book);
	}

	@Override
	public List<Orders> getOrderByDate(Date date) throws SQLException {
		// TODO Auto-generated method stub
		return ordersDAO.getOrderByDate(date);
	}

	@Override
	public Orders getOrderByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		return ordersDAO.getOrderByID(id);
	}

}
