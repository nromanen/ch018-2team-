package com.ch018.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.LanguagesDAO;
import com.ch018.library.entity.Language;

@Service
public class LanguageServiceImpl implements LanguageService{

	@Autowired
	private LanguagesDAO langDAO;
	
	@Override
	@Transactional
	public void save(Language language) {
		langDAO.save(language);
	}

	@Override
	@Transactional
	public void delete(Language language) {
		langDAO.delete(language);
	}

	@Override
	@Transactional
	public void update(Language language) {
		langDAO.update(language);
	}

	@Override
	@Transactional
	public Language getById(int id) {
		return langDAO.getById(id);
	}

	@Override
	@Transactional
	public Language getByValue(String value) {
		return langDAO.getByValue(value);
	}

}
