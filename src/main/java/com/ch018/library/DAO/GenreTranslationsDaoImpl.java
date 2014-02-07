package com.ch018.library.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.GenreTranslations;

@Repository
public class GenreTranslationsDaoImpl implements GenreTranslationsDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public void save(GenreTranslations genreTranslations) {
		
		factory.getCurrentSession().save(genreTranslations);
	
	}
	

	@Override
	public String getTranslationById(int id) {
		GenreTranslations genreTranslate = new GenreTranslations();
		String translate = null;
		String locale = LocaleContextHolder.getLocale().getLanguage();
		try {
			genreTranslate = (GenreTranslations) factory.getCurrentSession()
					.createCriteria(GenreTranslations.class)
					.add(Restrictions.eq("genre.id", id))
					.add(Restrictions.eq("locale", locale)).uniqueResult();
			translate = genreTranslate.getDescription();
		} catch (Exception e) {

		}
		return translate;
	}

	
}
