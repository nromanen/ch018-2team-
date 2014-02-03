/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Person;
import com.ch018.library.helper.SearchParamsPerson;

/**
 * 
 * @author Edd Arazian
 */
@Component
public class PersonDaoImpl implements PersonDao {

		private final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
		@Autowired
		private SessionFactory factory;
		
		@Autowired
		private SearchParamsPerson searchParams;
	
		@Override
		public List<Person> getPersonsBySurname(String surname) {
	
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.like("surname", "%" + surname + "%")).list();
		}
	
		@Override
		public void save(Person person) {
			try {
				factory.getCurrentSession().save(person);
			} catch (Exception e) {
				logger.error("during save person {}", person);
			}
	
		}
	
		@Override
		public List<Person> getAll() {
			return factory.getCurrentSession().createCriteria(Person.class).list();
	
		}
	
		@Override
		public int delete(int id) {
            int deleted;
            try {Query query = factory.getCurrentSession()
                    .createQuery("delete from Person where id=:id")
                    .setInteger("id", id);
                deleted = query.executeUpdate();

            }
            catch (Exception e){
                deleted=0;
                System.out.println("EXC");
                return deleted;}
            System.out.println("NO EXC");
            return deleted;
	
		}
	
		@Override
		public void update(Person person) {
			try {
				factory.getCurrentSession().update(person);
			} catch (Exception e) {
				logger.error("during update person {}, {}", person.getEmail(), e.getMessage());
			}
		}
	
		@Override
		public Person getById(int id) {
			return (Person) factory.getCurrentSession().get(Person.class, id);
		}
	
		@Override
		public Person getByEmail(String email) {
			try {
				return (Person) factory.getCurrentSession()
						.createCriteria(Person.class)
						.add(Restrictions.eq("email", email)).uniqueResult();
			} catch (Exception e) {
				logger.error("in getByEmail[Dao] {}", e.getMessage());
				return null;
			}
	
		}
	
		@Override
		public List<Person> getByName(String name) {
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.eq("name", name)).list();
		}
	
		@Override
		public List<Person> getBySurname(String surname) {
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.eq("surname", surname)).list();
	
		}
	
		@Override
		public Person getByCellPhone(String cellphone) {
	
			return (Person) factory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("cellphone", cellphone)).list().get(0);
	
		}
	
		@Override
		public List<Person> getByRole(String role) {
	
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.eq("role", role)).list();
	
		}
	
		@Override
		public List<Person> getConfirmed() {
	
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.eq("confirm", "1")).list();
	
		}
	
		@Override
		public List<Person> getSmsEnabled() {
	
			return factory.getCurrentSession().createCriteria(Person.class)
					.add(Restrictions.eq("sms", "1")).list();
	
		}
	
		@Override
		public Person getPersonById(int id) {
			return (Person) factory.getCurrentSession().load(Person.class, id);
	
		}
	
		@Override
		public List<Person> simpleSearch(String request) {
			// TODO Auto-generated method stub
			if (!request.equals("")) {
	
				Session session = factory.openSession();
	
				Criteria criteria = session.createCriteria(Person.class);
	
				Disjunction or = Restrictions.disjunction();
	
				or.add(Restrictions.like("name", request, MatchMode.ANYWHERE));
	
				or.add(Restrictions.like("surname", request, MatchMode.ANYWHERE));
	
				criteria.add(or);
	
				List<Person> users = criteria.list();
	
				return users;
	
			} else {
				return null;
			}
	
		}
	
		@Override
		public List<Person> advancedSearch(Person person) {
			// TODO Auto-generated method stub
	
			Session session = factory.openSession();
	
			Criteria criteria = session.createCriteria(Person.class);
	
			if (!person.getName().equals("")) {
				criteria.add(Restrictions.like("name", person.getName(), MatchMode.ANYWHERE));
			}
	
			if (!person.getSurname().equals("")) {
				criteria.add(Restrictions.like("surname", person.getSurname(), MatchMode.ANYWHERE));
			}
	
			if (!person.getEmail().equals("")) {
				criteria.add(Restrictions.like("email", person.getEmail(), MatchMode.ANYWHERE));
			}
	
			if (!person.getCellphone().equals("")) {
				criteria.add(Restrictions.like("cellphone", person.getCellphone(), MatchMode.ANYWHERE));
			}
	
			List<Person> users = criteria.list();
	
			return users;
		}
	
		@Override
		public Person getPersonByKey(String key) {
			try {
				
				return (Person) factory.getCurrentSession()
						.createCriteria(Person.class)
						.add(Restrictions.eq("mailKey", key)).uniqueResult();
			} catch (Exception e) {
				logger.error("error during mailkey {} search", key);
				return null;
			}
	
		}

		@Override
		public List<Person> orderByName() {
			
			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.addOrder(Order.asc("name"));
			
			List<Person> users = criteria.list();
			
			for (Person person : users) {
				System.out.println(person.getName());
			}
			
			return users;
		}

		@Override
		public List<Person> orderBySurname() {

			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.addOrder(Order.asc("surname"));
			
			List<Person> users = criteria.list();
			
			for (Person person : users) {
				System.out.println(person.getName());
			}
			
			return users;
		}

		@Override
		public List<Person> orderByRating() {

			Session session = factory.openSession();
			Criteria criteria = session.createCriteria(Person.class);
			criteria.addOrder(Order.asc("generalRating"));
			
			List<Person> users = criteria.list();
			
			for (Person person : users) {
				System.out.println(person.getName());
			}
			
			return users;
		}

		@Override
		public List<Person> getPersonsBySessionParams() {
			logger.info("person dao search {}", searchParams);
			String query;
			Criteria criteria = factory.getCurrentSession().createCriteria(
					Person.class);
			
			if(searchParams.getEmail() != null) {
				query = "%" + searchParams.getEmail() + "%";
				criteria.add(Restrictions.like("email", query));
			}
			
			if(searchParams.getName() != null) {
				query = "%" + searchParams.getName() + "%";
				criteria.add(Restrictions.like("name", query));
			}
			
			if(searchParams.getSurname() != null) {
				query = "%" + searchParams.getSurname() + "%";
				criteria.add(Restrictions.like("surname", query));
			}

			if (searchParams.getOrder())
				criteria.addOrder(Order.desc(searchParams.getOrderField()));
			else
				criteria.addOrder(Order.asc(searchParams.getOrderField()));
			
			setBorders(criteria);
			
			return criteria.list();
		}
		
		private void setBorders(Criteria criteria) {
			int pageNum = searchParams.getPage();
			int pageSize = searchParams.getPageSize();
			int itemsQuantity = criteria.list().size();
			int quantity = (int) Math.ceil((double) itemsQuantity / pageSize);
			if(quantity == 0)
				quantity = 1;
			searchParams.setPagesQuantity(quantity);
			if(pageNum > quantity) {
				pageNum = 1;
				searchParams.setPage(pageNum);
			}
			
			int end = pageNum * pageSize;
			int start = end - pageSize;
			
			criteria.setFirstResult(start).setMaxResults(end);
		}
		
}


