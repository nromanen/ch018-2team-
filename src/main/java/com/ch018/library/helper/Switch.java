package com.ch018.library.helper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Switch {

	private Boolean value = Boolean.FALSE;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
	
	
}
