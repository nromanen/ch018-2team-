package com.ch018.library.exceptions;

public class BookUnavailableException extends Exception {

	public BookUnavailableException() {
		super("Book unavailable now");
	}
	
}