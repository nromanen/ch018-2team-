/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Genre;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class GenreDaoImpl implements GenreDao {

        @Autowired
        private SessionFactory factory;
        private final Logger logger = LoggerFactory.getLogger(GenreDaoImpl.class);

        @Override
        public void save(Genre genre) {
            try {
                factory.getCurrentSession().save(genre);
            } catch (Exception e) {
                logger.error("error during save genre {}", e.getMessage());
            }
        }

        @Override
        public void update(Genre genre) {
            try {
                factory.getCurrentSession().update(genre);
            } catch (Exception e) {
                logger.error("error during update genre {}", e.getMessage());
            }
        }

        @Override
        public void update(int id, Genre genre) {
            Genre tmp;
            tmp = (Genre) factory.getCurrentSession().get(Genre.class, id);
            tmp.setDescription(genre.getDescription());
            factory.getCurrentSession().update(tmp);
        }

        @Override
        public void delete(Genre genre) {
            factory.getCurrentSession().delete(genre);
        }

        @Override
        public List<Genre> getAll() {
        	String local = LocaleContextHolder.getLocale().getLanguage();
    		
        	
        	
            List<Genre> genres = factory.getCurrentSession().createCriteria(Genre.class, "genre")
            		.createAlias("genre.translations", "gt")
            		.add(Restrictions.eq("gt.locale", local))
            		.list();
            for(Genre g : genres)
            	System.out.println(g);
            return genres;
        }

        @Override
        public Genre getById(int id) {
            return (Genre) factory.getCurrentSession().get(Genre.class, id);
        }

        @Override
        public Genre getByDescription(String description) {
            return (Genre) factory.getCurrentSession().createCriteria(Genre.class).add(Restrictions.eq("description", description)).uniqueResult();
        }
}
