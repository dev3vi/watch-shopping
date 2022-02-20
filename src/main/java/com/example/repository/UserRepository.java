package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	//boolean checkEmailsExits(String email);
	
	//boolean checkEmailsExits(null, null);
	
	Optional<User> findByUsernameAndHashPassword(String username, String password);
	
	@Query("select u.username from User u where u.email=:email")
	Optional<String> checkEmailsExits(String email);

}
