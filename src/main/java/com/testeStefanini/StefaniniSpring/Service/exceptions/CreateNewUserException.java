package com.testeStefanini.StefaniniSpring.Service.exceptions;

public class CreateNewUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public CreateNewUserException(String msg){
        super(msg);
    }
}
