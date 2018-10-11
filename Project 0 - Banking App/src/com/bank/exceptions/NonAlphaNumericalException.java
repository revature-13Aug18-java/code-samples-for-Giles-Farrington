package com.bank.exceptions;

@SuppressWarnings("serial")
public class NonAlphaNumericalException extends Exception {

	public NonAlphaNumericalException(String message) {
		super(message);
	}
}
