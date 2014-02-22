package com.ch018.library.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.GenreTranslations;

@Repository
public class GenreTranslationsDaoImpl implements GenreTranslationsDao {

	private static final String DEFAULT_LANGUAGE = "en";

	private Logger logger = LoggerFactory.getLogger(GenreTranslationsDaoImpl.class);

	@Autowired
	SessionFactory factory;

	@Override
	public void save(GenreTranslations genreTranslations) {

		factory.getCurrentSession().save(genreTranslations);

	}


	@Override
	public void deleteByGenreId(int id) {
		Session session = factory.getCurrentSession();
		String hql = "delete from GenreTranslations where genre.id= :id";
		session.createQuery(hql).setInteger("id", id).executeUpdate();
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
			if(genreTranslate == null) {
				getTranslationById(id, DEFAULT_LANGUAGE);
			}
			translate = genreTranslate.getDescription();

		} catch (Exception e) {
			logger.error("{}", e.getMessage());
		}
		return translate;
	}


	@Override
	public String getTranslationById(int id, String locale) {
		GenreTranslations genreTranslate = new GenreTranslations();
		String translate = null;
		try {
			genreTranslate = (GenreTranslations) factory.getCurrentSession()
					.createCriteria(GenreTranslations.class)
					.add(Restrictions.eq("genre.id", id))
					.add(Restrictions.eq("locale", locale)).uniqueResult();
			translate = genreTranslate.getDescription();

		} catch (Exception e) {
			logger.error("{}", e.getMessage());
		}
		return translate;
	}


	@Override
	public GenreTranslations getByDescription(String description) {
		
		Criteria criteria = factory.getCurrentSession().createCriteria(GenreTranslations.class);
		criteria.add(Restrictions.eq("description", description));
		
		return (GenreTranslations) criteria.uniqueResult();
	}


	@Override
	public String getDescription(Integer genreId) {
		String locale = LocaleContextHolder.getLocale().getLanguage();
		Criteria criteria = factory.getCurrentSession().createCriteria(GenreTranslations.class);
		criteria.add(Restrictions.eq("genre.id", genreId));
		criteria.add(Restrictions.eq("locale", locale));
		
		GenreTranslations translation = (GenreTranslations) criteria.uniqueResult();
		String description = translation.getDescription();
		
		return description;
	}

	

}