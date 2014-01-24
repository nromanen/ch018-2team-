/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ch018.library.entity.Person;

/**
 *
 * @author Edd Arazian
 */
@Component
public interface PersonDao {   
    void save(Person person);
    void delete(int id);
    void update(Person person);
    List<Person> getAll();
    Person getById(int id);
    Person getByEmail(String email);
    List<Person> getByName(String name);
    List<Person> getBySurname(String surname);
    Person getByCellPhone(String cellphone);
    List<Person> getByRole(String role);
    List<Person> getConfirmed();
    List<Person> getSmsEnabled();
    Person getPersonById(int id);
    List<Person> simpleSearch(String request);
    List<Person> advancedSearch(Person person); 
    Person getPersonByKey(String key);
    List<Person> getPersonsBySurname(String surname);
    List<Person> orderByName();
    List<Person> orderBySurname();
    List<Person> orderByRating();
    List<Person> pagination(int pageNumber);
    		
}
