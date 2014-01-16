/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Genre;


/**
 *
 * @author Edd Arazian 
 */
@Repository
public interface GenreDao {
    
        void save(Genre genre);
        void update(Genre genre);
        void update(int id, Genre genre);
        void delete(Genre genre);
        List<Genre> getAll();
        Genre getById(int id);
        Genre getByDescription(String description); 
}
