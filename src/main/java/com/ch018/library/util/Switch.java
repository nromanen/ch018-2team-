package com.ch018.library.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class Switch {

	private Logger logger = LoggerFactory.getLogger(Switch.class);
	
	private Boolean switcher = Boolean.FALSE;
	
	private Boolean recommendationState = Boolean.TRUE;
	

	public Switch() {
		Resource resource = new ClassPathResource("settings.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			switcher = Boolean.valueOf(props.getProperty("localSearch"));
			recommendationState = Boolean.valueOf(props.getProperty("recommendations"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
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
