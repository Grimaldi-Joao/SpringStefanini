package com.testeStefanini.StefaniniSpring.Resource.Exception;

import java.time.Instant;

import com.testeStefanini.StefaniniSpring.Resource.Exception.Enum.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.testeStefanini.StefaniniSpring.Service.exceptions.BaseExeptionInvalid;
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
	public ResponseEntity<StandardError> database(BaseExeptionInvalid e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		System.out.println(e.getMessage());
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(), e.getEnum(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler({ValidationException.class, CreateNewUserException.class, PasswordException.class})
	public ResponseEntity<StandardError> Invalid(BaseExeptionInvalid e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		StandardError err = new StandardError(Instant.now(), status.value(), e.getMessage(),e.getEnum() , request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErro> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErro err = new ValidationErro(Instant.now(), status.value(),ExceptionEnum.Validation_error , "Erro de validação" , request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        
    }
	return ResponseEntity.status(status).body(err);
}
}