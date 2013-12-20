package com.ch018.library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "orders")
@Proxy(lazy = false)
public class Orders {
	
	/*
	 * Unfinished connection with Book.class and Person.class
	 * Finished but not tested OrdersDAOimpl.class
	 * Finished OrdersServiceImpl.class
	*/
	
	private int id;
	private Person person;
	private Book book;
	private Date date;
	
	public Orders(){
		
	}
	
	public Orders(Person person, Book book, Date date){
		this.person = person;
		this.book = book;
		this.date = date;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	@ManyToOne
	@JoinColumn(name = "personId")
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
