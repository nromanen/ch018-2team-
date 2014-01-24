package com.ch018.library.helper;

import com.ch018.library.entity.Book;

/**
 * Contains user request search params. 
 * Use equals to verify changes in params which can cause result change. 
 * @author Edd Arazian
 */
public class BookSearch extends Book {

		private String query = "";
        private Integer yearStart;
        private Integer yearEnd;
        private Integer bookPageStart;
        private Integer bookPageEnd;
        private Integer genreId;
        private Integer viewPageNum = 1;
        private Integer lowBorder;
        private Integer highBorder;
        private Integer booksOnPage = 3;
        
        private String sort = "title";
        private Boolean order = false;
        
        public void setBorders() {
            highBorder = viewPageNum * booksOnPage;
            lowBorder = highBorder - booksOnPage;
            if(highBorder - lowBorder == 1 && lowBorder > 0) {
            	highBorder = lowBorder;
            }
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

        public Integer getGenreId() {
            return genreId;
        }

        public void setGenreId(Integer genreId) {
            this.genreId = genreId;
        }

        public Integer getViewPageNum() {
            return viewPageNum;
        }

        public void setViewPageNum(Integer viewPageNum) {
            this.viewPageNum = viewPageNum;
        }

        public Integer getLowBorder() {
            return lowBorder;
        }

        public void setLowBorder(Integer lowBorder) {
            this.lowBorder = lowBorder;
        }

        public Integer getHighBorder() {
            return highBorder;
        }

        public void setHighBorder(Integer highBorder) {
            this.highBorder = highBorder;
        }

        public Integer getBooksOnPage() {
            return booksOnPage;
        }

        public void setBooksOnPage(Integer booksOnPage) {
            this.booksOnPage = booksOnPage;
        }

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public Boolean getOrder() {
            return order;
        }

        public void setOrder(Boolean order) {
            this.order = order;
        }

        

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bookPageEnd == null) ? 0 : bookPageEnd.hashCode());
			result = prime * result
					+ ((bookPageStart == null) ? 0 : bookPageStart.hashCode());
			result = prime * result
					+ ((genreId == null) ? 0 : genreId.hashCode());
			result = prime * result + ((query == null) ? 0 : query.hashCode());
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
			if (!(obj instanceof BookSearch))
				return false;
			BookSearch other = (BookSearch) obj;
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
			if (genreId == null) {
				if (other.genreId != null)
					return false;
			} else if (!genreId.equals(other.genreId))
				return false;
			if (query == null) {
				if (other.query != null)
					return false;
			} else if (!query.equals(other.query))
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
            return "BookSearch{"  + "yearStart=" + yearStart + ", yearEnd=" + yearEnd + ", bookPageStart=" + bookPageStart + ", bookPageEnd=" + bookPageEnd + ", genreId=" + genreId + ", viewPageNum=" + viewPageNum + ", lowBorder=" + lowBorder + ", highBorder=" + highBorder + ", booksOnPage=" + booksOnPage + ", query=" + query + ", sort=" + sort + ", order=" + order + '}';
        }

        
}
