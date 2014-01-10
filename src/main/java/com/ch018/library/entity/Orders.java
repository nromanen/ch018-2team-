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
import org.springframework.context.annotation.Lazy;



import org.hibernate.annotations.Proxy;


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
        
        public Orders(){
                
        }
        
        public Orders(Person person, Book book, Date date){
                this.person = person;
                this.book = book;
                this.orderDate = date;
                this.changed = false;
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

        
        public Boolean getChanged() {
            return changed;
        }

        public void setChanged(Boolean changed) {
            this.changed = changed;
        }
        
        

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
           return person.getPid() + " " + book.getbId() + " " + orderDate;
        }
        
}
