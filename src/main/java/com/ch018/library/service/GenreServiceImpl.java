/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.DAO.GenreDao;
import com.ch018.library.entity.Genre;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Edd ARazian
 */
@Service
public class GenreServiceImpl implements GenreService {
    
    @Autowired
    GenreDao gDao;

    @Override
    @Transactional
    public void save(Genre genre) {
        gDao.save(genre);
    }

    @Override
    @Transactional
    public void update(Genre genre) {
        gDao.update(genre);
    }

    @Override
    @Transactional
    public void update(int id, Genre genre) {
        gDao.update(id, genre);
    }

    @Override
    @Transactional
    public void delete(Genre genre) {
        gDao.delete(genre);
    }

    @Override
    @Transactional
    public List<Genre> getAll() {
        return gDao.getAll();
    }

    @Override
    @Transactional
    public Genre getById(int id) {
        return gDao.getById(id);
    }

    @Override
    @Transactional
    public Genre getByDescription(String description) {
        return gDao.getByDescription(description);
    }
    
    
    
}
