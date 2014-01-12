package com.ch018.library.service;

//import com.ch018.library.dao.PersonDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.UserRegistrationForm;
import java.security.MessageDigest;
import java.security.Principal;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @Autowired
    private MailService mailService;
    
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
    public boolean updatePassword(Password password, Principal principal) {
        Person person = personDao.getByEmail(principal.getName());
        if (!encoder.matches(password.getOldPass(), person.getPassword())) {
            logger.error("passwords don't match during update");
            return false;
        }
        Authentication auth = new PreAuthenticatedAuthenticationToken(person.getEmail(), encoder.encode(password.getNewPass()));
        person.setPassword(encoder.encode(password.getNewPass()));
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
        @Transactional
	public List<Person> simpleSearch(String request) {
		return personDao.simpleSearch(request);
	}

	@Override
        @Transactional
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

        @Override
        @Transactional
        public boolean changeEmail(String email, Person person) {
            try {
                person.setEmail(email);
                update(person);
                Authentication auth = new PreAuthenticatedAuthenticationToken(email,
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return true;
            } catch (Exception e) {
                logger.error("error during email: {} change {}", email, e.getMessage());
                return false;
            }
        }

        @Override
        @Transactional
        public boolean register(UserRegistrationForm form) {
            Person person = new Person(form.getName(), form.getSurname(), form.getEmail(), form.getPassword(), form.getCellPhone());
            if(getByEmail(person.getEmail()) != null){
                return false;
            }
            person.setPassword(encoder.encode(person.getPassword()));
            person.setProle("ROLE_USER");
            person.setMultiBook(5);
            String mailKey = encoder.encode(person.getEmail().concat(String.valueOf(new Random().nextLong())));
            person.setMailKey(mailKey);
            mailService.sendConfirmationMail("springytest@gmail.com", "etenzor@gmail.com", "confirmation mail", person.getMailKey());
            save(person);
            logger.info("new user {} registered", person);
            return true;
        }

        @Override
        @Transactional
        public boolean confirmMail(String key) {
            Person person = personDao.getPersonByKey(key);
            if (person == null)
                return false;
            person.setMailConfirm(Boolean.TRUE);
            person.setMailKey(null);
            save(person);
            logger.info("person {} successfully confirm mail", person);
            return true;
        }

        @Override
        @Transactional
        public boolean restoreSendEmail(String email) {
            Person person;
            if((person = getByEmail(email)) != null){
                
                String mailKey = encoder.encode(person.getEmail().concat(String.valueOf(new Random().nextLong())));
                person.setMailKey(mailKey);
                mailService.sendRestoreMail("springytest@gmail.com", "etenzor@gmail.com", person.getMailKey());
                save(person);
                logger.info("user {} restore pass mail send", person);
                return true;
            }
            return false;
        
        }
        
        @Override
        @Transactional
        public boolean restorePass(String key, Password password) {
            Person person;
            if((person = personDao.getPersonByKey(key)) != null){
                person.setPassword(encoder.encode(password.getNewPass()));
                person.setMailKey(null);
                save(person);
                logger.info("person {} password changed", person);
                return true;
            }
            logger.info("person {} password don't changed", person);
            return false;
                
        }
        

        @Override
        @Transactional
        public boolean isKeyValid(String key) {
            if(personDao.getPersonByKey(key) != null)
                return true;
            return false;
        }

        
        
        
}

