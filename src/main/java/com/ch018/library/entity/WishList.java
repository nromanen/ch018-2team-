/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "wishlist", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "bid" } ) })
public class WishList implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne()//targetEntity = Person.class)
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private Person person;
    
    @ManyToOne()//targetEntity = Book.class)
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    private Book book;
    
    public WishList() {
        
    }

    public WishList(Person person, Book book) {
        this.person = person;
        this.book = book;
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
    
    
    
    
}
