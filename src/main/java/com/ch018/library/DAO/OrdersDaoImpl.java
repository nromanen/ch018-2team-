package com.ch018.library.DAO;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
				factory.getCurrentSession().save(order);

			
	
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
		public List<Orders> getAll() {
			return factory.getCurrentSession().createCriteria(Orders.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setFirstResult(0).setMaxResults(10).list();
		}
	
		@Override
		public List<Orders> getOrderByPerson(Person person) {
			return factory.getCurrentSession().createCriteria(Orders.class).add(Restrictions.eq("person", person)).list();
	
		}
	
		@Override
		public List<Orders> getOrderByBook(Book book) {
			List<Orders> orders = new ArrayList<>();
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.eq("book", book));
			criteria.addOrder(Order.asc("orderDate"));
			try {
				orders = criteria.list();	
			} catch (Exception e) {
				logger.error("error in getOrderByBook {}", e.getMessage());
			}
			return orders;
		}
	
		@Override
		public List<Orders> getOrderByDate(Date date) {
			return factory.getCurrentSession().createCriteria(Orders.class)
					.add(Restrictions.eq("orderDate", date)).list();
		}
	
		@Override
		public Orders getOrderByID(int id) {
			return (Orders) factory.getCurrentSession()
					.createCriteria(Orders.class).add(Restrictions.eq("id", id)).uniqueResult();
		}
	
		@Override
		public List<Orders> getOrdersToday() {
			Date[] dates = formTodayStartEndTime();
			Date startDate = dates[0];
			Date endDate = dates[1];
			
			Session session = factory.getCurrentSession();
	
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.add(Restrictions.between("orderDate", startDate, endDate));
	
			List<Orders> orders = criteria.list();
	
			return orders;
		}
		
		@Override
		public List<Orders> getOrdersTodayWithoutPerson(Book book, Person person) {
			
			Date[] dates = formTodayStartEndTime();
			Date startDate = dates[0];
			Date endDate = dates[1];
	
			Session session = factory.getCurrentSession();
	
			Criteria criteria = session.createCriteria(Orders.class);
			criteria.add(Restrictions.eq("book", book));
			criteria.add(Restrictions.ne("person", person));
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
	
			Session session = factory.getCurrentSession();
	
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
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.eq("person", person));
			criteria.add(Restrictions.eq("book", book));
			try {
				Orders order = (Orders) criteria.uniqueResult();
				return order == null ? false : true;
			} catch (Exception e) {
				logger.error(e.getMessage());
				
			}
			return false;
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
        public List<Orders> testCriteria(String title, String surname, int page, int count){


            boolean and = false;
            System.out.println("input data: title:"+title+", surname:"+surname+".");
            if (title.compareTo("Title")==0 && surname.compareTo("Surname")==0) {
                Query query = factory.getCurrentSession().createQuery("FROM Orders");
                query.setFirstResult((page-1)*count);
                query.setMaxResults(count);
                return query.list();
            }
                StringBuilder QUERY = new StringBuilder("From Orders where ");
                if (title.compareTo("Title")!=0) {
                    System.out.println("ttl");
                                                    if (and) QUERY.append(" and ");
                                                    else and=true;
                                                    QUERY.append("book.title like "+"'%"+title+"%'");

                }
                if (surname.compareTo("Surname")!=0) {
                    System.out.println("srnm");
                                                    if (and) QUERY.append(" and ");
                                                    else and=true;
                                                    QUERY.append("person.surname like "+"'%"+surname+"%'");

                }
            System.out.println("!!!"+QUERY.toString());
                Query query= factory.getCurrentSession().createQuery(QUERY.toString());
            query.setFirstResult((page-1)*count);
            query.setMaxResults(count);
            return query.list();
        
        }

    @Override
    @Transactional
    public List<Orders> testCriteria(String title, String surname,String how, String what,int page,int count){
        boolean and = false;

        if (what.compareTo("")!=0){
        StringBuilder QUERY = new StringBuilder("From Orders ");
        if (title.compareTo("Title")==0 && surname.compareTo("Surname")==0) {
            QUERY.append("order by "+what+" "+how);
            System.out.println("!!!" + QUERY.toString());
            Query query= factory.getCurrentSession().createQuery(QUERY.toString());
            query.setFirstResult((page-1)*count);
            query.setMaxResults(count);

            return query.list();
        }
        else QUERY.append("where ");
        if (title.compareTo("Title")!=0) {

            if (and) QUERY.append(" and ");
            else and=true;
            QUERY.append("book.title like "+"'%"+title+"%'");

        }
        if (surname.compareTo("Surname")!=0) {

            if (and) QUERY.append(" and ");
            else and=true;
            QUERY.append("person.surname like "+"'%"+surname+"%'");


        }

        QUERY.append("order by "+what+" "+how);
        System.out.println("!!!"+QUERY.toString());
        Query query= factory.getCurrentSession().createQuery(QUERY.toString());
        query.setFirstResult((page-1)*count);
        query.setMaxResults(count);



        return query.list();}
        else {

            return  testCriteria(title,surname,page,count);
        }


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
            return criteria.list();
        }

		@Override
		public List<Orders> getOrdersBetweenDatesWithoutPerson(Person person, Book book, Date firstDate, Date secondDate) {
 
			Calendar tmpDate = Calendar.getInstance();
			tmpDate.setTime(firstDate);
			tmpDate.set(Calendar.DATE, tmpDate.getActualMaximum(Calendar.DAY_OF_MONTH));
			Date endOfFirstMonth = tmpDate.getTime();
			
			logger.info("first {} end {} second {}", firstDate, endOfFirstMonth, secondDate);
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.ne("person", person));
			criteria.add(Restrictions.eq("book", book));
			
			Criterion returnDateExp = Restrictions.between("returnDate", firstDate, endOfFirstMonth);
			Criterion orderDateExp = Restrictions.between("orderDate", firstDate, secondDate);
			
			criteria.add(Restrictions.or(returnDateExp, orderDateExp));
			
			criteria.addOrder(Order.asc("orderDate"));
			
			List<Orders> orders = criteria.list();
			
			logger.info("Ordersss = {}", orders);
			
			return orders;
			
		}
		@Override
        public List<Orders> getOrdersByDate() throws Exception{
            /*System.out.println("fuck u");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String Date = "2014-02-24 11:00:00";
            String date1 =  new SimpleDateFormat("-MM-dd").format(Date);
            System.out.println("fuckin' date: "+date1);*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String Date = "2014-02-24";
            java.util.Date dateFrom = simpleDateFormat.parse(Date);
            java.util.Date dateTo = null;// = dateFrom.getDay();
            dateTo.setDate(dateFrom.getDate()+1);
            System.out.println("DATE:"+dateTo+"!"+"DATE FROM:"+dateFrom+"!");
            Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
            //criteria.add(Restrictions.eq("orderDate",));
            System.out.println("!!!"+criteria.list());
            return criteria.list();
        }
		

		@Override
		public long getOrdersCountForBook(Book book) {
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.eq("book", book));
			criteria.setProjection(Projections.rowCount());
			
			return (long) criteria.uniqueResult();
		}

		@Override
		public long getOrdersCountWithoutPerson(Book book, Person person) {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.ne("person", person));
			criteria.add(Restrictions.eq("book", book));
			criteria.setProjection(Projections.rowCount());
			
			return (long) criteria.uniqueResult();

		}
		
		
		@Override
		public long getOrdersCountForPerson(Person person) {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.eq("person", person));
			criteria.setProjection(Projections.rowCount());
			
			return (long) criteria.uniqueResult();
		}

		@Override
		public Orders getFirstOrderAfterDateWithoutPerson(Date date, Person person, Book book) {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.ne("person", person));
			criteria.add(Restrictions.eq("book", book));
			criteria.addOrder(Order.asc("orderDate"));
			criteria.add(Restrictions.gt("orderDate", date));
			criteria.setFirstResult(0).setMaxResults(1);

			Orders order = (Orders) criteria.uniqueResult();
			
			return order;
			
			
		}
		
		
		
		@Override
		public Orders getFirstOrderAfterDate(Date date, Book book) {
			Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
			criteria.add(Restrictions.eq("book", book));
			criteria.addOrder(Order.asc("orderDate"));
			criteria.add(Restrictions.gt("orderDate", date));
			criteria.setFirstResult(0).setMaxResults(1);

			Orders order = (Orders) criteria.uniqueResult();
			
			return order;
		}

		private Date[] formTodayStartEndTime() {
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
			
			return new Date[]{startDate, endDate};
		}
    @Override
    public List<Orders> getAllPagin(int n){
        Criteria criteria = factory.getCurrentSession().createCriteria(Orders.class);
        criteria.setFirstResult(0);
        criteria.setMaxResults(n);
        return criteria.list();
    }
			
}
