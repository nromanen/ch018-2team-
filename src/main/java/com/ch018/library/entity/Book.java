package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "books")
@Proxy(lazy = false)
public class Book implements Serializable {
	
		private static final int MAX_NAME = 255;
		private static final int MIN_YEAR = 1800;
		private static final int MAX_YEAR = 2030;
		private static final int MAX_PAGES = 10000;
		private static final int MAX_SHELF = 1000;
		private static final int MAX_QUANTITY = 1000;
		private static final int MAX_TERM = 365;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "bid", unique = true, nullable = false)
		private int bId;
	
		@NotEmpty
		@Size(max = MAX_NAME)
		@Column(name = "title", unique = true, nullable = false)
		private String title;
	
		
		@Size(max = MAX_NAME)
		@Column(name = "authors")
		private String authors;
		
		@ManyToMany(fetch=FetchType.EAGER)
		@JoinTable(name = "book_genre", joinColumns = {@JoinColumn(name = "bId")}, inverseJoinColumns = {@JoinColumn(name = "gid")})
		private Set<GenreTranslations> genreNew;
	
		@NotNull
		@Range(min = MIN_YEAR, max = MAX_YEAR)
		@Column(name = "year_public")
		private int year;
	
		
		@Size(max = MAX_NAME)
		@Column(name = "publisher")
		private String publisher;
	
		@NotNull
		@Range(min = 1, max = MAX_PAGES)
		@Column(name = "pages")
		private int pages;
	
		@Size(min = 0)
		@Column(name = "description")
		private String description;
	
		@Column(name = "bookcase")
		private int bookcase;
		
		
		@ManyToOne()
		@JoinColumn(name = "gid")
		private Genre genre;
	
		@NotNull
		@Range(min = 1, max = MAX_SHELF)
		@Column(name = "shelf")
		private int shelf;
	
		@NotNull
		@Range(min = 0, max = MAX_TERM)
		@Column(name = "term")
		private int term;
	
		@Column(name = "img")
		private String img = "http://placehold.it/480x480";
	
		@Range(min = 0)
		@Column(name = "cur_quantity")
		private int currentQuantity;
	
		@NotNull
		@Range(min = 0, max = MAX_QUANTITY)
		@Column(name = "gen_quantity")
		private Integer generalQuantity;
		
		@Column(name = "arrival_date")
		@Temporal(TemporalType.TIMESTAMP)
		private Date arrivalDate = new Date();
		
		@Column(name = "orders_quantity")
		private Integer ordersQuantity = 0;
	
		@OneToMany(targetEntity = BooksInUse.class, mappedBy = "book")
		private Set<Person> personsUse;
	
		@OneToMany(targetEntity = Orders.class, mappedBy = "book")
		private Set<Person> personsOrders;
	
		@OneToMany(targetEntity = WishList.class, mappedBy = "book")
		private Set<Person> personsWishes;
	
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

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthors() {
			return authors;
		}

		public void setAuthors(String authors) {
			this.authors = authors;
		}

		public Set<GenreTranslations> getGenreNew() {
			return genreNew;
		}

		public void setGenre(Set<GenreTranslations> genreNew) {
			this.genreNew = genreNew;
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getBookcase() {
			return bookcase;
		}

		public void setBookcase(int bookcase) {
			this.bookcase = bookcase;
		}

		public Genre getGenre() {
			return genre;
		}

		public void setGenre(Genre genre) {
			this.genre = genre;
		}

		public int getShelf() {
			return shelf;
		}

		public void setShelf(int shelf) {
			this.shelf = shelf;
		}

		public int getTerm() {
			return term;
		}

		public void setTerm(int term) {
			this.term = term;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public int getCurrentQuantity() {
			return currentQuantity;
		}

		public void setCurrentQuantity(int currentQuantity) {
			this.currentQuantity = currentQuantity;
		}

		public Integer getGeneralQuantity() {
			return generalQuantity;
		}

		public void setGeneralQuantity(Integer generalQuantity) {
			this.generalQuantity = generalQuantity;
		}

		public Date getArrivalDate() {
			return arrivalDate;
		}

		public void setArrivalDate(Date arrivalDate) {
			this.arrivalDate = arrivalDate;
		}

		public Integer getOrdersQuantity() {
			return ordersQuantity;
		}

		public void setOrdersQuantity(Integer ordersQuantity) {
			this.ordersQuantity = ordersQuantity;
		}

		public Set<Person> getPersonsUse() {
			return personsUse;
		}

		public void setPersonsUse(Set<Person> personsUse) {
			this.personsUse = personsUse;
		}

		public Set<Person> getPersonsOrders() {
			return personsOrders;
		}

		public void setPersonsOrders(Set<Person> personsOrders) {
			this.personsOrders = personsOrders;
		}

		public Set<Person> getPersonsWishes() {
			return personsWishes;
		}

		public void setPersonsWishes(Set<Person> personsWishes) {
			this.personsWishes = personsWishes;
		}

		@Override
		public String toString() {
			return "Book [bId=" + bId + ", title=" + title + ", authors="
					+ authors + ", genre=" + genre + ", year=" + year
					+ ", publisher=" + publisher + ", pages=" + pages
					+ ", bookcase=" + bookcase + ", shelf=" + shelf + ", term="
					+ term + ", currentQuantity=" + currentQuantity
					+ ", generalQuantity=" + generalQuantity + ", arrivalDate="
					+ arrivalDate + ", ordersQuantity=" + ordersQuantity + "]";
		}

		

}
