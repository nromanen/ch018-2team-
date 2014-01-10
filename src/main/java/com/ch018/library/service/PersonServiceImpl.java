package com.ch018.library.service;

//import com.ch018.library.dao.PersonDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Edd Arazian
 */
@Service
public class PersonServiceImpl implements PersonService {
    
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    
    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Override
    @Transactional
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    @Transactional
    public void delete(int id) {
        personDao.delete(id);
    }

    @Override
    @Transactional
    public void update(Person person) {
        personDao.update(person);
    }

    @Override
    @Transactional
    public List<Person> getAll() {
        return personDao.getAll();
    }

    @Override
    @Transactional
    public Person getById(int id) {
        return personDao.getById(id);
    }

    @Override
    @Transactional
    public Person getByEmail(String email) {
        return personDao.getByEmail(email);
    }

    @Override
    @Transactional
    public List<Person> getByName(String name) {
        return personDao.getByName(name);
    }

    @Override
    @Transactional
    public List<Person> getBySurname(String surname) {
        return personDao.getBySurname(surname);
    }

    @Override
    @Transactional
    public Person getByCellPhone(String cellphone) {
        return personDao.getByCellPhone(cellphone);
    }

    @Override
    @Transactional
    public List<Person> getByRole(String role) {
        return personDao.getByRole(role);
    }

    @Override
    @Transactional
    public List<Person> getConfirmed() {
        return personDao.getConfirmed();
    }

    @Override
    @Transactional
    public List<Person> getSmsEnabled() {
        return personDao.getConfirmed();
    }

    @Override
    @Transactional
    public boolean updatePassword(String oldPass, String newPass, String reNewPass, Principal principal) {
        Person person = personDao.getByEmail(principal.getName());
        if (!encoder.matches(oldPass, person.getPassword()) || !newPass.equals(reNewPass)) {
            logger.error("passwords don't match during update");
            return false;
        }
        Authentication auth = new PreAuthenticatedAuthenticationToken(person.getEmail(), encoder.encode(newPass));
        person.setPassword(encoder.encode(newPass));
        SecurityContextHolder.getContext().setAuthentication(auth);
        logger.error("passwords for {} changed", person.getEmail());
        return true;
    }

    @Override
    @Transactional
    public String updateField(String fieldName, String fieldValue) {
        Person person = personDao.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (fieldName.equals("name")) {
            person.setName(fieldValue);
            update(person);
            return person.getName();
        } else if (fieldName.equals("surname")) {
            person.setSurname(fieldValue);
            update(person);
            return person.getSurname();
        } else if (fieldName.equals("phone")) {
            person.setCellphone(fieldValue);
            update(person);
            return person.getCellphone();
        } else {
            return null;
        }
        
    }

	@Override
	public List<Person> simpleSearch(String request) {
		return personDao.simpleSearch(request);
	}

	@Override
	public List<Person> advancedSearch(Person person) {
		return personDao.advancedSearch(person);
	}

	@Override
	@Transactional
	public Person countRating(Person person) {
		int returnedInTime, returnedNotInTime; 
		double grade = 0;
		int booksOnHands, gradeInt = 0;
		returnedInTime = person.getTimelyReturn();
		returnedNotInTime = person.getUntimekyReturn();
		if((returnedInTime > 0) || (returnedNotInTime > 0) ){
			grade = (double) returnedInTime/(returnedNotInTime + returnedInTime);
			grade *= 100;
			gradeInt = (int) grade;
			}
		person.setGeneralRating(gradeInt);
		update(person);
		return person;
	}
}

