package com.backEnd.gladyTest.exceptions;

public class CompanyNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String message) {
		super(message);
	}
}
