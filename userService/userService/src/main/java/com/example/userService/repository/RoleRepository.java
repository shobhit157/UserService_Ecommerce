package com.example.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userService.mode.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

}
