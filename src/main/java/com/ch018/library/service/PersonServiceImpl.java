package com.ch018.library.service;

import java.sql.SQLException;
import java.util.List;

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

}
