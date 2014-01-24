package com.ch018.library.DAO;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Language;

@Repository
public interface LanguagesDAO {

	void save(Language language);
	void delete(Language language);
	void update(Language language);
	Language getById(int id);
	Language getByValue(String value);
	
}
