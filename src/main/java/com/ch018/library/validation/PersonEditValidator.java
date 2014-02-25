package com.ch018.library.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.ch018.library.util.Constants;

public class PersonEditValidator {
		
		private int pid;
	
		@NotEmpty
		@Size(min = Constants.MIN_USER_NAME_LENGTH, max = Constants.MAX_USER_NAME_LENGTH)
		private String name;
	
		@NotEmpty
		@Size(min = Constants.MIN_USER_NAME_LENGTH, max = Constants.MAX_USER_NAME_LENGTH)
		private String surname;
	
		@NotEmpty
		@Email
		private String email;
	
		@NotEmpty
        @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "phone field can't be empty")
		private String cellphone;
	
		private boolean confirm;
	
		private boolean sms;
	
		@NotNull
		private Integer booksAllowed;
	
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
	
		public String getCellphone() {
			return cellphone;
		}
	
		public void setCellphone(String cellphone) {
			this.cellphone = cellphone;
		}
	
		public boolean isConfirm() {
			return confirm;
		}
	
		public void setConfirm(boolean confirm) {
			this.confirm = confirm;
		}
	
		public boolean isSms() {
			return sms;
		}
	
		public void setSms(boolean sms) {
			this.sms = sms;
		}
	
		public Integer getBooksAllowed() {
			return booksAllowed;
		}
	
		public void setBooksAllowed(Integer booksAllowed) {
			this.booksAllowed = booksAllowed;
		}
	
		public int getPid() {
			return pid;
		}
	
		public void setPid(int pid) {
			this.pid = pid;
		}

}
