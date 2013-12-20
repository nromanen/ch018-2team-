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
    public void delete(int id) {
        pDao.delete(id);
    }

    @Override
    public void update(Person person) {
        pDao.update(person);
    }

    @Override
    public List<Person> getAll() {
        return pDao.getAll();
    }
>>>>>>> master

import org.springframework.stereotype.Service;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.DAO.PersonDaoImpl;
import com.ch018.library.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	private PersonDao personDAO = new PersonDaoImpl();

	@Override
	public void addPerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		personDAO.save(person);
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		// TODO Auto-generated method stub
		personDAO.update(person);
	}

	@Override
	public void deletePerson(int id) throws SQLException {
		// TODO Auto-generated method stub
		personDAO.delete(id);
	}

	@Override
	public List<Person> getAllPersons() throws SQLException {
		// TODO Auto-generated method stub
		return personDAO.getAll();
	}

	@Override
	public Person getPersonById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return personDAO.getById(id);
	}

<<<<<<< HEAD
=======
    @Override
    public List<Person> getSmsEnabled() {
        return pDao.getConfirmed();
    }
    
    
    
>>>>>>> master
}

