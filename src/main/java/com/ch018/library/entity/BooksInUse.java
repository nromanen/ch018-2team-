package com.ch018.library.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;


@Entity
@Table(name="BooksInUse",
        uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "bid" } ) })
public class BooksInUse implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;
        
        
        @ManyToOne()//targetEntity = Person.class)
        @JoinColumn(name = "pid", referencedColumnName = "pid")
        private Person person;
        
        @ManyToOne()//targetEntity = Book.class)
        @JoinColumn(name = "bid", referencedColumnName = "bid")
        private Book book;

        @Temporal(value = TemporalType.TIMESTAMP)
        @Column(name = "issue_date")
        private Date issueDate;
        
        @Temporal(value = TemporalType.TIMESTAMP)
        @Column(name = "return_date")
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



        public Date getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(Date issueDate) {
            this.issueDate = issueDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

   
        
        
        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            if (!(other instanceof BooksInUse))return false;
            BooksInUse otherBookInUse = (BooksInUse) other;
            return person.getPid() == otherBookInUse.person.getPid() 
                    && book.getbId() == otherBookInUse.book.getbId();
        }
        
        @Override
        public int hashCode() {
            return person.hashCode() + book.hashCode() + returnDate.hashCode();
        }
       
        @Override 
        public String toString() {
            return person.getPid() + " " + book.getbId() + " " + returnDate;
        }

	
}
