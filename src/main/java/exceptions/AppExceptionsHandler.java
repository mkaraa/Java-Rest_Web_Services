package exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import modelResponse.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		 return new ResponseEntity<>(ex,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);			 
	}
	
	@ExceptionHandler(value = {NullPointerException.class, UserSeriousException.class})
	public ResponseEntity<Object> handleSpecificException(NullPointerException ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		 return new ResponseEntity<>(ex,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);			 
	}
	
	/*
	@ExceptionHandler(value = {UserSeriousException.class})
	public ResponseEntity<Object> handleUserSeriousException(UserSeriousException ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) {
			errorMessageDescription = ex.toString();
		}
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		
		 return new ResponseEntity<>(ex,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);			 
	}*/
}
