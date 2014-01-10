package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Genre;
/**
 *
 * @author Edd Arazian
 */
@Service
public interface GenreService {
        void save(Genre genre);
        void update(Genre genre);
        void update(int id, Genre genre);
        void delete(Genre genre);
        List<Genre> getAll();
        Genre getById(int id);
        Genre getByDescription(String description); 
}
