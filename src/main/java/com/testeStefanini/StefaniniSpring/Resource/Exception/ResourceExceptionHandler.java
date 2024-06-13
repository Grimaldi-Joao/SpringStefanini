package com.testeStefanini.StefaniniSpring.Resource.Exception;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.testeStefanini.StefaniniSpring.Service.exceptions.CreateNewUserException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.DatabaseException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.PasswordException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ResourceNotFoundException;
import com.testeStefanini.StefaniniSpring.Service.exceptions.ValidationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)//essa anotatio dará para a excessão que estamos interceptando(a qual está dentro dos parenteses), o tratamento que estamos implementando a baixo 
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;//como esse erro é de não encontrar o ID aqui estamos usando o 404 not faund
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(),e.getEnum(), request.getRequestURI());// criamos um standart erro para altera-lo com nossas novas informações
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), e.getEnum(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler({ValidationException.class, CreateNewUserException.class, PasswordException.class})
	public ResponseEntity<StandardError> Invalid(ValidationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), e.getEnum(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
