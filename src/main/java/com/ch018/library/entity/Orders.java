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
@Table(name = "orders", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "bid" } ) })
public class Orders implements Serializable {
        
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
        
        @Column(name = "order_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date orderDate;
        
        @Column(name = "changed")
        private boolean changed;
        
        @Column(name = "daysamount")
        private long daysAmount;
        
        public Orders() {
                
        }
        
        public Orders(Person person, Book book, Date date) {
                this.person = person;
                this.book = book;
                this.orderDate = date;
                this.changed = false;
        }

        public Orders(Person person, Book book, Date orderDate, long daysAmount) {
            this.person = person;
            this.book = book;
            this.orderDate = orderDate;
            this.daysAmount = daysAmount;
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

        public boolean isChanged() {
            return changed;
        }

        public void setChanged(boolean changed) {
            this.changed = changed;
        }

        public long getDaysAmount() {
            return daysAmount;
        }

        public void setDaysAmount(long daysAmount) {
            this.daysAmount = daysAmount;
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
			return "Orders [id=" + id + ", person=" + person + ", book=" + book
					+ ", orderDate=" + orderDate + ", changed=" + changed
					+ ", daysAmount=" + daysAmount + "]";
		}

        
        
        
}
