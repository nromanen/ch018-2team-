package com.ch018.library.util;

import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.Ordinary;

public class SearchParamsRate extends SearchParams {
	
	
	@Ordinary(onlyCopy = true)
    @ComplexType(entityField = "bId")
    private Integer book;

	
	public void setMainFieldsDefault() {
    	
		super.setMainFieldsDefault();
		
    	setOrderField("rateDate");
    	setPageSize(3);
    	
    }


	public Integer getBook() {
		return book;
	}


	public void setBook(Integer book) {
		this.book = book;
	}


	


	
	
	
	
}
