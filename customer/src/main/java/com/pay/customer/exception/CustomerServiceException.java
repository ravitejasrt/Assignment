package com.pay.customer.exception;

public class CustomerServiceException extends Exception {

	private static final long serialVersionUID = 2L;

	public CustomerServiceException() {
		super();
	}

	public CustomerServiceException(final String message) {
		super(message);
	}
}