package com.testeStefanini.StefaniniSpring.Resource.Exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;

public class ValidationErro extends StandardError {
	private static final long serialVersionUID = 1L;
	private Map<String, String> errors = new HashMap<>();

    public ValidationErro(Instant timestamp, Integer status, ExceptionEnum messagEnum, String message, String path) {
        super(timestamp, status, message, messagEnum, path);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }
}

