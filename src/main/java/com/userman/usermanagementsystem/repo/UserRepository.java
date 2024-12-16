package com.userman.usermanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userman.usermanagementsystem.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

	public boolean existsByEmail(String email);

	public UserDtls findByEmail(String email);
	
	public UserDtls findByEmailAndPhonenumber(String email, String phonenumber);

	
	public UserDtls findByVerificationcode(String verificationcode);

	
}
