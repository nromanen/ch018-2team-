package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="books")
public class Book implements Serializable{
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bid", unique = true, nullable = false)
	private int bId;
        
        @NotEmpty
        @Size(max=255)
        @Column(name="title", unique = true, nullable = false)
	private String title;
        
        @NotEmpty
        @Size(max=255)
        @Column(name="authors")
	private String authors;
        
        
        @ManyToOne()
        @JoinColumn(name = "gid")
        private Genre genre;
        
        @NotNull
        @Range(min=1800, max=2030)
        @Column(name="year_public")
	private int year;
        
        @NotEmpty
        @Size(max=255)
        @Column(name="publisher")
	private String publisher;
        
        @NotNull
        @Range(min=1, max=10000)
        @Column(name="pages")
	private int pages;
        
        @Size(min=0)
        @Column(name="description")
	private String description;
        
        @Column(name = "bookcase")
        //@OneToOne(targetEntity = BookCase.class)
        //@JoinColumn(name = "caseid", referencedColumnName = "id")
        private int bookcase;
        
        @NotNull
        @Range(min=1, max=1000)
        @Column(name="shelf")
        //@OneToOne(targetEntity = BookCase.class)
        //@JoinColumn(name = "shelfid", referencedColumnName = "shelfid")
	private int shelf;
        
        @NotNull
        @Range(min=0, max=365)
        @Column(name="term")
	private int term;
        
        @Column(name = "img")
        private String img;
        
        @Range(min=0)
        @Column(name = "cur_quantity")
        private int currentQuantity;
        
        
        @NotNull
        @Range(min=1, max=1000)
        @Column(name = "gen_quantity")
       private Integer generalQuantity;
        
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
	
	
	public String getAuthors() {
		return authors;
	}
	
	
	public int getYear() {
		return year;
	}
	
	
	public String getPublisher() {
		return publisher;
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
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

    public Set<Person> getPersonsOrders() {
        return personsOrders;
    }

    public void setPersonsOrders(Set<Person> personsOrders) {
        this.personsOrders = personsOrders;
    }

    
    
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getBookcase() {
        return bookcase;
    }

    public void setBookcase(int bookcase) {
        this.bookcase = bookcase;
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

    public Set<Person> getPersonsWishes() {
        return personsWishes;
    }

    public void setPersonsWishes(Set<Person> personsWishes) {
        this.personsWishes = personsWishes;
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
				&& this.getPublisher().equals(((Book) obj).getPublisher())) {
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
