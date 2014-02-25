package com.ch018.library.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.service.BookService;
import com.ch018.library.util.annotation.Between;
import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.Ordinary;
import com.ch018.library.util.annotation.SimpleSearch;

public class SearchParamsBook extends SearchParams implements Serializable {

	
		


		private static final long serialVersionUID = -2525475415563280213L;


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
	    
	    public void setMainFieldsDefault() {

	    	super.setMainFieldsDefault();
	    	
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
	    		this.setMainFieldsDefault();
	    		query = tmpParams.getQuery();
	    		return;
	    	}
	    	
	    	super.update(params);
	    }
	    
	    
	    
	    public boolean isSlidersNull() {
	    	return bookPageStart == null || bookPageEnd == null 
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
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((authors == null) ? 0 : authors.hashCode());
			result = prime * result
					+ ((bookPageEnd == null) ? 0 : bookPageEnd.hashCode());
			result = prime * result
					+ ((bookPageStart == null) ? 0 : bookPageStart.hashCode());
			result = prime
					* result
					+ ((choosenPageEnd == null) ? 0 : choosenPageEnd.hashCode());
			result = prime
					* result
					+ ((choosenPageStart == null) ? 0 : choosenPageStart
							.hashCode());
			result = prime
					* result
					+ ((choosenYearEnd == null) ? 0 : choosenYearEnd.hashCode());
			result = prime
					* result
					+ ((choosenYearStart == null) ? 0 : choosenYearStart
							.hashCode());
			result = prime * result
					+ ((generalQuery == null) ? 0 : generalQuery.hashCode());
			result = prime * result + ((genre == null) ? 0 : genre.hashCode());
			result = prime * result
					+ ((publisher == null) ? 0 : publisher.hashCode());
			result = prime * result + ((query == null) ? 0 : query.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result
					+ ((yearEnd == null) ? 0 : yearEnd.hashCode());
			result = prime * result
					+ ((yearStart == null) ? 0 : yearStart.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SearchParamsBook other = (SearchParamsBook) obj;
			if (authors == null) {
				if (other.authors != null)
					return false;
			} else if (!authors.equals(other.authors))
				return false;
			if (bookPageEnd == null) {
				if (other.bookPageEnd != null)
					return false;
			} else if (!bookPageEnd.equals(other.bookPageEnd))
				return false;
			if (bookPageStart == null) {
				if (other.bookPageStart != null)
					return false;
			} else if (!bookPageStart.equals(other.bookPageStart))
				return false;
			if (choosenPageEnd == null) {
				if (other.choosenPageEnd != null)
					return false;
			} else if (!choosenPageEnd.equals(other.choosenPageEnd))
				return false;
			if (choosenPageStart == null) {
				if (other.choosenPageStart != null)
					return false;
			} else if (!choosenPageStart.equals(other.choosenPageStart))
				return false;
			if (choosenYearEnd == null) {
				if (other.choosenYearEnd != null)
					return false;
			} else if (!choosenYearEnd.equals(other.choosenYearEnd))
				return false;
			if (choosenYearStart == null) {
				if (other.choosenYearStart != null)
					return false;
			} else if (!choosenYearStart.equals(other.choosenYearStart))
				return false;
			if (generalQuery == null) {
				if (other.generalQuery != null)
					return false;
			} else if (!generalQuery.equals(other.generalQuery))
				return false;
			if (genre == null) {
				if (other.genre != null)
					return false;
			} else if (!genre.equals(other.genre))
				return false;
			if (publisher == null) {
				if (other.publisher != null)
					return false;
			} else if (!publisher.equals(other.publisher))
				return false;
			if (query == null) {
				if (other.query != null)
					return false;
			} else if (!query.equals(other.query))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (yearEnd == null) {
				if (other.yearEnd != null)
					return false;
			} else if (!yearEnd.equals(other.yearEnd))
				return false;
			if (yearStart == null) {
				if (other.yearStart != null)
					return false;
			} else if (!yearStart.equals(other.yearStart))
				return false;
			return true;
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
