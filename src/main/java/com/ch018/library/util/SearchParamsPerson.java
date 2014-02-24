package com.ch018.library.util;

import java.io.Serializable;

import com.ch018.library.util.annotation.Ordinary;

public class SearchParamsPerson extends SearchParams implements Serializable {



		private static final long serialVersionUID = 6196192130964154204L;
		
		
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
