package com.ch018.library.service;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.UserAlreadyExists;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.PersonEditValidator;
import com.ch018.library.validation.PersonalInfo;
import com.ch018.library.validation.UserRegistrationForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Edd Arazian
 */
@Service
public class PersonServiceImpl implements PersonService {

		private static final int DEFAULT_BOOKS_ALLOWED = 10;
		private static final int MAX_RATING = 100;
		
		private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
		@Autowired
		private PersonDao personDao;
	
		@Autowired
		private BCryptPasswordEncoder encoder;
	
		@Autowired
		private MailService mailService;
	
		@Autowired
		private BookInUseService bookInUse;
		
		@Autowired
		@Qualifier(value = "org.springframework.security.authenticationManager")
	    protected AuthenticationManager authenticationManager;
	
		private Person personEdit;
	
		@Override
		@Transactional
		public void save(Person person) {
			personDao.save(person);
		}
	
		@Override
		@Transactional
		public boolean delete(int id) {
            System.out.println("serviceimpl");
            if (personDao.delete(id)==0) return true;
            else return false;
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
		public void updatePassword(Password password, Person person)
				throws Exception {
			if (!encoder.matches(password.getOldPass(), person.getPassword())) {
				logger.info("person {}", person);
				logger.error("passwords don't match during update");
				throw new Exception("old password incorrect");
			}
			person.setPassword(encoder.encode(password.getNewPass()));
			update(person);
			Authentication auth = new PreAuthenticatedAuthenticationToken(
					person.getEmail(), encoder.encode(password.getNewPass())
					, Arrays.asList(new SimpleGrantedAuthority(person.getPersonRole())));
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			logger.error("passwords for {} changed", person.getEmail());
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
			int returnedInTime, returnedNotInTime, failedOrders;
			double grade = 0;
			int booksOnHands, gradeInt = 0;
			returnedInTime = person.getTimelyReturn();
			returnedNotInTime = person.getUntimekyReturn();
			failedOrders = person.getFailedOrders();
			if ((returnedInTime > 0) || (returnedNotInTime > 0)) {
				try{
					grade = (double) returnedInTime
							/ (returnedNotInTime + failedOrders + returnedInTime);
				} catch (Exception e) {
					grade = 0;
				}
				grade *= MAX_RATING;
				gradeInt = (int) grade;
			}
			person.setGeneralRating(gradeInt);
			personDao.update(person);
			return person;
		}
	
		@Override
		@Transactional
		public void changeEmail(String email, Person person, String path) throws Exception {
			try {
				if (getByEmail(email) != null)
					throw new Exception("email already in use");
				person.setEmail(email);
				person.setMailConfirm(Boolean.FALSE);
				String key = getHashFromString(email);
				person.setConfirmationKey(key);
				update(person);
				mailService.sendConfirmationMail("springytest@gmail.com",
						"etenzor@gmail.com", "email change confirmation", key, path);
				Authentication auth = new PreAuthenticatedAuthenticationToken(
						email, SecurityContextHolder.getContext()
								.getAuthentication().getPrincipal());
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				logger.error("error during email: {} change {}", email,
						e.getMessage());
				throw new Exception("email doesn't changed. Try later");
			}
		}
	
		@Override
		@Transactional
		public Person register(UserRegistrationForm form, String path) throws Exception {
			Person person = new Person(form.getName(), form.getSurname(),
					form.getEmail(), form.getPassword(), form.getCellPhone());
			if (getByEmail(person.getEmail()) != null) {
				throw new UserAlreadyExists();
			}
			person.setPassword(encoder.encode(person.getPassword()));
			person.setPersonRole("ROLE_USER");
			person.setBooksAllowed(DEFAULT_BOOKS_ALLOWED);
			String mailKey = getHashFromString(form.getEmail());
			person.setConfirmationKey(mailKey);
			mailService.sendConfirmationMail("springytest@gmail.com",
					"etenzor@gmail.com", "confirmation mail", person.getConfirmationKey(), path);
			save(person);
			logger.info("new user {} registered", person);
			return person;
		}
	
		@Override
		@Transactional
		public boolean confirmMail(String key, HttpServletRequest request) {
			try {
				Person person = personDao.getPersonByKey(key);
				if (person == null) {
					logger.info("key not found in db");
					return false;
				}
					
				person.setMailConfirm(Boolean.TRUE);
				person.setConfirmationKey(null);
				save(person);
				Authentication token = 
						new PreAuthenticatedAuthenticationToken(person.getEmail(), person.getPassword()
						, Arrays.asList(new SimpleGrantedAuthority(person.getPersonRole())));
				SecurityContextHolder.getContext().setAuthentication(token);
				
				logger.info("person {} successfully confirm mail | auth {}", person, SecurityContextHolder.getContext());
				return true;
			} catch (Exception e) {
				logger.info("Error in confirm {}", e.getMessage());
				return false;
			}
		}
	
		@Override
		@Transactional
		public boolean restoreSendEmail(String email, String path) {
			Person person = getByEmail(email);
			if (person != null) {
	
				String mailKey = getHashFromString(email);
				person.setConfirmationKey(mailKey);
				mailService.sendRestoreMail("springytest@gmail.com",
						"etenzor@gmail.com", person.getConfirmationKey(), path);
				save(person);
				logger.info("user {} restore pass mail send", person);
				return true;
			}
			return false;
	
		}
	
		@Override
		@Transactional
		public boolean restorePass(String key, Password password) {
			Person person = personDao.getPersonByKey(key);
			logger.info("person {} for pass change {}", person, key);
			if (person != null) {
				person.setPassword(encoder.encode(password.getNewPass()));
				person.setConfirmationKey(null);
				save(person);
				Authentication token = 
						new PreAuthenticatedAuthenticationToken(person.getEmail(), person.getPassword()
						, Arrays.asList(new SimpleGrantedAuthority(person.getPersonRole())));
				SecurityContextHolder.getContext().setAuthentication(token);
				logger.info("person {} password changed", person);
				return true;
			}
			logger.info("person {} password don't changed", person);
			return false;
	
		}
	
		@Override
		@Transactional
		public boolean isKeyValid(String key) {
			if (personDao.getPersonByKey(key) != null)
				return true;
			return false;
		}
	
		@Override
		@Transactional
		public void updatePersonalInfo(Person person, PersonalInfo info)
				throws Exception {
			try {
				person.setName(info.getName());
				person.setSurname(info.getSurname());
				person.setCellphone(info.getCellphone());
				person.setSms(info.isSms());
				update(person);
			} catch (Exception e) {
				logger.error("error in updatePersonalInfo {}", e.getMessage());
				throw new Exception("information not update");
			}
	
		}
	
		private String getHashFromString(String str) {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA-1");
			} catch (Exception e) {
				return null;
			}
			Random rand = new Random();
			str = str + String.valueOf(rand.nextLong());
			byte[] b = md.digest(str.getBytes());
			String result = "";
			for (int i = 0; i < b.length; i++) {
				result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
			}
			return result;
		}
	
		@Override
		@Transactional
		public void update(PersonEditValidator user) {
	
			personEdit = getById(user.getPid());
	
			personEdit.setName(user.getName());
			personEdit.setSurname(user.getSurname());
			personEdit.setEmail(user.getEmail());
			personEdit.setCellphone(user.getCellphone());
			personEdit.setSms(user.isSms());
			personEdit.setBooksAllowed(user.getBooksAllowed());
	
			update(personEdit);
		}
	
		@Override
		@Transactional
		public List<BooksInUse> getUsingBooks(Person person) {
	
			List<BooksInUse> books = bookInUse.getBooksInUseByPerson(person);
	
			return books;
	
		}

		@Override
		public List<Person> orderByName() {
			return personDao.orderByName();
		}

		@Override
		public List<Person> orderBySurname() {
			return personDao.orderBySurname();
		}

		@Override
		public List<Person> orderByRating() {
			return personDao.orderByRating();
		}
				

}
