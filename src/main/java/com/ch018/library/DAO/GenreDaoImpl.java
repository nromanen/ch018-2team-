/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Genre;
import com.ch018.library.util.HibernateUtil;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edd Arazian
 */
@Repository
public class GenreDaoImpl implements GenreDao {

    static Logger log = LogManager.getLogger(GenreDaoImpl.class);
    
    @Autowired
    SessionFactory factory;
    
    @Override
    public void save(Genre genre) {
             factory.getCurrentSession().save(genre);
            
        
    }

    @Override
    public void update(Genre genre) {
            factory.getCurrentSession().update(genre);
            
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
            return factory.getCurrentSession().createCriteria(Genre.class).list();
            
    }

    @Override
    public Genre getById(int id) {
        
            return (Genre) factory.getCurrentSession().get(Genre.class, id);
            
    }

    @Override
    public Genre getByDescription(String description) {
    
            return (Genre) factory.getCurrentSession().createQuery("from Genre where description = :d").
                    setString("d", description).list().get(0);
            
    }
    
    
    
}
