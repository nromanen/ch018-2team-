package com.ch018.library.DAO;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.GenreTranslations;

@Repository
public interface GenreTranslationsDao {

	void save(GenreTranslations genreTranslations);

	void deleteByGenreId(int id);
	
	String getTranslationById(int id);

	String getTranslationById(int id, String locale);
	
	GenreTranslations getByDescription(String description);
	
	String getDescription(Integer genreId);
}
