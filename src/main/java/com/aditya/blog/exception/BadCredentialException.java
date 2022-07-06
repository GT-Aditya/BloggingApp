package com.aditya.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String causeMessage;
	private String sourceName;
	
	public BadCredentialException(String sourceName, String causeMessage) {
		super(String.format("Root cause %s : provided username or password is not valid in %s ", causeMessage, sourceName));
		this.causeMessage = causeMessage;
		this.sourceName = sourceName;
	}

}
