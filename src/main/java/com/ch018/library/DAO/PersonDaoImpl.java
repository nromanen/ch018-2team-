/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 *
 * @author Edd Arazian
 */
@Component
public class PersonDaoImpl implements PersonDao {

    private final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    @Override
    public List<Person> getPersonsBySurname(String surname){

        return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.like("surname", "%" + surname + "%")).list();
    }


    @Override
    public void save(Person person) {
        factory.getCurrentSession().save(person);
       
    }

    @Override
    public List<Person> getAll() {
        return factory.getCurrentSession().createCriteria(Person.class).list();
           
    }

    @Override
    public void delete(int id) {
            Person person = (Person) factory.getCurrentSession().get(Person.class, id);
            factory.getCurrentSession().delete(person);
            
    }

    @Override
    public void update(Person person) {
        
            factory.getCurrentSession().update(person);
    }

    @Override
    public Person getById(int id) {
        
            return (Person) factory.getCurrentSession().get(Person.class, id);
           
    }

    @Override
    public Person getByEmail(String email) {
        try {
          return (Person) factory.getCurrentSession().createCriteria(Person.class)
                   .add(Restrictions.eq("email", email)).uniqueResult(); 
        } catch (Exception e) {
            logger.error("in getByEmail[Dao] {}", e.getMessage());
            return null;
        }
       
    }

    @Override
    public List<Person> getByName(String name) {
        return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("name", name)).
                    list();
    }

    @Override
    public List<Person> getBySurname(String surname) {
        return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("surname", surname)).
                    list();
           
    }

    @Override
    public Person getByCellPhone(String cellphone) {
        
            return (Person) factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("cellphone", cellphone)).
                    list().get(0);
            
    }

    @Override
    public List<Person> getByRole(String role) {
        
            return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("role", role)).list();
           
    }

    @Override
    public List<Person> getConfirmed() {
        
            return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("confirm", "1")).
                    list();
           
    }

    @Override
    public List<Person> getSmsEnabled() {
        
            return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("sms", "1")).
                    list();
           
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
		
		if(!person.getName().equals("")) {
		criteria.add(Restrictions.eq("name", person.getName()));
		}
		
		if(!person.getSurname().equals("")) {
		criteria.add(Restrictions.eq("surname", person.getSurname()));
		}
		
		if(!person.getEmail().equals("")) {
		criteria.add(Restrictions.eq("email", person.getEmail()));
		}
		
		if(!person.getCellphone().equals("")) {
		criteria.add(Restrictions.eq("cellphone", person.getCellphone()));
		}
		
		List<Person> users = criteria.list();
		
		return users;
	}

        @Override
        public Person getPersonByKey(String key) {
            try {
                return (Person) factory.getCurrentSession().createCriteria(Person.class)
                        .add(Restrictions.eq("mailKey", key)).uniqueResult();
            } catch(Exception e) {
                logger.error("error during mailkey {} search", key);
                return null;
            }
            
        }

        
        
        
    
    
}
