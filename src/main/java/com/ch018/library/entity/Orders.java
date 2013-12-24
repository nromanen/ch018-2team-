package com.ch018.library.entity;


import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Lazy(value = false)
public class Orders implements Serializable {
        
        private int id;
        private Person person;
        private Book book;
        private Date orderDate;
        
        public Orders(){
                
        }
        
        public Orders(Person person, Book book, Date date){
                this.person = person;
                this.book = book;
                this.orderDate = date;
        }
        
        @Id
        @GeneratedValue
        @Column(name = "id")
        public int getId() {
                return id;
        }
        
        public void setId(int id) {
                this.id = id;
        }
        
        @ManyToOne
        @JoinColumn(name = "bid", referencedColumnName = "bid")
        public Book getBook() {
                return book;
        }
        
        public void setBook(Book book) {
                this.book = book;
        }
        
        @ManyToOne
        @JoinColumn(name = "pid", referencedColumnName = "pid")
        public Person getPerson() {
                return person;
        }
        
        public void setPerson(Person person) {
                this.person = person;
        }
        
        @Column(name = "order_date")
        @Temporal(TemporalType.TIMESTAMP)
        public Date getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
           return person.getPid() + " " + book.getbId() + " " + orderDate;
        }
        
}
