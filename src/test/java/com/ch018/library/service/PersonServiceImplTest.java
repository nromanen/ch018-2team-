package com.ch018.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.EmailAlreadyInUseException;
import com.ch018.library.exceptions.EmailNotChangedException;
import com.ch018.library.exceptions.InformationNotUpdateException;
import com.ch018.library.exceptions.OldPasswordIncorrectException;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.PersonalInfo;
import com.ch018.library.validation.UserRegistrationForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context_for_service.xml", "classpath:root-context_for_service.xml"})
@WebAppConfiguration
public class PersonServiceImplTest {
	
	private Logger logger = LoggerFactory.getLogger(PersonServiceImplTest.class);
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Person person;
	
	private String newPass;
	private String oldPass;
	
	@Before
	public void setup() {
		newPass = "user234";
		oldPass = "user123";
		
		person = new Person();
		person.setPid(1);
		person.setName("user1");
		person.setSurname("user1");
		person.setEmail("user1@mail.com");
		person.setPassword("$2a$10$wMLrI4F8SXwEwW4HaLucW.boXEC1.iGpduJrVC7mIr3mUHrjc4n9a");
		person.setBooksAllowed(10);
		person.setBooksOnHands(0);
		person.setCellphone("000-000-0000");
		person.setFailedOrders(0);
		person.setGeneralRating(0);
		person.setMailConfirm(true);
		person.setConfirmationKey("1111111111");
		person.setPersonRole("ROLE_USER");
		person.setSms(false);
		person.setTimelyReturn(0);
		person.setUntimekyReturn(0);
		
		setDatabase(person);
		
	}
	
	@After
	public void after() {
		flush();
	}
	
	@Test(expected = OldPasswordIncorrectException.class)
	public void updatePasswordIncorrectTest() throws Exception {
		
		Password pass = new Password();
		pass.setNewPass(newPass);
		pass.setrNewPass(newPass);
		pass.setOldPass("incorrect");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authToken);
	    
		Person testPerson = personService.getById(person.getPid());
		
		personService.updatePassword(pass, testPerson);
		
	}
	
	@Test
	public void updatePasswordTest() throws Exception {
		
		Password pass = new Password();
		pass.setNewPass(newPass);
		pass.setrNewPass(newPass);
		pass.setOldPass(oldPass);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername (person.getEmail());
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authToken);
	    
		Person testPerson = personService.getByEmail(person.getEmail());
		
		
		personService.updatePassword(pass, testPerson);
		
		String newPassFromPerson = personService.getByEmail(person.getEmail()).getPassword();
		Assert.assertTrue(encoder.matches(newPass, newPassFromPerson));
	}

	
	@Test
	public void changeEmailTest() throws Exception {
		String newEmail = "user1new@mail.com";
		String path = "path";
		
		personService.changeEmail(newEmail, person, path);
		
		Person personFromDb = personService.getByEmail(person.getEmail());
		
		Assert.assertTrue(personFromDb.getEmail().equals(newEmail));
	}
	
	@Test(expected = EmailAlreadyInUseException.class)
	public void changeEmailAlreadyInUseTest() throws Exception {
		String newEmail = "user1@mail.com";
		String path = "path";
		
		personService.changeEmail(newEmail, person, path);
		
		Person personFromDb = personService.getByEmail(person.getEmail());
		
		Assert.assertTrue(personFromDb.getEmail().equals(newEmail));
	}
	
	@Test(expected = EmailNotChangedException.class)
	public void changeEmailNullPersonTest() throws Exception {
		String newEmail = "user1new@mail.com";
		String path = "path";
		personService.changeEmail(newEmail, null, path);
		
	}
	
	@Test
	public void registerTest() {
		UserRegistrationForm form = new UserRegistrationForm();
		form.setName("newUser");
		form.setSurname("newSurname");
		form.setEmail("newmail@mail.com");
		form.setrEmail("newmail@mail.com");
		form.setPassword("user123");
		form.setrPassword("user123");
		form.setCellPhone("000-000-0000");
		
		personService.register(form, "path");
	}
	
	@Test(expected = HibernateException.class)
	public void registerTestUserExists() {
		UserRegistrationForm form = new UserRegistrationForm();
		form.setName(person.getName());
		form.setSurname(person.getSurname());
		form.setEmail(person.getEmail());
		form.setrEmail(person.getEmail());
		form.setPassword("user123");
		form.setrPassword("user123");
		form.setCellPhone(person.getCellphone());
		
		personService.register(form, "path");
	}
	
	@Test
	public void confirmMailTest() throws Exception {
		String key = person.getConfirmationKey();
		Assert.assertTrue(personService.confirmMail(key));
	}
	
	@Test
	public void confirmMailTestKeyNotFound() throws Exception {
		String key = "1";
		Assert.assertFalse(personService.confirmMail(key));
	}
	
	@Test
	public void restoreSendMailTest() throws Exception {
		String email = person.getEmail();
		Assert.assertTrue(personService.restoreSendEmail(email, "path"));
		
	}
	
	@Test
	public void restoreSendMailNotFoundTest() throws Exception {
		String email = "incorrect@mail.com";
		Assert.assertFalse(personService.restoreSendEmail(email, "path"));
		
	}
	
	@Test
	public void restorePassTest() throws Exception {
		String key = person.getConfirmationKey();
		Password pass = new Password();
		pass.setNewPass(newPass);
		pass.setrNewPass(newPass);
		pass.setOldPass(oldPass);
		personService.restorePass(key, pass);
		
		String changedPass = personService.getById(person.getPid()).getPassword();
		
		Assert.assertTrue(encoder.matches(newPass, changedPass));
	}
	
	@Test
	public void restorePassKeyNotFoundTest() throws Exception {
		String key = "1";
		Password pass = new Password();
		pass.setNewPass(newPass);
		pass.setrNewPass(newPass);
		pass.setOldPass(oldPass);
		
		Assert.assertFalse(personService.restorePass(key, pass));
	}
	
	@Test
	public void restorePassNullTest() throws Exception {		
		Assert.assertFalse(personService.restorePass(null, null));
	}
	
	@Test
	public void updatePersonalInfoTest() throws Exception {
		PersonalInfo info = new PersonalInfo();
		info.setName(person.getName());
		info.setSurname(person.getSurname());
		info.setSms(person.isSms());
		info.setCellphone(person.getCellphone());
		
		personService.updatePersonalInfo(person, info);
		
	}
	
	@Test(expected = InformationNotUpdateException.class)
	public void updatePersonalInfoNotUpdatePersonNullTest() throws Exception {
		PersonalInfo info = new PersonalInfo();
		info.setName(person.getName());
		info.setSurname(person.getSurname());
		info.setSms(person.isSms());
		info.setCellphone(person.getCellphone());
		
		personService.updatePersonalInfo(null, info);
		
	}
	
	private void setDatabase(Person person) {
		Connection connection;
		PreparedStatement preparedStmt;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
			preparedStmt = connection.prepareStatement("delete from person");
			preparedStmt.execute();
			
			String userCreateQuery = "INSERT INTO `librarytest2`.`person` (`personId`, `booksAllowed`, `booksOnHands`, `cellphone`,"
            		+ " `email`, `failedorders`, `generalratio`, `mailConfirm`, `name`, `password`,"
            		+ " `personRole`, `sms`, `surname`, `timelyreturn`, `untimelyreturn`, `confirmationKey`)"
            		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(userCreateQuery);
			preparedStmt.setInt(1, person.getPid());
			preparedStmt.setInt(2, person.getBooksAllowed());
			preparedStmt.setInt(3, person.getBooksOnHands());
			preparedStmt.setString(4, person.getCellphone());
			preparedStmt.setString(5, person.getEmail());
			preparedStmt.setInt(6, person.getFailedOrders());
			preparedStmt.setDouble(7, person.getGeneralRating());
			preparedStmt.setBoolean(8, person.isMailConfirm());
			preparedStmt.setString(9, person.getName());
			preparedStmt.setString(10, person.getPassword());
			preparedStmt.setString(11, person.getPersonRole());
			preparedStmt.setBoolean(12, person.isSms());
			preparedStmt.setString(13, person.getSurname());
			preparedStmt.setInt(14, person.getTimelyReturn());
			preparedStmt.setInt(15, person.getUntimekyReturn());
			preparedStmt.setString(16, person.getConfirmationKey());
            
			preparedStmt.execute();
			
			
			
		} catch (Exception e) {
			logger.error("DB wr {}", e.getMessage());
		}
		
	}

	private void flush() {
		Connection connection;
		PreparedStatement preparedStmt;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
			preparedStmt = connection.prepareStatement("delete from person");
			preparedStmt.execute();

			
		} catch (Exception e) {
			logger.error("DB wr {}", e.getMessage());
		}
	}
}

 
