package com.nnk.springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
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
		if (signin == null) {
			ResponseEntity.status(400).body("Informations for signin not found");
		}
		return myUserDetailService.loadUserByUsername(signin.getUsername());
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
            isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public UserDetails autoLogin(SigninDto signin) {
        UserDetails userDetails = myUserDetailService.loadUserByUsername(signin.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, signin.getPassword(), userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", signin.getUsername()));
        }
        return userDetails;
    }
}
