package com.example.SpringExceptionHandling.ExceptionhandlingExamples.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Client Not Found")//404
public class ClientNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(Long id) {
		super("ClientNotFoundException with id :"+id);
	}
}
