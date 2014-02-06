package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.util.ReflectionUtils;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.Switch;

@Repository
public class UniversalDaoImpl<T> implements UniversalDao<T> {

	
	private final Logger logger = LoggerFactory.getLogger(UniversalDao.class);
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ReflectionUtils reflectionUtils;
	
	
	@Override
	public List<T> getPaginatedResult(SearchParams search, Class<T> entity) {
		
		Criteria criteria = factory.getCurrentSession().createCriteria(entity);
		
		reflectionUtils.fillCriteria(criteria, search);
		
		return  criteria.list();
	}

	
}
