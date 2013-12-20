/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

//import com.ch018.library.dao.PersonDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edd Arazian
 */
@Service
public class PersonServiceImpl implements PersonService {
    
    @Autowired
    PersonDao pDao;

    @Override
    @Transactional
    public void save(Person person) {
        pDao.save(person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        pDao.delete(id);
    }

    @Override
    @Transactional
    public void update(Person person) {
        pDao.update(person);
    }

    @Override
    @Transactional
    public List<Person> getAll() {
        return pDao.getAll();
    }

    @Override
    @Transactional
    public Person getById(int id) {
        return pDao.getById(id);
    }

    @Override
    @Transactional
    public Person getByEmail(String email) {
        return pDao.getByEmail(email);
    }

    @Override
    @Transactional
    public List<Person> getByName(String name) {
        return pDao.getByName(name);
    }

    @Override
    @Transactional
    public List<Person> getBySurname(String surname) {
        return pDao.getBySurname(surname);
    }

    @Override
    @Transactional
    public Person getByCellPhone(String cellphone) {
        return pDao.getByCellPhone(cellphone);
    }

    @Override
    @Transactional
    public List<Person> getByRole(String role) {
        return pDao.getByRole(role);
    }

    @Override
    @Transactional
    public List<Person> getConfirmed() {
        return pDao.getConfirmed();
    }

    @Override
    @Transactional
    public List<Person> getSmsEnabled() {
        return pDao.getConfirmed();
    }
    
    
    
}

