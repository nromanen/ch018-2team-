package com.ch018.library.exceptions;

public class BookAlreadyRatedException extends Exception {

	public BookAlreadyRatedException() {
		super("You already rate this book");
	}
	
}