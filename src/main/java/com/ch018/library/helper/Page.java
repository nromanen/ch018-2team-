package com.ch018.library.helper;

import java.util.List;

import com.ch018.library.entity.Book;

public class Page {

		private SearchParams searchParams;
		private int pagesQuantity;
		private List<Book> books;
		
		
		public SearchParams getSearchParams() {
			return searchParams;
		}
		public void setSearchParams(SearchParams searchParams) {
			this.searchParams = searchParams;
		}
		public int getPagesQuantity() {
			return pagesQuantity;
		}
		public void setPagesQuantity(int pagesQuantity) {
			this.pagesQuantity = pagesQuantity;
		}
		public List<Book> getBooks() {
			return books;
		}
		public void setBooks(List<Book> books) {
			this.books = books;
		}
		@Override
		public String toString() {
			return "Page [searchParams=" + searchParams + ", searchQuery="
					+ ", pagesQuantity=" + pagesQuantity
					+ ", books=" + books + "]";
		}
		
		
		
		
	
}
