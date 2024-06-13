package com.testeStefanini.StefaniniSpring.Service.exceptions;

import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;

public class PasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    ExceptionEnum Enum;

    public PasswordException(String msg, ExceptionEnum Enum){
        super(msg);
        this.Enum = Enum;
    }

    	
	public ExceptionEnum getEnum() {
        return Enum;
    }
}
