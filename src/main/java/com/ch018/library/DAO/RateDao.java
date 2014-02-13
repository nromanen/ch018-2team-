package com.ch018.library.DAO;


import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;

@Repository
public interface RateDao {

		
				
		void save(Rate rate);
		Rate getRate(Person person, Book book);
		
	
}
