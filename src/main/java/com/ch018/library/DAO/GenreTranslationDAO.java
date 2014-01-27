package com.ch018.library.DAO;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.GenreTranslations;

@Repository
public interface GenreTranslationDAO {
	
	void save(GenreTranslations genreTranslations);
    void delete(GenreTranslations genreTranslations);
    void update(GenreTranslations genreTranslations);
    String getDescription(Book book, Locale locale);
    List<GenreTranslations> getAllByLocale(String locale); 
    GenreTranslations getById(int id);
    Set<GenreTranslations> getByGenreId(int genreId);

}
