package com.userman.usermanagementsystem.service;

import com.userman.usermanagementsystem.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user , String url);
	
      public boolean checkEmail(String email);
      
	 public boolean verifyAccount(String code);
	
	
	
}
