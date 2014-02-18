package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreDao;
import com.ch018.library.DAO.GenreTranslationsDao;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.GenreTranslations;
import com.ch018.library.exceptions.EmptyGenreException;
import com.ch018.library.exceptions.GenreAlreadyExists;

/**
 *
 * @author Edd ARazian
 */
@Service
public class GenreServiceImpl implements GenreService {
    
        @Autowired
        private GenreDao genreDao;
        
        @Autowired 
        private GenreTranslationsDao translationDao;
        

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
        	translationDao.deleteByGenreId(genre.getId());
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

		@Override
		@Transactional
		public void addGenre(String eng, String ukr) throws Exception {
			

			if(eng == null || eng.equals(""))
				throw new EmptyGenreException();
			
			if(ukr == null || ukr.equals(""))
				ukr = eng;

			if(getByDescription(eng) != null)
				throw new GenreAlreadyExists();
			
			Genre genre = new Genre();
			GenreTranslations engTranslation = new GenreTranslations();
			GenreTranslations ukrTranslation = new GenreTranslations();
			
			genre.setDescription(eng);
			save(genre);
			
			System.out.println(genre.getId());
			
			engTranslation.setDescription(eng);
			engTranslation.setLocale("en");
			engTranslation.setGenre(genre);
			
			translationDao.save(engTranslation);
			
			ukrTranslation.setDescription(ukr);
			ukrTranslation.setLocale("ua");
			ukrTranslation.setGenre(genre);
			
			translationDao.save(ukrTranslation);
				
		}
        
        
}
