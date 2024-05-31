package com.testeStefanini.StefaniniSpring.Service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {//basicamente quando ocorrer esse erro, vai aparecer essa menssagem
		super("Resource not found. Id " + id);
	}
}
