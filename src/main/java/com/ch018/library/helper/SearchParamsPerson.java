package com.ch018.library.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchParamsPerson {

		private final Logger logger = LoggerFactory.getLogger(SearchParamsPerson.class);
	
		private static final int DEFAULT_PAGE_SIZE = 10;
		
		private Integer page;
		private Integer pageSize;
		private String orderField;
		private Boolean order;
		
		private String name;
		private String surname;
		private String email;
		
		
		private Integer pagesQuantity;
	
		public void setDefaults() {
	    	page = 1;
	    	pageSize = DEFAULT_PAGE_SIZE;
	    	
	    	orderField = "email";
	    	order = Boolean.FALSE;
	    	
	    	name = null;
	    	surname = null;
	    	email = null;
	    	
	    }
		
		public void update(SearchParamsPerson params) {
	    	try {
	    	Class<?> clazz = SearchParamsPerson.class;
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
	
		public Integer getPage() {
			return page;
		}
	
		public void setPage(Integer page) {
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
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getSurname() {
			return surname;
		}
	
		public void setSurname(String surname) {
			this.surname = surname;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public Integer getPagesQuantity() {
			return pagesQuantity;
		}
	
		public void setPagesQuantity(Integer pagesQuantity) {
			this.pagesQuantity = pagesQuantity;
		}

		@Override
		public String toString() {
			return "SearchParamsPersons [page=" + page + ", pageSize="
					+ pageSize + ", orderField=" + orderField + ", order="
					+ order + ", name=" + name + ", surname=" + surname
					+ ", email=" + email + ", pagesQuantity=" + pagesQuantity
					+ "]";
		}
	
		
		
	
    
	
}
