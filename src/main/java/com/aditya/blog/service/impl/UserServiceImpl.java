package com.aditya.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aditya.blog.config.AppConstants;
import com.aditya.blog.entities.Role;
import com.aditya.blog.entities.User;
import com.aditya.blog.payload.UserDTO;
import com.aditya.blog.repository.RoleRepository;
import com.aditya.blog.repository.UserRepository;
import com.aditya.blog.service.UserService;
import com.aditya.blog.exception.*;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class); 
		User response = userRepository.save(user);
		return modelMapper.map(response, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));
		userDTO.setId(userId);
		user = modelMapper.map(userDTO, User.class);
		User response = userRepository.save(user);
		return modelMapper.map(response, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id", userId));
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> response = new ArrayList<>();
		users.stream().forEach(x->response.add(modelMapper.map(x, UserDTO.class)));
		return response;
	}

	@Override
	public void deleteUser(Integer userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user  =  modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		Role role = roleRepo.findById(AppConstants.NORMAL_ROLE).get();
		
		user.getRoles().add(role);
		
		User response = userRepository.save(user);
		
		return modelMapper.map(response, UserDTO.class);
	}

}
