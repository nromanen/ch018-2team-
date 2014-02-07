package com.ch018.library.service;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.GenreTranslations;

@Service
public interface GenreTranslationsService {

	void save(GenreTranslations genreTranslations);
	
	String getTranslationById(int id);
}
