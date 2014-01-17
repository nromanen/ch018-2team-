package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreDao;
import com.ch018.library.entity.Genre;

/**
 *
 * @author Edd ARazian
 */
@Service
public class GenreServiceImpl implements GenreService {
    
        @Autowired
        private GenreDao genreDao;

        @Override
        @Transactional
        public void save(Genre genre) {
            genreDao.save(genre);
        }

        @Override
        @Transactional
        public void update(Genre genre) {
            genreDao.update(genre);
        }

        @Override
        @Transactional
        public void update(int id, Genre genre) {
            genreDao.update(id, genre);
        }

        @Override
        @Transactional
        public void delete(Genre genre) {
            genreDao.delete(genre);
        }

        @Override
        @Transactional
        public List<Genre> getAll() {
            return genreDao.getAll();
        }

        @Override
        @Transactional
        public Genre getById(int id) {
            return genreDao.getById(id);
        }

        @Override
        @Transactional
        public Genre getByDescription(String description) {
            return genreDao.getByDescription(description);
        }
}
