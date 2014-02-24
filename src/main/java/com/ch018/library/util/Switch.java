package com.ch018.library.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Switch {

	private Logger logger = LoggerFactory.getLogger(Switch.class);
	
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
