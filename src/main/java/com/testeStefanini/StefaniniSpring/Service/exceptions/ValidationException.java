package com.testeStefanini.StefaniniSpring.Service.exceptions;

import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;

public class ValidationException extends BaseExeptionInvalid {
    
    private static final long serialVersionUID = 1L;
    ExceptionEnum Enum;

    public ValidationException(String msg, ExceptionEnum Enum) {
        super(msg, Enum);
    }
}
