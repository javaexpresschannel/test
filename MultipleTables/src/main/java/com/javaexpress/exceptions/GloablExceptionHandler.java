package com.javaexpress.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GloablExceptionHandler {

	// 400
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorAPI handleException(ResourceNotFoundException ex) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setDetails(ex.getMessage());
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorApi.setTitle("Client Error");
		// return new ResponseEntity<>(errorApi,HttpStatus.BAD_REQUEST);
		return errorApi;
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorAPI handleException(ValidationException ex) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setDetails(ex.getMessage());
		errorApi.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorApi.setTitle("Validation Failed");
		// return new ResponseEntity<>(errorApi,HttpStatus.BAD_REQUEST);
		return errorApi;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex) {
		var errorApi = new ErrorAPI();
		errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorApi.setLocalDateTime(LocalDateTime.now());
		errorApi.setDetails(ex.getMessage());
		errorApi.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorApi.setTitle("Something Went Wrong");
		return new ResponseEntity<>(errorApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
