package com.ch018.library.exceptions;

public class EmailAlreadyInUseException extends Exception {

	public EmailAlreadyInUseException() {
		super("Email already in use");
	}
	
}