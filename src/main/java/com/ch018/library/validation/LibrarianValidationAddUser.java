package com.ch018.library.validation;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class LibrarianValidationAddUser {

		private static final int MAX_NAME = 20;
	
		@NotEmpty
		@Size(min = 2, max = MAX_NAME)
		private String name;
		
		@NotEmpty
		@Size(min = 2, max = MAX_NAME)
		private String surname;
		
		@NotEmpty
		@Email
		private String email;
		
		@NotEmpty
		@Size(min = 6, max = MAX_NAME)
		private String password;
		
		@NotEmpty
		@Size(min = 5, max = MAX_NAME)
		private String cellphone;
		
		@NotEmpty
		@Size(min = 1, max = 2)
		private Integer multiBook;
		
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
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getCellphone() {
			return cellphone;
		}
		
		public void setCellphone(String cellphone) {
			this.cellphone = cellphone;
		}
		
		public Integer getMultiBook() {
			return multiBook;
		}
		
		public void setMultiBook(Integer multiBook) {
			this.multiBook = multiBook;
		}
		
}
