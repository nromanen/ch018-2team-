/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import javax.persistence.*;

/**
 *
 * @author earaztc
 */
@Entity
@Table(name = "genres")
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne()
    @JoinColumn(name = "bid", referencedColumnName = "bid")
    private Book book;
    
    
    
}
