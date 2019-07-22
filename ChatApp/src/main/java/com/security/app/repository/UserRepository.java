package com.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.app.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByusername(String username);
}
