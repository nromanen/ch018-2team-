package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="books")
@Proxy(lazy = false)
public class Book implements Serializable{
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid", unique = true, nullable = false)
	private int bId;
        
        @Column(name="title")
	private String title;
        
        @Column(name="authors")
	private String authors;
        
        @Column(name="year_public")
	private int year;
        
        @Column(name="publication")
	private String publication;
        
        @Column(name="pages")
	private int pages;
        
        @Column(name="description")
	private String description;
        
        @Column(name="shelf")
	private int shelf;
        
        @Column(name="term")
	private int term;
        
        @OneToMany(targetEntity = BooksInUse.class, mappedBy = "book")
	private Set<Person> personsUse;
	
	public Book() {
		
	}
	
	public Book(Book b) {
		title = b.getTitle();
	}

        public int getbId() {
            return bId;
        }

        public void setbId(int bId) {
            this.bId = bId;
        }
	
	
	public String getTitle() {
		return title;
	}
	
	
	public String getAuthors() {
		return authors;
	}
	
	
	public int getYear() {
		return year;
	}
	
	
	public String getPublication() {
		return publication;
	}
	
	
	public int getPages() {
		return pages;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	
	public int getShelf() {
		return shelf;
	}
	
	
	public int getTerm() {
		return term;
        }
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}

    public Set<Person> getPersonsUse() {
        return personsUse;
    }

    public void setPersonsUse(Set<Person> personsUse) {
        this.personsUse = personsUse;
    }

        
        

    
        
        
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this.title.equals(((Book) obj).getTitle())
				&& this.getAuthors().equals(((Book) obj).getAuthors())
				&& this.getPublication().equals(((Book) obj).getPublication())) {
			return true;
		}
		return false;
	}
	
	@Override 
	public int hashCode() {
		return this.bId;
	}

	@Override
	public String toString() {
		return bId + " " + getTitle() + " " + getAuthors() + " " + getYear();
	}

	
}
