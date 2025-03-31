package com.example.userService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userService.DTO.UserRequestDTO;
import com.example.userService.mode.User;
import com.example.userService.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).get();
	}

	public List<User> searchUser(String keywords) {
		// TODO Auto-generated method stub
		return userRepository.searchByKeyword(keywords);
	}

	public void updateUser(Long userId, UserRequestDTO userDto) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).get();
		
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		
		
		userRepository.save(user);
		
		
		
		
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
		userRepository.deleteById(userId);
		
	}

	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		
		if(userRepository.findByUsername(username).get()!=null)
		{
			return true;
		}
		return false;
	}




	
	
}
