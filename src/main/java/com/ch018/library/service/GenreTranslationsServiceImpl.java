package com.ch018.library.service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreTranslationDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.GenreTranslations;

@Service
public class GenreTranslationsServiceImpl implements GenreTranslationService {

	@Autowired
	private GenreTranslationDAO gtransDAO;
	
	@Override
	@Transactional
	public void save(GenreTranslations genreTranslations) {
		gtransDAO.save(genreTranslations);
	}

	@Override
	@Transactional
	public void delete(GenreTranslations genreTranslations) {
		gtransDAO.delete(genreTranslations);
	}

	@Override
	@Transactional
	public void update(GenreTranslations genreTranslations) {
		gtransDAO.update(genreTranslations);
	}

	@Override
	public String getDescription(Book book, Locale locale) {
		return gtransDAO.getDescription(book, locale);
	}

	@Override
	public List<GenreTranslations> getAllByLocale(String locale) {
		return gtransDAO.getAllByLocale(locale);
	}

	@Override
	@Transactional
	public GenreTranslations getById(int id) {
		return gtransDAO.getById(id);
	}

	@Override
	public Set<GenreTranslations> getByGenreId(int genreId) {
		return gtransDAO.getByGenreId(genreId);
	}

}
