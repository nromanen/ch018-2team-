package com.ch018.library.helper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.service.BookService;

@Component
public class SearchParams implements Serializable{


		private static final long serialVersionUID = 1L;
		
		private final Logger logger = LoggerFactory.getLogger(SearchParams.class);

		@Autowired
		private BookService bookService;
		
		private static final int DEFAULT_PAGE_SIZE = 12;
	
		private int page;
		private Integer pageSize;
		private String orderField;
		private Boolean order;
		
	    private Integer yearStart;
	    private Integer yearEnd;
	    private Integer choosenYearStart;
	    private Integer choosenYearEnd;
	    
	    private Integer bookPageStart;
	    private Integer bookPageEnd;
	    private Integer choosenPageStart;
	    private Integer choosenPageEnd;
	    
	    private Boolean generalQuery = Boolean.FALSE;
	    private String query;
	    private String title;
	    private String authors;
	    private String publisher;
	    private Integer genreId;
	    
	    private Integer pagesQuantity;
	    

	    public void init() {
	    	
	    	yearStart = bookService.getMinIntegerField("year");
		    yearEnd = bookService.getMaxIntegerField("year");
		    bookPageStart = bookService.getMinIntegerField("pages");
		    bookPageEnd = bookService.getMaxIntegerField("pages");
		    genreId = 0;
	    }
	    
	    public void setDefaults() {
	    	page = 1;
	    	pageSize = DEFAULT_PAGE_SIZE;
	    	
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
		    
		    query = null;
		    title = null;
		    authors = null;
		    publisher = null;
		    genreId = 0;
	    }
	    
	    
	    public void update(SearchParams params) {
	    	if(params.getGeneralQuery()) {
	    		this.setDefaults();
	    		query = params.getQuery();
	    		return;
	    	}
	    	try {
	    	Class<?> clazz = SearchParams.class;
	    	Field[] fields = clazz.getDeclaredFields();
	    	for(Field field : fields) {
	    		if(Modifier.isFinal(field.getModifiers()) 
	    					|| field.getDeclaredAnnotations().length > 0)
	    			continue;
	    		field.setAccessible(true);
	    		Object o = field.get(params);
	    		if(o != null) {
	    			field.set(this, o);
	    		}
	    	}
	    	} catch (Exception e) {
	    		logger.error("during update {}" + e.getMessage());
	    	}

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

		public Integer getPageSize() {
			return pageSize;
		}

		public void setPageSize(Integer pageSize) {
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

		public Boolean getGeneralQuery() {
			return generalQuery;
		}

		public void setGeneralQuery(Boolean generalQuery) {
			this.generalQuery = generalQuery;
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

		public Integer getPagesQuantity() {
			return pagesQuantity;
		}

		public void setPagesQuantity(Integer pagesQuantity) {
			this.pagesQuantity = pagesQuantity;
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
					+ ", choosenPageEnd=" + choosenPageEnd + ", generalQuery="
					+ generalQuery + ", query=" + query + ", title=" + title
					+ ", authors=" + authors + ", publisher=" + publisher
					+ ", genreId=" + genreId + ", pagesQuantity="
					+ pagesQuantity + "]";
		}

		
	    
		
}
