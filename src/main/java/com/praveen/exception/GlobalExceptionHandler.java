package com.praveen.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.praveen.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e) {

		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message("You doing operation with Null value").build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> handleMissingParameterException(Exception e) {

		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.message(e.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
          HashMap<String, String> hashMap = new HashMap<>();
	     List<ObjectError> allErrors = e.getBindingResult().getAllErrors();//ctr+2
	     allErrors.stream().forEach(ex->{
	    	 String message = ex.getDefaultMessage();
	    	 String field = ((FieldError)ex).getField();
	    	 hashMap.put(field, message);
	     });
		
//		ErrorResponse error = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
//				.message(e.getMessage()).build();
		
		return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}