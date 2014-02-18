package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Book")
public class Book implements Serializable {
	

		private static final long serialVersionUID = -7141788634638340212L;
		
		private static final int MAX_NAME = 255;
		private static final int MIN_YEAR = 1900;
		private static final int MAX_YEAR = 2030;
		private static final int MAX_PAGES = 10000;
		private static final int MAX_SHELF = 1000;
		private static final int MAX_QUANTITY = 1000;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "bookId", unique = true, nullable = false)
		private int bId;
	
		@NotEmpty
		@Size(max = MAX_NAME)
		@Column(name = "title", unique = true, nullable = false)
		private String title;
	
		
		@Size(max = MAX_NAME)
		@Column(name = "authors")
		private String authors;
	
		@NotNull
		@Range(min = MIN_YEAR, max = MAX_YEAR)
		@Column(name = "yearPublic")
		private int year;
	
		
		@Size(max = MAX_NAME)
		@Column(name = "publisher")
		private String publisher;
	
		@NotNull
		@Range(min = 1, max = MAX_PAGES)
		@Column(name = "pages")
		private int pages;
	
		@Size(min = 0)
		@Column(name = "description", columnDefinition="TEXT")
		private String description;
	
		@Column(name = "bookcase")
		private int bookcase;
		
		
		@ManyToOne()
		@JoinColumn(name = "genreId")
		private Genre genre;
	
		@NotNull
		@Range(min = 1, max = MAX_SHELF)
		@Column(name = "shelf")
		private int shelf;
	
		@Column(name = "img")
		private String img = "resources/img/default.jpg";
	
		@Range(min = 0)
		@Column(name = "currentQuantity")
		private int currentQuantity;
	
		@NotNull
		@Range(min = 0, max = MAX_QUANTITY)
		@Column(name = "generalQuantity")
		private Integer generalQuantity;
		
		@Column(name = "arrivalDate")
		@Temporal(TemporalType.TIMESTAMP)
		private Date arrivalDate = new Date();
		
		@Column(name = "rating")
		Float rating = 0F;
		
		@Column(name = "votes")
		Integer votes = 0;
	
		@OneToMany(mappedBy = "book")
		private Set<BooksInUse> bookUses;
	
		@OneToMany(mappedBy = "book")
		private Set<Orders> bookOrders;
	
		@OneToMany(mappedBy = "book")
		private Set<WishList> bookWishes;
		
		@OneToMany(mappedBy = "book")
		private Set<Rate> rates;
	
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

		public Float getRating() {
			return rating;
		}

		public void setRating(Float rating) {
			this.rating = rating;
		}

		public Integer getVotes() {
			return votes;
		}

		public void setVotes(Integer votes) {
			this.votes = votes;
		}

		public Set<BooksInUse> getBookUses() {
			return bookUses;
		}

		public void setBookUses(Set<BooksInUse> bookUses) {
			this.bookUses = bookUses;
		}

		public Set<Orders> getBookOrders() {
			return bookOrders;
		}

		public void setBookOrders(Set<Orders> bookOrders) {
			this.bookOrders = bookOrders;
		}

		public Set<WishList> getBookWishes() {
			return bookWishes;
		}

		public void setBookWishes(Set<WishList> bookWishes) {
			this.bookWishes = bookWishes;
		}

		public Set<Rate> getRates() {
			return rates;
		}

		public void setRates(Set<Rate> rates) {
			this.rates = rates;
		}

		@Override
		public String toString() {
			return "Book [bId=" + bId + ", title=" + title + ", authors="
					+ authors + ", genre=" + genre + ", year=" + year
					+ ", publisher=" + publisher + ", pages=" + pages
					+ ", bookcase=" + bookcase + ", shelf=" + shelf  + ", currentQuantity=" + currentQuantity
					+ ", generalQuantity=" + generalQuantity + ", arrivalDate="
					+ arrivalDate + "]";
		}

		

}
