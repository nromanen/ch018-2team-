package com.ch018.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.EmailAlreadyInUseException;
import com.ch018.library.exceptions.EmailNotChangedException;
import com.ch018.library.exceptions.OldPasswordIncorrectException;
import com.ch018.library.validation.Password;

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
		person.setPersonRole("ROLE_USER");
		person.setSms(false);
		person.setTimelyReturn(0);
		person.setUntimekyReturn(0);
		
		setDatabase(person);
		
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
	
	
	/*@Override
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
		}*/
	
	
	private void setDatabase(Person person) {
		Connection connection;
		Statement stmt;
		PreparedStatement preparedStmt;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
			preparedStmt = connection.prepareStatement("delete from person");
			preparedStmt.execute();
			
			String userCreateQuery = "INSERT INTO `librarytest2`.`person` (`personId`, `booksAllowed`, `booksOnHands`, `cellphone`,"
            		+ " `email`, `failedorders`, `generalratio`, `mailConfirm`, `name`, `password`,"
            		+ " `personRole`, `sms`, `surname`, `timelyreturn`, `untimelyreturn`)"
            		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            
			preparedStmt.execute();
			
			
			
		} catch (Exception e) {
			logger.error("DB wr {}", e.getMessage());
		}
		
	}
	
}

 
