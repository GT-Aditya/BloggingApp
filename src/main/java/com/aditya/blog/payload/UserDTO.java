package com.aditya.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDTO {
	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be of minimum 4 characters")
	private String name;
	@Email(message = "Provided email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 3, message = "Password must be of min 3 or max 10 characters")
	private String password;
	@NotEmpty
	private String about;
	
}
