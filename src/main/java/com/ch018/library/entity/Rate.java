package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "rate", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "personId", "bookId" } ) })
public class Rate implements Serializable {


		private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue
	    @Column(name = "id")
	    private int id;
		
		@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "personId", referencedColumnName = "personId")
        private Person person;
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "bookId", referencedColumnName = "bookId")
        private Book book;
        
        @Column(name = "score")
        private Float score;
        
        @Column(name = "message")
        private String message;
        
        @Column(name = "rateDate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date rateDate;

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

		public Float getScore() {
			return score;
		}

		public void setScore(Float rate) {
			this.score = rate;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getRateDate() {
			return rateDate;
		}

		public void setRateDate(Date rateDate) {
			this.rateDate = rateDate;
		}
		
		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + id;
			result = prime * result
					+ ((message == null) ? 0 : message.hashCode());
			result = prime * result
					+ ((person == null) ? 0 : person.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Rate))
				return false;
			Rate other = (Rate) obj;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (id != other.id)
				return false;
			if (message == null) {
				if (other.message != null)
					return false;
			} else if (!message.equals(other.message))
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
			return "Rate [id=" + id + ", person=" + person.getEmail() + ", book=" + book.getTitle()
					+ ", score=" + score + ", message=" + message + ", rateDate="
					+ rateDate + "]";
		}
        
		
		
        
	
}
