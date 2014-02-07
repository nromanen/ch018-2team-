package com.ch018.library.DAO;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.GenreTranslations;

@Repository
public interface GenreTranslationsDao {

	void save(GenreTranslations genreTranslations);

	String getTranslationById(int id);
}
