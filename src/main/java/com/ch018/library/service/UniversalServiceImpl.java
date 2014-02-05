package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.UniversalDao;
import com.ch018.library.util.SearchParams;

@Service
public class UniversalServiceImpl<T> implements UniversalService<T> {

	@Autowired
	UniversalDao<T> universalDao;
	
	@Override
	@Transactional
	public List<T> getPaginatedResult(SearchParams searchParams, Class<?> entity) {
		return universalDao.getPaginatedResult(searchParams, entity);
	}

	
	
}
