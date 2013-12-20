
package com.ch018.library.service;

import java.sql.SQLException;
import java.util.List;

import com.ch018.library.entity.Person;

public interface PersonService {

	public void addPerson(Person person) throws SQLException;
	public void updatePerson(Person person) throws SQLException;
	public void deletePerson(int id) throws SQLException;
	public List<Person> getAllPersons() throws SQLException;
	public Person getPersonById(int id) throws SQLException;
}
