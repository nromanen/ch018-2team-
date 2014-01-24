package com.ch018.library.service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.GenreTranslations;

@Service
public interface GenreTranslationService {

	void save(GenreTranslations genreTranslations);
    void delete(GenreTranslations genreTranslations);
    void update(GenreTranslations genreTranslations);
    String getDescription(Book book, Locale locale);
    List<GenreTranslations> getAllByLocale(String locale); 
    GenreTranslations getById(int id);
    Set<GenreTranslations> getByGenreId(int genreId);
}
