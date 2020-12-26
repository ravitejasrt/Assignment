package com.pay.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

//import javax.persistence.EntityNotFoundException;

import com.pay.customer.exception.*;
import com.pay.customer.error.ApiError;
import com.pay.customer.error.ErrorModel;

@RestControllerAdvice
public class RestExceptionHandler1 extends ResponseEntityExceptionHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	
    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex){
        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    
	/*
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }*/
   
    /*@ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorModel> invalidRequest(Exception ex){
        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Invalid Request", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }*/
    
    
    @ExceptionHandler(value 
    	      = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    	 String bodyOfResponse = "This should be application specific";
    	 return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
   
    /*
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorModel requestHandlingNoHandlerFound() {
        return new ErrorModel(HttpStatus.NOT_FOUND, "message for 404 error code","Url not exists");
    } */
}