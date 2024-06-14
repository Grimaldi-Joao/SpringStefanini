package com.testeStefanini.StefaniniSpring.Resource.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.ExceptionEnum;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable { //essa classe serve para subistituir o erro 500, com ela nos criamos que um tipo de erro
    private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")	
	private Instant timestamp;
	private Integer status;
	private Integer messagEnum;
	private String message;
	private String path;

	public StandardError() {
	}

	public StandardError(Instant timestamp, Integer status, String message, ExceptionEnum messagEnum, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
		setExceptionEnum(messagEnum);
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionEnum getExceptionEnum() {
        return ExceptionEnum.valueOf(messagEnum);
    }

    public void setExceptionEnum(ExceptionEnum exceptionEnum) {
        if (exceptionEnum != null) {
            this.messagEnum = exceptionEnum.getCode();
        }
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}

