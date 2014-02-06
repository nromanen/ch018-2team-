package com.ch018.library.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.service.BookService;
import com.ch018.library.util.annotation.Affect;
import com.ch018.library.util.annotation.Between;
import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.Ordinary;
import com.ch018.library.util.annotation.SimpleSearch;

public class SearchParamsBook extends SearchParams implements Serializable {

	
		

		private final Logger logger = LoggerFactory.getLogger(SearchParams.class);
	

		@Autowired
		private BookService bookService;
		

		private Integer yearStart;

	    private Integer yearEnd;
	    @Ordinary(onlyCopy = true)
	    @Between(name = "year", value = "start")
	    private Integer choosenYearStart;
	    @Ordinary(onlyCopy = true)
	    @Between(name = "year", value = "end")
	    private Integer choosenYearEnd;
	    

	    private Integer bookPageStart;

	    private Integer bookPageEnd;
	    @Ordinary(onlyCopy = true)
	    @Between(name = "pages", value = "start")
	    private Integer choosenPageStart;
	    @Ordinary(onlyCopy = true)
	    @Between(name = "pages", value = "end")
	    private Integer choosenPageEnd;
	    

	    private Boolean generalQuery = Boolean.FALSE;
	    
	    @Ordinary(onlyCopy = true)
	    @SimpleSearch(entityFields = {"title", "authors", "publisher"})
	    private String query;
	    @Ordinary
	    private String title;
	    @Ordinary
	    private String authors;
	    @Ordinary
	    private String publisher;
	    @Ordinary(onlyCopy = true)
	    @ComplexType(entityField = "id")
	    private Integer genre;
	    
	    
	    
	    
	    public void init() {
	    	
	    	yearStart = bookService.getMinIntegerField("year");
		    yearEnd = bookService.getMaxIntegerField("year");
		    bookPageStart = bookService.getMinIntegerField("pages");
		    bookPageEnd = bookService.getMaxIntegerField("pages");
		    genre = 0;
	    }
	    
	    public void setDefaults() {

	    	super.setDefaults();
	    	
	    	setOrderField("title");
	    	
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
		    genre = 0;
	    }
	
	
	    public void update(SearchParams params) {
	    	SearchParamsBook tmpParams = (SearchParamsBook) params;
	    	if(tmpParams.getGeneralQuery()) {
	    		this.setDefaults();
	    		query = tmpParams.getQuery();
	    		return;
	    	}
	    	
	    	super.update(params);
	    }
	    
	    
	    
	    public boolean isSlidersNull() {
	    	return bookPageStart == null || bookPageStart == null 
	    			|| yearStart == null || yearEnd == null || genre == null;
	    }

		public BookService getBookService() {
			return bookService;
		}

		public void setBookService(BookService bookService) {
			this.bookService = bookService;
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

		public Integer getGenre() {
			return genre;
		}

		public void setGenre(Integer genre) {
			this.genre = genre;
		}


		public Logger getLogger() {
			return logger;
		}

		@Override
		public String toString() {
			return super.toString() + "SearchParamsBook [yearStart=" + yearStart + ", yearEnd="
					+ yearEnd + ", choosenYearStart=" + choosenYearStart
					+ ", choosenYearEnd=" + choosenYearEnd + ", bookPageStart="
					+ bookPageStart + ", bookPageEnd=" + bookPageEnd
					+ ", choosenPageStart=" + choosenPageStart
					+ ", choosenPageEnd=" + choosenPageEnd + ", generalQuery="
					+ generalQuery + ", query=" + query + ", title=" + title
					+ ", authors=" + authors + ", publisher=" + publisher
					+ ", genre=" + genre + ", pagesQuantity="
					+ "]";
		}
	    
		
	    
}
