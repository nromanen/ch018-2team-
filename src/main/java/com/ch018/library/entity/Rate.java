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
@Table(name = "rates", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "bid" } ) })
public class Rate implements Serializable {


		private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue
	    @Column(name = "id")
	    private int id;
		
		@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "pid", referencedColumnName = "pid")
        private Person person;
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "bid", referencedColumnName = "bid")
        private Book book;
        
        @Column(name = "rate")
        private Float rate;
        
        @Column(name = "message")
        private String message;
        
        @Column(name = "rate_date")
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

		public Float getRate() {
			return rate;
		}

		public void setRate(Float rate) {
			this.rate = rate;
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
		public String toString() {
			return "Rate [id=" + id + ", person=" + person.getEmail() + ", book=" + book.getTitle()
					+ ", rate=" + rate + ", message=" + message + ", rateDate="
					+ rateDate + "]";
		}
        
		
		
        
	
}
