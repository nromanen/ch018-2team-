package com.ch018.library.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ch018.library.util.SearchParams;

@Repository
public interface UniversalDao <T> {

	List<T> getPaginatedResult(SearchParams searchParams, Class<T> entity);
	
	
}
