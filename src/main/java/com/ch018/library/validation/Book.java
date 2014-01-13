package com.ch018.library.validation;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

// TODO: if possible, merge with Book entity?
public class Book {

	@NotEmpty
	private String title;
	
	@NotEmpty
	private String authors;
	
	@NotEmpty
	private int year;
	
	@NotEmpty
	private String publisher;
	
	@NotEmpty
	private int pages;
	
	@NotEmpty
	private int shelf;
	
	@NotEmpty
	private int term;
	
	@NotEmpty
	private int generalQuantity;
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthors() {
		return authors;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}
	
	public int getShelf() {
		return shelf;
	}
	
	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
	
	public int getGeneralQuantity() {
		return generalQuantity;
	}
	
	public void setGeneralQuantity(int generalQuantity) {
		this.generalQuantity = generalQuantity;
	}
	
}
