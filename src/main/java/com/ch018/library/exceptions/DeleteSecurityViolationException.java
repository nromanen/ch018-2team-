package com.ch018.library.exceptions;

public class DeleteSecurityViolationException extends Exception {

	public DeleteSecurityViolationException() {
		super("Security Violation. Dear User use site properly.");
	}
	
}
