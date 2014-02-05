package com.ch018.library.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ch018.library.util.annotation.ComplexType;

@Component
public abstract class SearchParams implements Serializable{


		
		private final Logger logger = LoggerFactory.getLogger(SearchParams.class);

		
		public static final int DEFAULT_PAGE_SIZE = 12;
	
		private Integer page;
		private Integer pageSize;
		private String orderField;
		private Boolean order;
		private Integer pagesQuantity;
	    
	    
	    public Boolean isInit() {
			return page != null && pageSize != null && order != null && orderField != null;
		}
	    
	    public void setDefaults() {
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
	    

	    
	    private void fillFieds(Field[] fields, SearchParams params) {
	    	try {
		    	for(Field field : fields) {
		    		if(!Modifier.isFinal(field.getModifiers()) || !Modifier.isStatic(field.getModifiers())
		    					|| field.isAnnotationPresent(ComplexType.class)) {
		    			
			    		field.setAccessible(true);
			    		Object o = field.get(params);
			    		System.out.println("FIELD : " + field.getName() + ", val = " + field.get(params));
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
