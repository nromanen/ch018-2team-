package com.ch018.library.util;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.entity.Genre;
import com.ch018.library.service.GenreTranslationsService;

@Service
public class Intercept extends EmptyInterceptor {

	@Autowired
	GenreTranslationsService translationService;
	
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		
		if(entity instanceof Genre) {
			
			Genre genre = (Genre) entity;
			state[1] = translationService.getTranslationById(genre.getId());



		}
		return true;
		
	}

	
	
}
