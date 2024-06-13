package com.testeStefanini.StefaniniSpring.Service.exceptions;

import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;

public class CreateNewUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    ExceptionEnum Enum;

    public CreateNewUserException(String msg, ExceptionEnum Enum){
        super(msg);
    }

    public ExceptionEnum getEnum() {
        return Enum;
    }
}
