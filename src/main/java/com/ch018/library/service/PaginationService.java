package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.util.SearchParams;

@Service
public interface PaginationService<T> {
	
	
	List<T> getPaginatedResult(SearchParams searchParams, Class<T> entity);
	
	List<T> getPaginatedResult(SearchParams searchParams, SearchParams tmpSearchParams, Class<T> entity);
}
