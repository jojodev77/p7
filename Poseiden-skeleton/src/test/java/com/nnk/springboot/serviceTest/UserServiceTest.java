package com.nnk.springboot.serviceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.config.MyUserDetailsService;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Spy
	@InjectMocks
	private UserService userService = new UserService();
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	MyUserDetailsService myUserDetailService;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	/**
	 * @Description test createUser with success
	 */
	@Test
	public void createUserWithSucces() {
		//GIVEN
		User user = new User();
		user.setFullname("jhh");
		user.setPassword("ggggA#hhfffff.");
		user.setUsername("hgj");
		User user1 = new User();
		user1.setFullname("jDDhh");
		user1.setPassword("ggggA#hhfffff.");
		user1.setUsername("hgDDj");
		//WHEN
		lenient().when(userRepository.findById((int) anyLong())).thenReturn(Optional.of(user1));
		//THEN
		userService.createUser(user);
		verify(userService).createUser(user);
	}
	
	/**
	 * @Description test signin with success
	 */
	@Test
	public void signinUserWithSucces() {
		//GIVEN
		User user = new User();
		user.setFullname("jhh");
		user.setPassword("ggggA#hhfffff.");
		user.setUsername("hgj");
		SigninDto user1 = new SigninDto();
		user1.setPassword("ggggA#hhfffff.");
		user1.setUsername("hgDDj");
		userRepository.save(user);
		UserDetails userD = null; 
		//WHEN
		lenient().when(userRepository.findById((int) anyLong())).thenReturn(Optional.of(user));
		lenient().when(myUserDetailService.loadUserByUsername(anyString())).thenReturn(userD);
		//THEN
		userService.signin(user1);
		verify(userService).signin(user1);
	}
	

	/**
	 * @Description test isValidPassword with success
	 */
	@Test
	public void isValidPasswordUserWithSucces() {
		//GIVEN
		User user = new User();
		user.setFullname("jhh");
		user.setPassword("ggggA#hhfffff.");
		user.setUsername("hgj");
		SigninDto user1 = new SigninDto();
		user1.setPassword("ggggA#hhfffff.");
		user1.setUsername("hgDDj");
		userRepository.save(user);
		//WHEN
		//THEN
		userService.isValid(user1.getPassword());
		verify(userService).isValid(user1.getPassword());
	}
	
	
}
