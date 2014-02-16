package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BooksInUse", uniqueConstraints = { @UniqueConstraint(columnNames = {"personId", "bookId" }) })
public class BooksInUse implements Serializable {
	

		private static final long serialVersionUID = 2738859313687793456L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
	
		@ManyToOne()
		@JoinColumn(name = "personId", referencedColumnName = "personId")
		private Person person;
	
		@ManyToOne()
		@JoinColumn(name = "bookId", referencedColumnName = "bookId")
		private Book book;
	
		@Temporal(value = TemporalType.TIMESTAMP)
		@Column(name = "returnDate")
		private Date returnDate;
	
		public BooksInUse() {
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public Person getPerson() {
			return person;
		}
	
		public void setPerson(Person person) {
			this.person = person;
		}
	
		public Book getBook() {
			return book;
		}
	
		public void setBook(Book book) {
			this.book = book;
		}

		public Date getReturnDate() {
			return returnDate;
		}
	
		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + id;
			result = prime * result + ((person == null) ? 0 : person.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof BooksInUse))
				return false;
			BooksInUse other = (BooksInUse) obj;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (id != other.id)
				return false;
			if (person == null) {
				if (other.person != null)
					return false;
			} else if (!person.equals(other.person))
				return false;
			return true;
		}
	
		@Override
		public String toString() {
			return "BooksInUse [id=" + id + ", person=" + person + ", book=" + book
					+ ", returnDate=" + returnDate
					+ "]";
		}


}
