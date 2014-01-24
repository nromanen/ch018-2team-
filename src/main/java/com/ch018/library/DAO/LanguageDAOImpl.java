package com.ch018.library.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Language;

@Repository
public class LanguageDAOImpl implements LanguagesDAO{

	@Autowired
	private SessionFactory factory;
	
	@Override
	public void save(Language language) {
		try {
			factory.getCurrentSession().save(language);
		} catch (Exception e) {
			System.out.println("Error saving Language" + e);
		}
	}

	@Override
	public void delete(Language language) {
		try {
			factory.getCurrentSession().delete(language);
		} catch (Exception e) {
			System.out.println("Error deleting Language");
		}
	}

	@Override
	public void update(Language language) {
		try {
			factory.getCurrentSession().update(language);
		} catch (Exception e) {
			System.out.println("Error update Language");
		}
	}

	@Override
	public Language getById(int id) {
		return (Language) factory.getCurrentSession().get(Language.class, id);
	}

	@Override
	public Language getByValue(String value) {
		return (Language) factory.getCurrentSession().createCriteria(Language.class)
				.add(Restrictions.eq("language", value));
	}

}
