package com.ch018.library.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.util.annotation.Affect;
import com.ch018.library.util.annotation.Ordinary;

@Component
public abstract class SearchParams implements Serializable{



		private final Logger logger = LoggerFactory.getLogger(SearchParams.class);


		@Autowired
		PaginationUtils paginationUtils;
		

		public static final int DEFAULT_PAGE_SIZE = 12;
	
		@Ordinary
		private Integer page;
		@Ordinary
		private Integer pageSize;
		@Ordinary
		private String orderField;
		@Ordinary
		private Boolean order;
		@Ordinary
		private Integer pagesQuantity;
	    
	    
	    public Boolean isMainFieldsEmpty() {
			return page == null || pageSize == null || order == null || orderField == null;
		}
	    
	    public void setMainFieldsDefault() {
	    	page = 1;
	    	pageSize = DEFAULT_PAGE_SIZE;
	    	order = false;
	    }
	    
	    public void update(SearchParams params) {
	    	
	    	try {
	    	Class<?> clazz = params.getClass();
	    	Class<?> superClass = clazz.getSuperclass();
	    	Field[] fields = clazz.getDeclaredFields();
	    	Field[] superFields = superClass.getDeclaredFields();
	    	fillFieds(fields, params);
	    	fillFieds(superFields, params);
	    	} catch (Exception e) {
	    		
	    	}
	  
	    }
	    
	    public void copy(SearchParams params) {
	    	
	    	Field[] fields = this.getClass().getDeclaredFields();
	    	Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
	    	List<Field> allFields = new ArrayList<Field>(Arrays.asList(fields));
	    	allFields.addAll(Arrays.asList(superFields));
	    	for(Field field : allFields) {
	    		if(field.isAnnotationPresent(Ordinary.class)) {
		    		field.setAccessible(true);
		    		try {
		    			field.set(this, params.getClass().getMethod("get" + Character.toUpperCase(field.getName().charAt(0))
		    																+ field.getName().substring(1)).invoke(params));
		    		} catch (Exception e) {
		    			logger.error("During copy " + e.getMessage());
		    		}
	    		}
	    	}
	    	
	    }
	    
	    public Boolean isPageChanged(SearchParams searchParams) {
	    	
	    	return (searchParams.getPage() != null && !this.page.equals(searchParams.getPage()));
	    }

	    
	    public Boolean isPageSizeChanged(SearchParams searchParams) {
	    	
	    	return (searchParams.getPageSize() != null && !this.pageSize.equals(searchParams.getPageSize()));
	    }
	    
	    public Boolean isSortingFieldChanged(SearchParams searchParams) {
	    	return (searchParams.getOrderField() != null && !this.orderField.equals(searchParams.getOrderField()));
	    }
	    
	    public Boolean isSortingOrderChanged(SearchParams searchParams) {
	    	return (searchParams.getOrder() != null && !this.order.equals(searchParams.getOrder()));
	    }
	   
	    public Boolean isSortingFieldsChanged(SearchParams searchParams) {
	    	return isSortingFieldChanged(searchParams) || isSortingOrderChanged(searchParams);
	    }
	    
	    public Boolean isOnlyPageChangedOrSize(SearchParams searchParams) {
	    	
	    	return (isPageChanged(searchParams) || isPageSizeChanged(searchParams))
	    				&& (!isSortingFieldChanged(searchParams) && !isSortingOrderChanged(searchParams));
	    	
	    }
	    
	    private void fillFieds(Field[] fields, SearchParams params) {
	    	try {
		    	for(Field field : fields) {
		    		if(field.isAnnotationPresent(Ordinary.class)) {
		    			
			    		field.setAccessible(true);
			    		Object o = field.get(params);
			    		if(o != null) {
			    			field.set(this, o);
			    		}
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
					+ ", pagesQuantity=" + pagesQuantity + "]";
		}

		
		

		
	    
		
}
