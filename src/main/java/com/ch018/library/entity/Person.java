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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.ch018.library.helper.Roles;

/**
 * 
 * @author Edd Arazian
 */

@Entity
@Table(name = "persons")
@Proxy(lazy = false)
public class Person implements Serializable {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int pid;
	
		@NotEmpty
		@Size(max = 255)
		@Column(name = "name")
		private String name;
	
		@NotEmpty
		@Size(max = 255)
		@Column(name = "surname")
		private String surname;
	
		@NotEmpty
		@Email
		@Column(name = "email", unique = true, nullable = false)
		private String email;
	
		@NotEmpty
		@Column(name = "password", nullable = false)
		private String password;
	
		@Column(name = "prole")
		private String personRole;
	
		@NotEmpty
		@Column(name = "cellphone")
		private String cellphone;
	
		@Column(name = "confirm")
		private boolean confirm;
	
		@Column(name = "sms")
		private boolean sms;
	
		@Column(name = "mailconfirm")
		private boolean mailConfirm;
	
		@Column(name = "mailkey")
		private String mailKey;
	
		@Column(name = "multi")
		private Integer multiBook;
	
		@Column(name = "timelyreturn")
		private int timelyReturn;
	
		@Column(name = "untimelyreturn")
		private int untimekyReturn;
	
		@NotNull
		@Column(name = "booksallowed")
		private int booksAllowed;
	
		@Column(name = "failedorders")
		private int failedOrders;
	
		@NotNull
		@Column(name = "generalratio")
		private double generalRating;
	
		@OneToMany(targetEntity = BooksInUse.class, mappedBy = "person")
		private Set<Book> booksInUse = new HashSet<Book>();
	
		@OneToMany(targetEntity = Orders.class, mappedBy = "person")
		private Set<Book> orders = new HashSet<>();
	
		@OneToMany(targetEntity = WishList.class, mappedBy = "person")
		private Set<Book> wishes = new HashSet<>();
	
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
	
		public String getProle() {
			return personRole;
		}
	
		public void setProle(String prole) {
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
	
		public Set<Book> getBooksInUse() {
			return booksInUse;
		}
	
		public void setBooksInUse(Set<Book> booksInUse) {
			this.booksInUse = booksInUse;
		}
	
		public Set<Book> getOrders() {
			return orders;
		}
	
		public void setOrders(Set<Book> orders) {
			this.orders = orders;
		}
	
		public Set<Book> getWishes() {
			return wishes;
		}
	
		public void setWishes(Set<Book> wishes) {
			this.wishes = wishes;
		}
	
		
	
		public Integer getMultiBook() {
			return multiBook;
		}
	
		public void setMultiBook(Integer multiBook) {
			this.multiBook = multiBook;
		}
	
		public boolean isMailConfirm() {
			return mailConfirm;
		}
	
		public void setMailConfirm(boolean mailConfirm) {
			this.mailConfirm = mailConfirm;
		}
	
		public String getMailKey() {
			return mailKey;
		}
	
		public void setMailKey(String mailKey) {
			this.mailKey = mailKey;
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
					+ ", confirm=" + confirm + ", sms=" + sms + ", mailConfirm="
					+ mailConfirm + ", mailKey=" + mailKey + ", multiBook="
					+ multiBook + ", timelyReturn=" + timelyReturn
					+ ", untimekyReturn=" + untimekyReturn + ", booksAllowed="
					+ booksAllowed + ", failedOrders=" + failedOrders
					+ ", generalRating=" + generalRating + "]";
		}

}
