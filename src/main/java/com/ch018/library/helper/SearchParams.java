package com.ch018.library.helper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SearchParams {

		private int page = 1;
		private int pageSize = 1;
		private String orderField = "title";
		private Boolean order = Boolean.FALSE;
		
	    private Integer yearStart;
	    private Integer yearEnd;
	    private Integer bookPageStart;
	    private Integer bookPageEnd;
	    
	    public void setDefaults() {
	    	page = 1;
	    	pageSize = 1;
	    	orderField = "title";
	    	order = Boolean.FALSE;
	    	yearStart = null;
	    	yearEnd = null;
	    	bookPageStart = null;
	    	bookPageEnd = null;
	    }
	    
	    
	    
	    public void set(SearchParams params) {
	    	page = params.page;
	    	pageSize = params.pageSize;
	    	orderField = params.orderField;
	    	order = params.order;
	    	yearStart = params.yearStart;
	    	yearEnd = params.yearEnd;
	    	bookPageStart = params.bookPageStart;
	    	bookPageEnd = params.bookPageEnd;
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
		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((bookPageEnd == null) ? 0 : bookPageEnd.hashCode());
			result = prime * result
					+ ((bookPageStart == null) ? 0 : bookPageStart.hashCode());
			result = prime * result + ((order == null) ? 0 : order.hashCode());
			result = prime * result
					+ ((orderField == null) ? 0 : orderField.hashCode());
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
			if (!(obj instanceof SearchParams))
				return false;
			SearchParams other = (SearchParams) obj;
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
			if (order == null) {
				if (other.order != null)
					return false;
			} else if (!order.equals(other.order))
				return false;
			if (orderField == null) {
				if (other.orderField != null)
					return false;
			} else if (!orderField.equals(other.orderField))
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
			return "SearchParams [page=" + page + ", pageSize=" + pageSize
					+ ", orderField=" + orderField + ", order=" + order
					+ ", yearStart=" + yearStart + ", yearEnd=" + yearEnd
					+ ", bookPageStart=" + bookPageStart + ", bookPageEnd="
					+ bookPageEnd + "]";
		}
	    
		
}
