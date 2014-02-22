package com.ch018.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreTranslationsDao;
import com.ch018.library.entity.GenreTranslations;


@Service
public class GenreTranslationsServiceImpl implements GenreTranslationsService {

	@Autowired
	GenreTranslationsDao genreTranslationsDao;
	
	@Override
	@Transactional
	public void save(GenreTranslations genreTranslations) {
		
		genreTranslationsDao.save(genreTranslations);
		
	}

	@Override
	@Transactional
	public String getTranslationById(int id) {
		return genreTranslationsDao.getTranslationById(id);
	}

	@Override
	public String getDescription(Integer genreId) {
		
		return genreTranslationsDao.getDescription(genreId);
	}

	
	
}
