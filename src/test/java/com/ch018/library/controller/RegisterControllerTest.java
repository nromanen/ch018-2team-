package com.ch018.library.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ch018.library.service.PersonService;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.UserRegistrationForm;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:servlet-context.xml", "classpath:root-context.xml"})
@WebAppConfiguration
public class RegisterControllerTest {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private UserRegistrationForm form;
	
	private MockHttpServletRequest request;
	
	@Before
	public void setup() {
		Mockito.reset(personService);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		form = new UserRegistrationForm();
		form.setEmail("email@mail.com");
		form.setName("name");
		form.setPassword("pass123");
		form.setrPassword("pass123");
		form.setSurname("surname");
		form.setCellPhone("000-0000-0000");
		
		request = new MockHttpServletRequest();
		
		
	}
	
	@Test
	public void addUserNormal() throws Exception {
		
		String response = "{}";
		
		String str = mockMvc.perform(post("/register").param("name", "name")
				.param("surname", "surname")
				.param("email", "email@mail.com")
				.param("rEmail", "email@mail.com")
				.param("password", "pass123")
				.param("rPassword", "pass123")
				.param("cellPhone", "000-000-0000"))
		.andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		assertEquals(str, response);
		
	}
	
	@Test
	public void addUserIncorrectParam() throws Exception {
		
		mockMvc.perform(post("/register").param("name", "name")
				.param("surname", "surname")
				.param("email", "email@mail.com")
				.param("rEmail", "email@mail.com")
				.param("password", "pass123")
				.param("rPassword", "pass123")
				.param("cellPhone", "000-0000-0000"))
		.andExpect(status().isBadRequest());
		
	}
	
	@Test public void confirmationKeyFound() throws Exception {
		String key = "11111111111";
		when(personService.confirmMail(key)).thenReturn(true);
		mockMvc.perform(get("/register").param("key", key))
		.andExpect(status().isOk());
	}
	
	@Test
	public void restoreNormal() throws Exception {
		String email = "email@mail.com";
		RegisterController controller = new RegisterController();
		String path = controller.getPathFromRequest(request);
		when(personService.restoreSendEmail(email, path)).thenReturn(true);
		
		mockMvc.perform(post("/restore").param("email", email))
				.andExpect(status().isOk());	
	}
	
	@Test
	public void restoreEmailNotFound() throws Exception {
		RegisterController controller = new RegisterController();
		String path = controller.getPathFromRequest(request);
		String email = "email@mail.com";
		when(personService.restoreSendEmail(email, path)).thenReturn(false);
		
		mockMvc.perform(post("/restore").param("email", email))
				.andExpect(status().isBadRequest());	
	}
	
	@Ignore
	@Test
	public void restorePasswordPostNormal() throws Exception {
		
		String key = "11111111111";
		
		Password pass = new Password();
		pass.setNewPass("111111");
		pass.setOldPass("pass234");
		pass.setrNewPass("pass234");
		
		when(personService.restorePass(key, pass)).thenReturn(true);
		
		mockMvc.perform(post("/restore/password")
				.param("key", key)
				.param("oldPass", "111111")
				
				.param("newPass", "pass234")
				.param("rNewPass", "pass234"))
		.andExpect(status().isOk());
		
	}
}
