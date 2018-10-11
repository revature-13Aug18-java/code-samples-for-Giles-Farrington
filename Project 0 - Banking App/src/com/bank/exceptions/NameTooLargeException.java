package com.bank.exceptions;

@SuppressWarnings("serial")
public class NameTooLargeException extends Exception {

	public NameTooLargeException(String message) {
		super(message);
	}
}
