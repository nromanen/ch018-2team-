package com.ch018.library.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ch018.library.util.annotation.Affect;
import com.ch018.library.util.annotation.Ordinary;

public class SearchParamsPerson extends SearchParams implements Serializable {


		private final Logger logger = LoggerFactory.getLogger(SearchParamsPerson.class);
	
		@Ordinary
		private String email;
		
	
		public void setMainFieldsDefault() {
	    	
			super.setMainFieldsDefault();
			
	    	setOrderField("email");
	    	
	    	email = null;
	    	
	    }



		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		@Override
		public String toString() {
			return "SearchParamsPerson [email=" + email + "]";
		}

		
    
	
}
