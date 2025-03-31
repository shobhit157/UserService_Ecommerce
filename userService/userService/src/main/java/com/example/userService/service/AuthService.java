package com.example.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userService.DTO.JwtRequest;
import com.example.userService.DTO.JwtResponse;
import com.example.userService.DTO.UserRequestDTO;
import com.example.userService.Jwt.JwtAuthenticationHelper;
import com.example.userService.mode.User;
import com.example.userService.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationHelper jwtHelper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public JwtResponse login(JwtRequest jwtRequest) {
		// TODO Auto-generated method stub
		this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());

	    // Generate JWT token
	    String token = jwtHelper.generateToken(userDetails);

	    // Return the response
	    JwtResponse response = JwtResponse.builder().token(token).build();
	    return response;
	
	}
	
	
	public void doAuthenticate(String username,String password)
	{
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
		try {
            manager.authenticate(authenticationToken);

        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
	}

	public void signupUser(UserRequestDTO userRequestDto) {
		// TODO Auto-generated method 
		
		User user=User.builder().email(userRequestDto.getEmail())
				.name(userRequestDto.getName())
				.username(userRequestDto.getUsername())
				.password(passwordEncoder.encode(userRequestDto.getPassword())).build();
		
		
		userRepository.save(user);
	}

}
