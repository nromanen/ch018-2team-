package com.ch018.library.DAO;

import com.ch018.library.controller.OrderController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class OrdersDaoImpl implements OrdersDao{

 
        final Logger logger = LoggerFactory.getLogger(OrdersDaoImpl.class);
    
        @Autowired
        SessionFactory factory;
    
        @Override
        public void save(Orders order) {
                factory.getCurrentSession().save(order);
        }

        @Override
        public void delete(Orders order){
                factory.getCurrentSession().delete(order);}

        @Override
        public void update(int id, Date newDate){
                Orders order = (Orders) factory.getCurrentSession().get(Orders.class, id);
                order.setOrderDate(newDate);
                
                //Orders o = getOrderByID(id);
                //o.setOrderDate(order.getOrderDate());
                
               //factory.getCurrentSession().flush();
               
        }

        @Override
        public List<Orders> getAll(){
                return factory.getCurrentSession().createCriteria(Orders.class).list();
                
        }

        @Override 
        public List<Orders> getOrderByPerson(Person person){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("person", person)).list();
                
        }

        @Override
        public List<Orders> getOrderByBook(Book book){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("book", book)).list();
        }

        @Override
        public List<Orders> getOrderByDate(Date date){
                return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("date", date)).list();      
        }

        @Override
        public Orders getOrderByID(int id) {
                return (Orders) factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("id", id)).list().get(0);
               
        }

        @Override
        public Orders getOrderIdByPersonIdBookId(int pId, int bId) {
            int oid =  (int) factory.getCurrentSession().createSQLQuery("select id from orders where pid = :pid and bid = :bid").
                    setInteger("pid", pId).setInteger("bid", bId).list().get(0);
            return (Orders) factory.getCurrentSession().load(Orders.class, oid);
        }


		@Override
		public List<Orders> search(String request) {
			// TODO Auto-generated method stub
			if (!request.equals("")){
				
				Session session = factory.openSession();
				
				Query query = session.createQuery("from Orders where book like :book or"
												+ " person like :person");
				query.setParameter("book", request + "%");
				query.setParameter("person", request + "%");
				
				List<Orders> orders = query.list();
				
				return orders;
				}else {
					return null;
				}
		}

		  @Override
    public List<Orders> getOrdersToday() {
        // TODO Auto-generated method stub

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        System.out.println(start.getTime());

        end.add(Calendar.DAY_OF_YEAR, 1);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        System.out.println(end.getTime());

        Session session = factory.openSession();
        Query query = session.createQuery("from Orders where orderDate BETWEEN :start and :end");
        query.setCalendar("start", start);
        query.setCalendar("end", end);
        List<Orders> orders = query.list();

        /*Orders order = new Orders();
         order = orders.get(0);
					
         System.out.println(order.getOrderDate() + " gsdkjvbasldj akhfsadkhsn cnhfls");
         */

        return orders;
    }

    @Override
    public List<Orders> getOrdersInHour() {
        // TODO Auto-generated method stub

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        System.out.println(start.getTime() + "Start");

        end.add(Calendar.HOUR_OF_DAY, 1);

        System.out.println(end.getTime() + "end");

        Session session = factory.openSession();
        Query query = session.createQuery("from Orders where orderDate BETWEEN :start AND :end");
        query.setCalendar("start", start);
        query.setCalendar("end", end);
        List<Orders> orders = query.list();

        /*Orders order = new Orders();
         order = orders.get(0);
						
         System.out.println(order.getOrderDate() + " Order 1");
			
         order = orders.get(1);
         System.out.println(order.getOrderDate() + " Order 2");*/

        return orders;
    }



    @Override
    public int getBookIdByPerson(Person person) {
       return ((Orders) factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("person", person)).list().get(0)
                ).getBook().getbId();
    }

  
    
    @Override
    public boolean isPersonOrderedBook(Person person, Book book) {
     try{   
            Orders order = (Orders) factory.getCurrentSession().createCriteria(Orders.class)
                        .add(Restrictions.eq("person", person))
                            .add(Restrictions.eq("book", book)).list().get(0);
            return order == null ? false : true;
     }catch(Exception e){
         return false;
     }
        
    }

    @Override
    public List<Orders> getOrdersForChanging(Book book, Date returnDate) {
        List<Orders> orders = new ArrayList<>();
        try{
            System.out.println("Book " + book + "return date " + returnDate);
            orders = factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("book", book))
                    .add(Restrictions.lt("orderDate", returnDate)).list();
        }catch(Exception e){
            logger.error("Error while getting orders to change {}", e.getMessage());
        }
        return orders;
    }

    @Override
    public void update(Orders order) {
    
        try{
           factory.getCurrentSession().update(order);
        }catch(Exception e){
            logger.error("error during update order {}", e.getMessage());
        }
        
    }
    
}