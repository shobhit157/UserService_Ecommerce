package com.example.userService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.DTO.UserDTO;
import com.example.userService.DTO.UserRequestDTO;
import com.example.userService.mode.User;
import com.example.userService.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	GET /users/all: Retrieve a list of all users.
//	● GET /users/{userId}: Retrieve a specific user by ID.
//	● GET /users/search/{keywords}: To search a user
//	● PUT /users/{userId} (Body: UserDto Object): Update the user with that particular
//	user-id
//	● DELETE /users/{userId}: Delete the user with that particular user-id
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return userService.getAll();
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId)
	{
		User user=userService.getUserById(userId);
		if(user!=null)
		{
			UserDTO userDto=new UserDTO();
			userDto.setEmail(user.getEmail());
			userDto.setName(user.getName());
			userDto.setUsername(user.getUsername());
			return ResponseEntity.ok(userDto);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{keywords}")
	public List<User> searchUser(@PathVariable String keywords)
	{
		return userService.searchUser(keywords);
	}
	
	@PutMapping("/{userId}")
	public void updateUser(@PathVariable Long userId,@RequestBody UserRequestDTO userDto)
	{
		userService.updateUser(userId,userDto);
	}

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId)
    {
    	userService.deleteUser(userId);
    }
    
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateUser(@RequestParam String username) {
        boolean exists = userService.userExists(username); // Query the database
        return ResponseEntity.ok(exists);
    }
}