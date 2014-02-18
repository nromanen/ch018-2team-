package com.ch018.library.exceptions;

public class TooManyOrdersException extends Exception {

	public TooManyOrdersException() {
		super("Too many orders for this book, try later");
	}
	
}
