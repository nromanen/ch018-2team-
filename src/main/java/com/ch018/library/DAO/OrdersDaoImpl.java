package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

		private final Logger logger = LoggerFactory.getLogger(OrdersDaoImpl.class);
	
		@Autowired
		private SessionFactory factory;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private PersonDao personDao;
	
		@Override
		public void save(Orders order) {
			try {
				factory.getCurrentSession().save(order);
			} catch (Exception e) {
				logger.error("during save order {}", order);
			}
	
		}
	
		@Override
		public void delete(Orders order) {
			try {
				factory.getCurrentSession().delete(order);
			} catch (Exception e) {
				logger.error("during delete order {}, {}", order, e.getMessage());
			}
		}
	
		@Override
		public void update(Orders order) {
	
			try {
				factory.getCurrentSession().update(order);
			} catch (Exception e) {
				logger.error("error during update order {}", e.getMessage());
			}
	
		}
	
		@Override
		public void update(int id, Date newDate) {
			Orders order = (Orders) factory.getCurrentSession().get(Orders.class,
					id);
			order.setOrderDate(newDate);
		}
	
		@Override
		public List<Orders> getAll() {
			return factory.getCurrentSession().createCriteria(Orders.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}
	
		@Override
		public List<Orders> getOrderByPerson(Person person) {
			return factory.getCurrentSession().createCriteria(Orders.class)
					.add(Restrictions.eq("person", person)).list();
	
		}
	
		@Override
		public List<Orders> getOrderByBook(Book book) {
			try {
				List<Orders> orders = factory.getCurrentSession()
						.createCriteria(Orders.class)
						.add(Restrictions.eq("book", book))
						.addOrder(Order.asc("orderDate")).list();
				return orders == null ? new ArrayList<Orders>() : orders;
			} catch (Exception e) {
				logger.error("error in getOrderByBook {}", e.getMessage());
				return new ArrayList<>();
			}
		}
	
		@Override
		public List<Orders> getOrderByDate(Date date) {
			return factory.getCurrentSession().createCriteria(Orders.class)
					.add(Restrictions.eq("orderDate", date)).list();
		}
	
		@Override
		public Orders getOrderByID(int id) {
			return (Orders) factory.getCurrentSession()
					.createCriteria(Orders.class).add(Restrictions.eq("id", id))
					.list().get(0);
		}
	
		@Override
		public List<Orders> getOrdersToday() {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
	
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			start.set(Calendar.MILLISECOND, 0);
	
			Date startDate = start.getTime();
	
			end.add(Calendar.DAY_OF_YEAR, 1);
			end.set(Calendar.HOUR_OF_DAY, 0);
			end.set(Calendar.MINUTE, 0);
			end.set(Calendar.SECOND, 0);
			end.set(Calendar.MILLISECOND, 0);
	
			Date endDate = end.getTime();
	
			Session session = factory.getCurrentSession();
	
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.add(Restrictions.between("orderDate", startDate, endDate));
	
			List<Orders> orders = criteria.list();
	
			return orders;
		}
	
		@Override
		public List<Orders> getOrdersInHour() {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
	
			end.add(Calendar.HOUR_OF_DAY, 1);
	
			Date startDate = start.getTime();
			Date endDate = end.getTime();
	
			Session session = factory.openSession();
	
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.add(Restrictions.between("orderDate", startDate, endDate));
	
			List<Orders> orders = criteria.list();
	
			return orders;
		}
	
		@Override
		public int getBookIdByPerson(Person person) {
			return ((Orders) factory.getCurrentSession()
					.createCriteria(Orders.class)
					.add(Restrictions.eq("person", person)).list().get(0))
					.getBook().getbId();
		}
	
		@Override
		public boolean isPersonOrderedBook(Person person, Book book) {
			try {
				Orders order = (Orders) factory.getCurrentSession()
						.createCriteria(Orders.class)
						.add(Restrictions.eq("person", person))
						.add(Restrictions.eq("book", book)).list().get(0);
				return order == null ? false : true;
			} catch (Exception e) {
				return false;
			}
	
		}
	
		@Override
		public List<Orders> getOrdersForChanging(Book book, Date returnDate) {
			List<Orders> orders = new ArrayList<>();
			try {
				System.out.println("Book " + book + "return date " + returnDate);
				orders = factory.getCurrentSession().createCriteria(Orders.class)
						.add(Restrictions.eq("book", book))
						.add(Restrictions.lt("orderDate", returnDate)).list();
			} catch (Exception e) {
				logger.error("Error while getting orders to change {}",
						e.getMessage());
			}
			return orders;
		}
	
		@Override
		public List<Orders> getOrderByIDList(int id) {
	
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.add(Restrictions.eq("id", id));
			List<Orders> orderes = criteria.list();
	
			if (orderes.size() > 0) {
				System.out.println("not empty");
	
			}
	
			return orderes;
			// return (Orders)
			// factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("id",
			// id)).list().get(0);
	
		}
        @Override
        @Transactional
        public List<Orders> testCriteria(String title, String surname){
            System.out.println("fuck");
            Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
            try {
                System.out.println("pizdec");
                criteria.add(Restrictions.like("book.tile",title, MatchMode.ANYWHERE ));
                criteria.add(Restrictions.like("person.surname", surname, MatchMode.ANYWHERE));

            }
            catch (Exception e){
                System.out.println("EXCEPTION");
            }
/*            System.out.println("!!"+criteria.list());
            System.out.println("!!!"+criteria);  */
            return criteria.list();
        }
        @Override
        @Transactional
        public List<Orders> sortOrdersBySurname(){
            int pageNumber=1;
            int pageSize=10;
            Session session = factory.getCurrentSession();
            Criteria criteria = session.createCriteria(Orders.class);
            criteria.setFirstResult((pageNumber-1)*pageSize);
            criteria.setMaxResults(pageSize);
            return (List<Orders>) criteria.list();
        }

}