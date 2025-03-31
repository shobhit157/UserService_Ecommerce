package com.example.userService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userService.DTO.JwtRequest;
import com.example.userService.DTO.JwtResponse;
import com.example.userService.DTO.UserRequestDTO;
import com.example.userService.mode.User;
import com.example.userService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
//	POST /api/auth/signin: Authenticate user credentials and generate an access
//	token.
//	● POST /api/auth/signup (Body: User Object): Create a new user.
//	● GET /api/auth/test: allows to check the health of the application
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> signin(@RequestBody JwtRequest jwtRequest)
	{
		return new ResponseEntity<>(authService.login(jwtRequest),HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public void signup(@RequestBody UserRequestDTO userRequestDto)
	{
		authService.signupUser(userRequestDto);
	}
	

}
