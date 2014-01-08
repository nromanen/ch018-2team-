package com.ch018.library.service;

//import com.ch018.library.dao.PersonDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    
    @Autowired
    PersonDao pDao;
    
    @Autowired
    BCryptPasswordEncoder encoder;
    
    @Autowired
    MyUserDetailsService userDetails;
    
    
    

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

    @Override
    @Transactional
    public boolean updatePassword(String oldPass, String newPass, String reNewPass, Principal principal) {
        Person person = pDao.getByEmail(principal.getName());
        if(!encoder.matches(oldPass, person.getPassword()) || !newPass.equals(reNewPass))
            return false;
        Authentication auth = new PreAuthenticatedAuthenticationToken(person.getEmail(), encoder.encode(newPass));
        person.setPassword(encoder.encode(newPass));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return true;
    }

    @Override
    @Transactional
    public String updateField(String fieldName, String fieldValue) {
        Person person = pDao.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(fieldName.equals("name")){
            person.setName(fieldValue);
            update(person);
            return person.getName();
        }else if(fieldName.equals("surname")){
            person.setSurname(fieldValue);
            update(person);
            return person.getSurname();
        }else if(fieldName.equals("phone")){
            person.setCellphone(fieldValue);
            update(person);
            return person.getCellphone();
        }else{
            return null;
        }
        
    }

	@Override
	public List<Person> simpleSearch(String request) {
		// TODO Auto-generated method stub
		return pDao.simpleSearch(request);
	}

	@Override
	public List<Person> advancedSearch(Person person) {
		// TODO Auto-generated method stub
		return pDao.advancedSearch(person);
	}
    
}

