package com.ch018.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.ch018.library.util.Constants;
import com.ch018.library.util.Roles;

/**
 * 
 * @author Edd Arazian
 */

@Entity
@Table(name = "person")
public class Person implements Serializable {
	

		private static final long serialVersionUID = -3583908570640889000L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "personId")
		private int pid;
	
		@NotEmpty
		@Size(min = Constants.MIN_USER_NAME_LENGTH, max = Constants.MAX_USER_NAME_LENGTH)
		@Column(name = "name")
		private String name;
	
		@NotEmpty
		@Size(min = Constants.MIN_USER_NAME_LENGTH, max = Constants.MAX_USER_NAME_LENGTH)
		@Column(name = "surname")
		private String surname;
	
		@NotEmpty
		@Email
		@Column(name = "email", unique = true, nullable = false)
		private String email;
	
		@NotEmpty
		@Column(name = "password", nullable = false)
		private String password;
	
		@Column(name = "personRole")
		private String personRole;
	
		@NotEmpty
        @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "phone field can't be empty")
		@Column(name = "cellphone")
		private String cellphone;
	
		@Column(name = "sms")
		private boolean sms;
	
		@Column(name = "mailConfirm")
		private boolean mailConfirm;
	
		@Column(name = "confirmationKey")
		private String confirmationKey;
	
		@Column(name = "booksOnHands")
		private Integer booksOnHands = 0;
	
		@Column(name = "timelyreturn")
		private int timelyReturn;
	
		@Column(name = "untimelyreturn")
		private int untimekyReturn;
	
		@NotNull
		@Range(min = 0, max = Constants.MAX_BOOKS_ALLOWED)
		@Column(name = "booksAllowed")
		private int booksAllowed;
	
		@Column(name = "failedorders")
		private int failedOrders;
	
		@Column(name = "generalratio")
		private double generalRating = 0D;
	
		@OneToMany(mappedBy = "person")
		private Set<BooksInUse> booksInUse;
	
		@OneToMany(mappedBy = "person")
		private Set<Orders> orders;
	
		@OneToMany(mappedBy = "person")
		private Set<WishList> wishes;
		
		@OneToMany(mappedBy = "person")
		private Set<Rate> rates = new HashSet<>();
	
		public Person() {
	
		}
	
		public Person(String email) {
			this.email = email;
		}
	
		public Person(String name, String surname, String email, String password,
				String cellphone) {
			this.name = name;
			this.surname = surname;
			this.email = email;
			this.password = password;
			this.cellphone = cellphone;
		}
	
		
		public int getPid() {
			return pid;
		}
	
		public void setPid(int id) {
			this.pid = id;
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
	
		public boolean isSms() {
			return sms;
		}
	
		public void setSms(boolean sms) {
			this.sms = sms;
		}
	
		public String getPersonRole() {
			return personRole;
		}
	
		public void setPersonRole(String prole) {
			this.personRole = Roles.valueOf(prole).toString();
		}
	
		public int getTimelyReturn() {
			return timelyReturn;
		}
	
		public void setTimelyReturn(int timelyReturn) {
			this.timelyReturn = timelyReturn;
		}
	
		public int getUntimekyReturn() {
			return untimekyReturn;
		}
	
		public void setUntimekyReturn(int untimekyReturn) {
			this.untimekyReturn = untimekyReturn;
		}
	
		public int getBooksAllowed() {
			return booksAllowed;
		}
	
		public void setBooksAllowed(int booksAllowed) {
			this.booksAllowed = booksAllowed;
		}
	
		public int getFailedOrders() {
			return failedOrders;
		}
	
		public void setFailedOrders(int failedOrders) {
			this.failedOrders = failedOrders;
		}
	
		public double getGeneralRating() {
			return generalRating;
		}
	
		public void setGeneralRating(double generalRating) {
			this.generalRating = generalRating;
		}
	
		public Set<BooksInUse> getBooksInUse() {
			return booksInUse;
		}

		public void setBooksInUse(Set<BooksInUse> booksInUse) {
			this.booksInUse = booksInUse;
		}

		public Set<Orders> getOrders() {
			return orders;
		}

		public void setOrders(Set<Orders> orders) {
			this.orders = orders;
		}

		public Set<WishList> getWishes() {
			return wishes;
		}

		public void setWishes(Set<WishList> wishes) {
			this.wishes = wishes;
		}

		public Set<Rate> getRates() {
			return rates;
		}

		public void setRates(Set<Rate> rates) {
			this.rates = rates;
		}

		public Integer getBooksOnHands() {
			return booksOnHands;
		}
	
		public void setBooksOnHands(Integer booksOnHands) {
			this.booksOnHands = booksOnHands;
		}
	
		public boolean isMailConfirm() {
			return mailConfirm;
		}
	
		public void setMailConfirm(boolean mailConfirm) {
			this.mailConfirm = mailConfirm;
		}
	
		public String getConfirmationKey() {
			return confirmationKey;
		}
	
		public void setConfirmationKey(String confirmationKey) {
			this.confirmationKey = confirmationKey;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + pid;
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Person))
				return false;
			Person other = (Person) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (pid != other.pid)
				return false;
			return true;
		}
	
		@Override
		public String toString() {
			return "Person [pid=" + pid + ", name=" + name + ", surname=" + surname
					+ ", email=" + email + ", password=" + password
					+ ", personRole=" + personRole + ", cellphone=" + cellphone
					+  ", sms=" + sms + ", mailConfirm="
					+ mailConfirm + ", confirmationKey=" + confirmationKey + ", booksOnHands="
					+ booksOnHands + ", timelyReturn=" + timelyReturn
					+ ", untimekyReturn=" + untimekyReturn + ", booksAllowed="
					+ booksAllowed + ", failedOrders=" + failedOrders
					+ ", generalRating=" + generalRating + "]";
		}

}
