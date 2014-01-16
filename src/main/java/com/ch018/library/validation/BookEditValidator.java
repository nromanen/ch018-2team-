package com.ch018.library.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class BookEditValidator {
	
	private int bId;
	
	@NotEmpty
    @Size(max=255)
	private String title;
	
	@NotEmpty
    @Size(max=255)
	private String authors;
	
	@NotNull
    @Range(min=1800, max=2030)
	private Integer year;
	
	@NotEmpty
    @Size(max=255)
	private String publisher;
	
	@NotNull
    @Range(min=1, max=10000)
	private Integer pages;
	
	@Size(min=0)
	private String description;
	
	
	private String img;
	
	@NotNull
    @Range(min=1, max=1000)
	private Integer shelf;
	
	@NotNull
    @Range(min=0, max=365)
	private Integer term;
	
	@NotNull
    @Range(min=1, max=1000)
	private Integer generalQuantity;
	
	public int getbId() {
		return bId;
	}
	
	public void setbId(int bId) {
		this.bId = bId;
	}
	
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
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Integer getPages() {
		return pages;
	}
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public Integer getShelf() {
		return shelf;
	}
	
	public void setShelf(Integer shelf) {
		this.shelf = shelf;
	}
	
	public Integer getTerm() {
		return term;
	}
	
	public void setTerm(Integer term) {
		this.term = term;
	}
	
	public Integer getGeneralQuantity() {
		return generalQuantity;
	}
	
	public void setGeneralQuantity(Integer generalQuantity) {
		this.generalQuantity = generalQuantity;
	}

}
