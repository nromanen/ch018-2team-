package com.ch018.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PaginationDao;
import com.ch018.library.util.PageContainer;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.SearchParamsRate;
import com.ch018.library.util.Switch;

@Service
public class PaginationServiceImpl<T> implements PaginationService<T> {

	private final Logger logger = LoggerFactory.getLogger(PaginationServiceImpl.class);
	
	@Autowired
	PaginationDao<T> paginationDao;
	
	@Autowired
	Switch switcher;
	
	@Autowired
	PageContainer<T> pageContainer;
	
		
	@Override
	@Transactional
	public List<T> getPaginatedResult(SearchParams searchParams, Class<T> entity) {
		return paginationDao.getPaginatedResult(searchParams, entity);
	}


	@Override
	@Transactional
	public List<T> getPaginatedResult(SearchParams searchParams, SearchParams tmpSearchParams, Class<T> entity) {
		
		List<T> items = null;
		
		if(switcher.getSwitcher()) {
			if(searchParams.isOnlyPageChangedOrSize(tmpSearchParams)) {
				logger.info("only page");
				searchParams.update(tmpSearchParams);
				items = pageContainer.getItemsPart(searchParams, entity);
			} else if(searchParams.isSortingFieldsChanged(tmpSearchParams)) {
				searchParams.update(tmpSearchParams);
				pageContainer.recalculateLocal(searchParams, entity);
				items = pageContainer.getItemsPart(searchParams, entity);	
			} else {
				searchParams.update(tmpSearchParams);
	    		items = paginationDao.getPaginatedResult(searchParams, entity);
	    		pageContainer.setItems(items);
	    		items = pageContainer.getItemsPart(searchParams, entity);
			}
		} else {
			searchParams.update(tmpSearchParams);
			items = paginationDao.getPaginatedResult(searchParams, entity);
		}
		
		
		return items;
	}

	
	
}
