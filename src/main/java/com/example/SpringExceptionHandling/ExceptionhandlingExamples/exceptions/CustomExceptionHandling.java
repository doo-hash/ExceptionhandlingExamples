package com.example.SpringExceptionHandling.ExceptionhandlingExamples.exceptions;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.SpringExceptionHandling.ExceptionhandlingExamples.model.ExceptionJsonInfo;

@ControllerAdvice
public class CustomExceptionHandling extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<ExceptionJsonInfo> handleClientNotFoundException(ClientNotFoundException ex,
			HttpServletRequest req){
		ExceptionJsonInfo response = new ExceptionJsonInfo();
		response.setDate(LocalDateTime.now());
		response.setMessage("Not Found");
		response.setUrl(req.getRequestURL().toString());
		ResponseEntity<ExceptionJsonInfo> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> handleSQLExceptions(SQLException ex,
			HttpServletRequest req){
		ExceptionJsonInfo response = new ExceptionJsonInfo();
		response.setDate(LocalDateTime.now());
		response.setMessage("server problem");
		response.setUrl(req.getRequestURL().toString());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		return entity;
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullException(IOException ex,
			HttpServletRequest req){
		ExceptionJsonInfo response = new ExceptionJsonInfo();
		response.setDate(LocalDateTime.now());
		response.setMessage("No value found");
		response.setUrl(req.getRequestURL().toString());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.GONE);
		return entity;
	}
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> handleIOException(IOException ex,
			HttpServletRequest req){
		ExceptionJsonInfo response = new ExceptionJsonInfo();
		response.setDate(LocalDateTime.now());
		response.setMessage("Server not found");
		response.setUrl(req.getRequestURL().toString());
		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
		return entity;
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionJsonInfo> handleException(Exception ex,
			HttpServletRequest req,
			WebRequest webrequest){
		ExceptionJsonInfo response = new ExceptionJsonInfo();
		response.setDate(LocalDateTime.now());
		response.setMessage(ex.getLocalizedMessage());
//		do not always use getCause(), its value will be null for some exceptions further resulting in null exceptions
		response.setUrl(req.getRequestURL().toString());
		ResponseEntity<ExceptionJsonInfo> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		return entity;
	}
	
///////////////////////////////////////////////////////////////////	
//	For all exceptions
//////////////////////////////////////////////////////////////////
//	@ExceptionHandler({
//	ClientNotFoundException.class,
//		SQLException.class,IOException.class,
//		Exception.class})
//	public ResponseEntity<Object> handleExceptions(final Throwable throwable, 
//			HttpServletRequest req){
//		ExceptionJsonInfo response = new ExceptionJsonInfo();
//		response.setDate(LocalDateTime.now());
//		response.setMessage(throwable.getLocalizedMessage());//message must be universal
//		response.setUrl(req.getRequestURL().toString());
//		
//		ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//		return entity;
//	}
}
