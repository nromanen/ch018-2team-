package com.ch018.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.entity.Person;
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
	
	@Before
	public void setup() {
		
		Connection connection;
		Statement stmt;
		PreparedStatement preparedStmt;
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/librarytest2", "root", "root");
			stmt = connection.createStatement();
			stmt.execute("delete from person");
			String saveTestBook = "insert into person(name, surname, email, password, cellphone) values (?, ?, ?, ?, ?)";
            String userCreateQuery = "INSERT INTO `librarytest2`.`person` (`personId`, `booksAllowed`, `booksOnHands`, `cellphone`, `email`, `failedorders`, `generalratio`, `mailConfirm`, `name`, `password`, `personRole`, `sms`, `surname`, `timelyreturn`, `untimelyreturn`) VALUES ('3', '10', '0', '326-564-6464', 'user1@mail.com', '0', '0', '1', 'user1', '$2a$10$wMLrI4F8SXwEwW4HaLucW.boXEC1.iGpduJrVC7mIr3mUHrjc4n9a', 'ROLE_USER', '0', 'user1', '0', '0')";
			stmt = connection.createStatement();
			stmt.executeUpdate(userCreateQuery);
			
		} catch (Exception e) {
			
		}
		
	}
	
	@Test(expected = OldPasswordIncorrectException.class)
	public void updatePasswordTest() throws Exception {
		
		Password pass = new Password();
		pass.setNewPass("user234");
		pass.setrNewPass("user234");
		pass.setOldPass("user356");
		
		UserDetails userDetails = userDetailsService.loadUserByUsername ("user1@mail.com");
		Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authToken);
	    
		Person person = personService.getByEmail("user1@mail.com");
		
		
		personService.updatePassword(pass, person);
		
	}

	
}


/*
 * @Override
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
	*/
 
