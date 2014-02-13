package com.ch018.library.service;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;
import com.ch018.library.exceptions.BookAlreadyRatedException;

@Service
public interface RateService {

	void save(Rate rate);
	void addRate(Rate rate, Integer bookId) throws BookAlreadyRatedException;
	Rate getRate(Person person, Book book);
	
}
