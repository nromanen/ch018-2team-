package com.ch018.library.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.service.BookService;

@Component
public class SearchParams {

		@Autowired
		BookService bookService;
	
		private int page = 1;
		private int pageSize = 1;
		private String orderField = "title";
		private Boolean order = Boolean.FALSE;
		
	    private Integer yearStart;
	    private Integer yearEnd;
	    private Integer choosenYearStart;
	    private Integer choosenYearEnd;
	    
	    private Integer bookPageStart;
	    private Integer bookPageEnd;
	    private Integer choosenPageStart;
	    private Integer choosenPageEnd;
	    
	    private String query;
	    private String title;
	    private String authors;
	    private String publisher;
	    private Integer genreId;
	    

	    public void init() {
	    	yearStart = bookService.getMinIntegerField("year");
		    yearEnd = bookService.getMaxIntegerField("year");
		    bookPageStart = bookService.getMinIntegerField("pages");
		    bookPageEnd = bookService.getMaxIntegerField("pages");
		    genreId = 0;
	    }
	    
	    public void setDefaults() {
	    	page = 1;
	    	pageSize = 1;
	    	
	    	orderField = "title";
	    	order = Boolean.FALSE;
	    	
	    	yearStart = bookService.getMinIntegerField("year");
		    yearEnd = bookService.getMaxIntegerField("year");
		    choosenYearStart = null;
		    choosenYearEnd = null;
		    
		    bookPageStart = bookService.getMinIntegerField("pages");
		    bookPageEnd = bookService.getMaxIntegerField("pages");
		    choosenPageStart = null;
		    choosenPageEnd = null;
		    
		    title = null;
		    authors = null;
		    publisher = null;
		    genreId = 0;
	    }
	    
	    
	    public void update(SearchParams params) {
	    	page = params.getPage();
	    	if(params.getPageSize() != 1)
	    		pageSize = params.getPageSize();
	    	//if(!params.getOrderField().equals("title"))
	    		orderField = params.getOrderField();
	    	
	    		order = params.getOrder();
	    	if(params.getChoosenYearStart() != null)
	    		choosenYearStart = params.getChoosenYearStart();
	    	if(params.getChoosenYearEnd() != null)
	    		choosenYearEnd = params.getChoosenYearEnd();
	    	if(params.getChoosenPageStart() != null)
	    		choosenPageStart = params.getChoosenPageStart();
	    	if(params.getChoosenPageEnd() != null)
	    		choosenPageEnd = params.getChoosenPageEnd();
	    	if(params.getQuery() != null)
	    		query = params.getQuery();
	    	if(params.getTitle() != null)
	    		title = params.getTitle();
	    	if(params.getAuthors() != null)
	    		authors = params.getAuthors();
	    	if(params.getPublisher() != null)
	    		publisher = params.getPublisher();
	    	if(params.getGenreId() != null)
	    		genreId = params.getGenreId();
	    	
	    }
	    
	    
	    public boolean isSlidersNull() {
	    	return bookPageStart == null || bookPageStart == null 
	    			|| yearStart == null || yearEnd == null || genreId == null;
	    }


		public int getPage() {
			return page;
		}



		public void setPage(int page) {
			this.page = page;
		}



		public int getPageSize() {
			return pageSize;
		}



		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}



		public String getOrderField() {
			return orderField;
		}



		public void setOrderField(String orderField) {
			this.orderField = orderField;
		}



		public Boolean getOrder() {
			return order;
		}



		public void setOrder(Boolean order) {
			this.order = order;
		}



		public Integer getYearStart() {
			return yearStart;
		}



		public void setYearStart(Integer yearStart) {
			this.yearStart = yearStart;
		}



		public Integer getYearEnd() {
			return yearEnd;
		}



		public void setYearEnd(Integer yearEnd) {
			this.yearEnd = yearEnd;
		}



		public Integer getChoosenYearStart() {
			return choosenYearStart;
		}



		public void setChoosenYearStart(Integer choosenYearStart) {
			this.choosenYearStart = choosenYearStart;
		}



		public Integer getChoosenYearEnd() {
			return choosenYearEnd;
		}



		public void setChoosenYearEnd(Integer choosenYearEnd) {
			this.choosenYearEnd = choosenYearEnd;
		}



		public Integer getBookPageStart() {
			return bookPageStart;
		}



		public void setBookPageStart(Integer bookPageStart) {
			this.bookPageStart = bookPageStart;
		}



		public Integer getBookPageEnd() {
			return bookPageEnd;
		}



		public void setBookPageEnd(Integer bookPageEnd) {
			this.bookPageEnd = bookPageEnd;
		}



		public Integer getChoosenPageStart() {
			return choosenPageStart;
		}



		public void setChoosenPageStart(Integer choosenPageStart) {
			this.choosenPageStart = choosenPageStart;
		}



		public Integer getChoosenPageEnd() {
			return choosenPageEnd;
		}



		public void setChoosenPageEnd(Integer choosenPageEnd) {
			this.choosenPageEnd = choosenPageEnd;
		}



		public String getQuery() {
			return query;
		}



		public void setQuery(String query) {
			this.query = query;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		public String getAuthors() {
			return authors;
		}



		public void setAuthors(String authors) {
			this.authors = authors;
		}



		public String getPublisher() {
			return publisher;
		}



		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}



		public Integer getGenreId() {
			return genreId;
		}



		public void setGenreId(Integer genreId) {
			this.genreId = genreId;
		}


		@Override
		public String toString() {
			return "SearchParams [page=" + page + ", pageSize=" + pageSize
					+ ", orderField=" + orderField + ", order=" + order
					+ ", yearStart=" + yearStart + ", yearEnd=" + yearEnd
					+ ", choosenYearStart=" + choosenYearStart
					+ ", choosenYearEnd=" + choosenYearEnd + ", bookPageStart="
					+ bookPageStart + ", bookPageEnd=" + bookPageEnd
					+ ", choosenPageStart=" + choosenPageStart
					+ ", choosenPageEnd=" + choosenPageEnd + ", query=" + query
					+ ", title=" + title + ", authors=" + authors
					+ ", publisher=" + publisher + ", genreId=" + genreId + "]";
		}
	    
		
}
