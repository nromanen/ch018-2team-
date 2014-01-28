package com.ch018.library.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;

@Component
public class PageContainer {
	
	@Autowired 
	BookService bookService;
	
	private final Logger logger = LoggerFactory.getLogger(PageContainer.class);

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
	
	public void recalculateLocal(SearchParams searchParams) {
		orderLocale(searchParams);
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
	
	private void orderLocale(SearchParams searchParams) {
		final String field = searchParams.getOrderField();
		final Boolean order = searchParams.getOrder();
		
		Comparator<Book> comparator = new Comparator<Book>() {
			
			@Override
			public int compare(Book book1, Book book2) {
				Field classField = null;
				Object o1 = null;
				Object o2 = null;
				try {
					classField = Book.class.getDeclaredField(field);
					classField.setAccessible(true);
					o1 = classField.get(book1);
					o2 = classField.get(book2);
				} catch (Exception e) {
					logger.error("during locale sort {}", e.getMessage());
				}
				if(o1 instanceof Number && o2 instanceof Number) {
					Integer n1 = (Integer) o1;
					Integer n2 = (Integer) o2;
					if(!order) {
						return n1.compareTo(n2);
					} else {
						return n2.compareTo(n1);
					}
				} else if (o1 instanceof String && o2 instanceof String) {
					String n1 = (String) o1;
					String n2 = (String) o2;
					if(!order) {
						return n1.compareTo(n2);
					} else {
						return n2.compareTo(n1);
					}
				}
				return 0;
			}
		};
		
		Collections.sort(books, comparator);
		
	}
}
