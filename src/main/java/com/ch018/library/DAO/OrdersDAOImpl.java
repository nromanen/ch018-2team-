package com.ch018.library.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;

@Repository
public class OrdersDAOImpl implements OrdersDAO{

	@Override
	public void addNewOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(order);
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
	}

	@Override
	public void deleteOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(order);
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
	}

	@Override
	public void updateOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(order);
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
	}

	@Override
	public List<Orders> getAllOrders() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Orders> orders = new ArrayList<Orders>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		orders = session.createCriteria(Orders.class).list();
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
		
		return orders;
	}

	@Override //Unchecked
	public List<Orders> getOrderByPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Orders> orders = new ArrayList<Orders>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		orders = session.createCriteria(Orders.class).add(Restrictions.eq("personId", person)).list();
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
		
		return orders;
	}

	@Override
	public List<Orders> getOrderByBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Orders> orders = new ArrayList<Orders>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		orders = session.createCriteria(Orders.class).add(Restrictions.eq("bookId", book)).list();
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
		
		return orders;
	}

	@Override
	public List<Orders> getOrderByDate(Date date) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Orders> orders = new ArrayList<Orders>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		orders = session.createCriteria(Orders.class).add(Restrictions.eq("date", date)).list();
		session.getTransaction().commit();
		
		if (session.isOpen()) {
			session.close();
		}
		
		return orders;	
	}

	@Override
	public Orders getOrderByID(int id) throws SQLException {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Orders order = (Orders) session.load(Orders.class, id);
		
		if (session.isOpen()) {
			session.close();
		}
		
		return order;
	}

	
}
