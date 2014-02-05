package com.ch018.library.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchParamsPerson extends SearchParams implements Serializable {

		private final Logger logger = LoggerFactory.getLogger(SearchParamsPerson.class);
	
		
		private String name;
		private String surname;
		private String email;
		
	
		public void setDefaults() {
	    	
	    	setOrderField("email");
	    	
	    	name = null;
	    	surname = null;
	    	email = null;
	    	
	    }


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getSurname() {
			return surname;
		}


		public void setSurname(String surname) {
			this.surname = surname;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		@Override
		public String toString() {
			return "SearchParamsPerson [name=" + name + ", surname=" + surname
					+ ", email=" + email + "]";
		}

		
    
	
}
