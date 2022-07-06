package com.aditya.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	static final long serialVersionUID = -7034897190745766939L;
	
	private String resourceName;
	private String resourceField;
	private Integer resourceValue;
	private String resourceVal;
	
	
	public ResourceNotFoundException(String resourceName, String resourceField, Integer resourceValue) {
		super(String.format("%s not found with %s : %d", resourceName, resourceField, resourceValue));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.resourceValue = resourceValue;
	}
	public ResourceNotFoundException(String resourceName, String resourceField, String resourceVal) {
		super(String.format("%s not found with %s : %s", resourceName, resourceField, resourceVal));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.resourceVal = resourceVal;
	}
	
}
