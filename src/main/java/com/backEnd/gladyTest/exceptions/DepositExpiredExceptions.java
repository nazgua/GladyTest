package com.backEnd.gladyTest.exceptions;

public class DepositExpiredExceptions extends Exception {

	private static final long serialVersionUID = 1L;

	public DepositExpiredExceptions(String message) {
		super(message);
	}
}
