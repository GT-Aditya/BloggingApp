package com.aditya.blog.service;

import java.util.List;

import com.aditya.blog.payload.UserDTO;

public interface UserService {
	
	UserDTO registerUser(UserDTO user);

	UserDTO createUser(UserDTO user);

	UserDTO updateUser(UserDTO user, Integer userId);

	UserDTO getUserById(Integer userId);

	List<UserDTO> getAllUsers();

	void deleteUser(Integer userId);
}
