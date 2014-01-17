package com.ch018.library.validation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Edd Arazian
 */
public class UserRegistrationForm {

	        private static final int MIN_NAME = 2;
	        private static final int MAX_NAME = 30;
	        private static final int MIN_PASS = 6;
	        private static final int MAX_PASS = 16;
	
	
	        @NotEmpty
	        @Size(min = MIN_NAME, max = MAX_NAME
	        		, message = "name size must be between 2 and 30")
	        private String name;
	
	        @NotEmpty
	        @Size(min = MIN_NAME, max = MAX_NAME
	        		, message = "surname size must be between 2 and 30")
	        private String surname;
	
	        @NotEmpty
	        @Email(message = "email format incorrect")
	        private String email;
	
	        @NotEmpty
	        @Email(message = "email format incorrect")
	        private String rEmail;
	
	        @NotEmpty(message = "password field can't be empty")
	        @Size(min = MIN_PASS, max = MAX_PASS
	        		, message = "password size must be between 6 and 16")
	        private String password;
	
	        @NotEmpty(message = "re-enter password field can't be empty")
	        @Size(min = MIN_PASS, max = MAX_PASS
	        		, message = "password size must be between 6 and 16")
	        private String rPassword;
	
	        @NotEmpty(message = "phone field can't be empty")
	        @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}"
	        		, message = "phone must be in format xxx-xxx-xxxx")
	        private String cellPhone;
	
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
	
	        public String getrEmail() {
	            return rEmail;
	        }
	
	        public void setrEmail(String rEmail) {
	            this.rEmail = rEmail;
	        }
	
	        public String getPassword() {
	            return password;
	        }
	
	        public void setPassword(String password) {
	            this.password = password;
	        }
	
	        public String getrPassword() {
	            return rPassword;
	        }
	
	        public void setrPassword(String rPassword) {
	            this.rPassword = rPassword;
	        }
	
	        public String getCellPhone() {
	            return cellPhone;
	        }
	
	        public void setCellPhone(String cellPhone) {
	            this.cellPhone = cellPhone;
	        }

}
