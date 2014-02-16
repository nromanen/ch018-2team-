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
@Table(name = "Orders", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "personId", "bookId" } ) })
public class Orders implements Serializable {

		private static final long serialVersionUID = 9045662704362349782L;

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
        
        @Column(name = "orderDate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date orderDate;
        
        @Column(name = "returnDate")
        @Temporal(TemporalType.TIMESTAMP)
        private Date returnDate;
        
        public Orders() {
                
        }
        
   

        public Orders(Person person, Book book, Date orderDate, Date returnDate) {
            this.person = person;
            this.book = book;
            this.orderDate = orderDate;
            this.returnDate = returnDate;
        }
        
        public int getId() {
                return id;
        }
        
        public void setId(int id) {
                this.id = id;
        }
        
        
        public Book getBook() {
                return book;
        }
        
        public void setBook(Book book) {
                this.book = book;
        }
        
        
        public Person getPerson() {
                return person;
        }
        
        public void setPerson(Person person) {
                this.person = person;
        }
        
        
        public Date getOrderDate() {
            return orderDate;
        }        
        

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

		public Date getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(Date returnDate) {
			this.returnDate = returnDate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + id;
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
			if (!(obj instanceof Orders))
				return false;
			Orders other = (Orders) obj;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (id != other.id)
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
			return "Orders [id=" + id + ", person=" + person.getPid() + ", book=" + book.getbId()
					+ ", orderDate=" + orderDate 
					+ ", returnDate=" + returnDate + "]";
		}

        
        
        
}
