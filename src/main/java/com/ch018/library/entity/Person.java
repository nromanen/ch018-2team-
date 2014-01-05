package com.ch018.library.entity;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 *
 * @author Edd Arazian
 */

enum Roles{ROLE_ADMIN, ROLE_LIBRARIAN, ROLE_USER}

@Entity
@Table(name = "persons")
@Proxy(lazy = false)
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "salt")
    private String salt;
    
    @Column(name = "prole")
    private String prole;
    
    @Column(name = "cellphone")
    private String cellphone;
    
    @Column(name = "confirm")
    private boolean confirm;
    
    @Column(name = "sms")
    private boolean sms;
    
    @Column(name = "multi")
    private Integer multiBook;
    
    @Column(name = "timelyreturn")
    private int timelyReturn;
    
    @Column(name = "untimelyreturn")
    private int untimekyReturn;
    
    @Column(name = "booksallowed")
    private int booksAllowed;
    
    @Column(name = "failedorders")
    private int failedOrders;
    
    @Column(name = "generalratio")
    private float generalRating;
    
    @Column(name = "img")
    private String img;
    
    @OneToMany(targetEntity = BooksInUse.class, mappedBy = "person")
    private Set<Book> booksInUse = new HashSet<Book>();
    
    @OneToMany(targetEntity = Orders.class, mappedBy = "person")
    private Set<Book> orders = new HashSet<>();
    
    @OneToMany(targetEntity = WishList.class, mappedBy = "person")
    private Set<Book> wishes = new HashSet<>();
    
    
    public Person() {
        
    }

    public Person(String email) {
        this.email = email;
    }

    public Person(String name, String surname, String email, String password, String cellphone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
    }

    
    public int getPid() {
        return pid;
    }

    public void setPid(int id) {
        this.pid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public String getProle() {
        return prole;
    }

    public void setProle(String prole) {
        this.prole = Roles.valueOf(prole).toString();
    }

    

    public int getTimelyReturn() {
        return timelyReturn;
    }

    public void setTimelyReturn(int timelyReturn) {
        this.timelyReturn = timelyReturn;
    }

    public int getUntimekyReturn() {
        return untimekyReturn;
    }

    public void setUntimekyReturn(int untimekyReturn) {
        this.untimekyReturn = untimekyReturn;
    }

    public int getBooksAllowed() {
        return booksAllowed;
    }

    public void setBooksAllowed(int booksAllowed) {
        this.booksAllowed = booksAllowed;
    }

    public int getFailedOrders() {
        return failedOrders;
    }

    public void setFailedOrders(int failedOrders) {
        this.failedOrders = failedOrders;
    }

    public float getGeneralRating() {
        return generalRating;
    }

    public void setGeneralRating(float generalRating) {
        this.generalRating = generalRating;
    }

    public Set<Book> getBooksInUse() {
        return booksInUse;
    }

    public void setBooksInUse(Set<Book> booksInUse) {
        this.booksInUse = booksInUse;
    }

    public Set<Book> getOrders() {
        return orders;
    }

    public void setOrders(Set<Book> orders) {
        this.orders = orders;
    }

    public Set<Book> getWishes() {
        return wishes;
    }

    public void setWishes(Set<Book> wishes) {
        this.wishes = wishes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMultiBook() {
        return multiBook;
    }

    public void setMultiBook(int multiBook) {
        this.multiBook = multiBook;
    }
    
    
    
    
    
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Person))return false;
        Person otherPerson = (Person) other;
        return email.equals(otherPerson.getEmail());
    }
    
    @Override
    public String toString() {
        return this.pid + " " + " " + email;
    }

}
