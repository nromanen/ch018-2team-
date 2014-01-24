package com.ch018.library.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;

@Component
public class PageContainer {
	
	@Autowired 
	BookService bookService;

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void recalculate(SimpleSearchQuery searchQuery, SearchParams searchParams) {
		
		books = bookService.getBooksComplex(searchQuery, searchParams);
		
	}
	
	public Page getPage(SimpleSearchQuery searchQuery, SearchParams searchParams) {
		
		Page page = new Page();
		int pageNum = searchParams.getPage();
		int pageSize = searchParams.getPageSize();
		List<Book> pageBooks = new ArrayList<>();
		int quantity = (int) Math.ceil((double) books.size() / pageSize);
		if(quantity == 0)
			quantity = 1;
		page.setPagesQuantity(quantity);
		page.setSearchQuery(searchQuery);
		page.setSearchParams(searchParams);
		
		int end = pageNum * pageSize;
		int start = end - pageSize;
		if(end > books.size())
			end = books.size();
		
		pageBooks = books.subList(start, end);
		page.setBooks(pageBooks);
		return page;
	}
	
}
