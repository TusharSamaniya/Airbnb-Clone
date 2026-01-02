package com.tushar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findbyEmial(String email);

}
