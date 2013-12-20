/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;


import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edd Arazian
 */
@Component
public class PersonDaoImpl implements PersonDao {

 
    @Autowired
    SessionFactory factory;
    
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
        
           return (Person) factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("email", email)).
                    list().get(0);
            
       
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
        
            return factory.getCurrentSession().createCriteria(Person.class).add(Restrictions.eq("role", role)).
                    list();
           
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

    
    
    
    
}
