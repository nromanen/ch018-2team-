package com.ch018.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.OldPasswordIncorrectException;
import com.ch018.library.validation.Password;

public class PersonServiceImplTest {
	
	@Autowired
	private PersonService personService;
	
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
			preparedStmt = connection.prepareStatement(saveTestBook);
			preparedStmt.setString(1, "testName");
			preparedStmt.setString(2, "testSurname");
			preparedStmt.setString(3, "testEmail@mail.com");
			preparedStmt.setString(4, "testPassword");
			preparedStmt.setString(5, "000-000-0000");
			
			preparedStmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		}
		
	}
	
	
	@Test(expected = OldPasswordIncorrectException.class)
	public void updatePasswordTest() throws Exception {
		
		Password pass = new Password();
		pass.setNewPass("newPass");
		pass.setrNewPass("newPass");
		pass.setOldPass("wrongPassword");
		
		Person person = personService.getByEmail("testEmail@mail.com");
		
		System.out.println(person);
		
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
 
