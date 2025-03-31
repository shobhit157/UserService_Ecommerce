package com.example.userService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.userService.mode.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByKeyword(String keyword);
	
	Optional<User> findByUsername(String username);

}
