package com.ch018.library.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void recalculate(SearchParams searchParams) {
		
		books = bookService.getBooksComplex(searchParams);
		
	}
	
	public Page getPage(SearchParams searchParams) {
		if(books == null)
			recalculate(searchParams);
		Page page = new Page();
		int pageNum = searchParams.getPage();
		int pageSize = searchParams.getPageSize();
		List<Book> pageBooks = new ArrayList<>();
		int quantity = (int) Math.ceil((double) books.size() / pageSize);
		if(quantity == 0)
			quantity = 1;
		page.setPagesQuantity(quantity);
		if(pageNum > quantity) {
			pageNum = 1;
			searchParams.setPage(pageNum);
		}
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
