package com.example.userService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.userService.mode.User;
import com.example.userService.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService{

	   @Autowired
	   private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	        return new org.springframework.security.core.userdetails.User(
	            user.getEmail(),
	            user.getPassword(),
	            user.getRoles().stream()
	                .map(role -> new SimpleGrantedAuthority(role.getRolename())).toList());

}
}
