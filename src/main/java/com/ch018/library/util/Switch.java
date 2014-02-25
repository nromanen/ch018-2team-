package com.ch018.library.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

public class Switch {

	
	private Boolean switcher = Boolean.FALSE;
	
	private Boolean recommendationState = Boolean.TRUE;
	
	@Autowired
	ServletContext servletContext;
	

	
	
	public Boolean getSwitcher() {
		return switcher;
	}

	public void setSwitcher(Boolean switcher) {
		this.switcher = switcher;
	}

	public Boolean getRecommendationState() {
		return recommendationState;
	}

	public void setRecommendationState(Boolean recommendationState) {
		this.recommendationState = recommendationState;
	}
	
	
	
}
