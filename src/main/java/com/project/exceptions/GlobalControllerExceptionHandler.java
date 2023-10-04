package com.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(ProductoException.class)
	public ResponseEntity<String> handleProductoNotFound(RuntimeException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); 
	}

}
