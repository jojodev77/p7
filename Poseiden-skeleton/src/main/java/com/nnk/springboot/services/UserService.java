package com.nnk.springboot.services;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.config.ExcludeFromJacocoGeneratedReport;
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
	public ResponseEntity<?> createUser(User user) {
		Optional<User> u = userRepository.findByUsername(user.getUsername());
		if (u.isPresent()) {
			return new ResponseEntity<>("User is present in db", HttpStatus.NOT_ACCEPTABLE);
		}
		if (!isValid(user.getPassword())) {
			return new ResponseEntity<>("password is not good", HttpStatus.NOT_ACCEPTABLE);
		} else {
		
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          user.setPassword(encoder.encode(user.getPassword()));
          
        	  userRepository.save(user);
        	  return new ResponseEntity<>("User create with success", HttpStatus.OK);
		}
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

	@ExcludeFromJacocoGeneratedReport
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
            isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

	@ExcludeFromJacocoGeneratedReport
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
    
    public String resultError(String result) {
    	return result;
    }
    
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
