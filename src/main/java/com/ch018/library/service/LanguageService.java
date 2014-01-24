package com.ch018.library.service;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Language;

@Service
public interface LanguageService {
	
	void save(Language language);
	void delete(Language language);
	void update(Language language);
	Language getById(int id);
	Language getByValue(String value);
}
