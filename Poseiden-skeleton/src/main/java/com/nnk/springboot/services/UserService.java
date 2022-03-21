package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.config.MyUserDetailsService;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyUserDetailsService myUserDetailService;
	
	/**
	 * @description method for create User
	 */
	public User createUser() {
		User user = new User();
		return user;
	}
	
	/**
	 * @description method for create User
	 */
	public UserDetails signin(SigninDto signin) {

		UserDetails ud;
		if (signin == null) {
			ResponseEntity.status(400).body("Informations for signin not found");
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@€" + myUserDetailService.loadUserByUsername(signin.getUsername()));
		return myUserDetailService.loadUserByUsername(signin.getUsername());
		
	}
}
