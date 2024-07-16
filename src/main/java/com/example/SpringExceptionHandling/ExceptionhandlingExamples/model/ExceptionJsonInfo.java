package com.example.SpringExceptionHandling.ExceptionhandlingExamples.model;

import java.time.LocalDateTime;

public class ExceptionJsonInfo {

	private String url;
	private String message;
	private LocalDateTime date;
	
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
