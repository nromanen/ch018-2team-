package com.ch018.library.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.GenreTranslations;
import com.sun.org.apache.regexp.internal.recompile;

@Repository
public class GenreTranslationsDAOImpl implements GenreTranslationDAO {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public void save(GenreTranslations genreTranslations) {
		factory.getCurrentSession().save(genreTranslations);
	}

	@Override
	public void delete(GenreTranslations genreTranslations) {
		factory.getCurrentSession().delete(genreTranslations);
	}

	@Override
	public void update(GenreTranslations genreTranslations) {
		factory.getCurrentSession().update(genreTranslations);
	}

	@Override
	public String getDescription(Book book, Locale locale) {
		
		Set<GenreTranslations> genres = book.getGenre();
		String translation = "";
		
		for (GenreTranslations genreTranslations : genres) {
			if (genreTranslations.getLanguage().equals(locale.toString())) {
				translation = genreTranslations.getGenreTranslation();
			}
		}
		
		return translation;
	}

	@Override
	public List<GenreTranslations> getAllByLocale(String locale) {
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(GenreTranslations.class);
		criteria.add(Restrictions.eq("lang", locale));
		List<GenreTranslations> genrTransl = criteria.list();
		
		if (session.isOpen()) {
			session.close();
		}
		
		return genrTransl;
	}

	@Override
	public GenreTranslations getById(int id) {
		return (GenreTranslations) factory.getCurrentSession().get(GenreTranslations.class, id);
	}

	@Override
	public Set<GenreTranslations> getByGenreId(int genreId) {
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(GenreTranslations.class);
		criteria.add(Restrictions.eq("genreId", genreId));
		List<GenreTranslations> genrTransl = criteria.list();
		
		Set<GenreTranslations> genreTransl = new HashSet<>();
		for (GenreTranslations genreTranslations : genrTransl) {
			genreTransl.add(genreTranslations);
		}
		
		if (session.isOpen()) {
			session.close();
		}
		
		return genreTransl;
	}

}
