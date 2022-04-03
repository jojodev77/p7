package com.nnk.springboot.controllerTest;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserRepository userRepository;
	
	private MockMvc mockMvc;
	
	@Mock
	UserService userService;

	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	/**
	 * @Description test get list bidList
	 */
	@Test
	public void getUserAdminBySignin() {
		SigninDto signin = new SigninDto();
		signin.setPassword("tototototo");
		signin.setUsername("toto");
		
		try {
			mockMvc
			  .perform(get("/user/login"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/login"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post for signin with user
	 */
	@Test
	public void postUserAdminBySignin() {
		SigninDto signin = new SigninDto();
		signin.setPassword("tototototo");
		signin.setUsername("toto");
		
		try {
			mockMvc
			  .perform(post("/user/login"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/userH"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get pour view menu user
	 */
	@Test
	public void getUserHTest() {
		try {
			mockMvc
			  .perform(get("/user/userH"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/userH"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * @Description test get for view list user
	 */
	@Test
	public void getListUserTest() {
		try {
			mockMvc
			  .perform(get("/user/list"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get for add  user
	 */
	@Test
	public void getaddUserTest() {
		try {
			mockMvc
			  .perform(get("/user/add"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post add user
	 */
	@Test
	public void postAddUser() {
		User user = new User();
		user.setFullname("jojo");
		user.setPassword("fggfrdf");
		user.setRole("ADMON");
		user.setUsername("test");
		List<User> lu = new ArrayList<>();
		lu.add(user);
		lenient().when(userRepository.findAll()).thenReturn(lu);
		
		try {
			mockMvc
			  .perform(post("/user/validate"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get for update user
	 */
	@Test
	public void getaddUpdateTest() {
		try {
			mockMvc
			  .perform(get("/user/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/update"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get for delete user
	 */
	@Test
	public void getDeleteTest() {
		User u = new User();
		u.setFullname("ddd");
		u.setPassword("sdfghjklDf888#");
		u.setUsername("ggghcfff");
		u.setId(1);
		userRepository.save(u);
		List<User> lu= new ArrayList<>();
		try {
			mockMvc
			  .perform(get("/user/delete/1"))
			  .andExpect(view().name("redirect:/user/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get update user
	 */
	@Test
	public void getUpdateUser() {
		User u = new User();
		u.setFullname("ddd");
		u.setPassword("sdfghjklDf888#");
		u.setUsername("ggghcfff");
		u.setId(1);
		userRepository.save(u);
		List<User> lu= new ArrayList<>();
		lenient().when(userRepository.findById((int) anyLong())).thenReturn(Optional.of(u));
		
		try {
			mockMvc
			  .perform(get("/user/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/update"))
			  .andExpect(model().attribute("user", u));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	/**
//	 * @Description test post update user
//	 */
//	@Test
//	public void postUpdateUser() {
//		User u = new User();
//		u.setFullname("ddd");
//		u.setPassword("sdfghjklDf888#");
//		u.setUsername("ggghcfff");
//		u.setId(1);
//		lenient().when(userRepository.findById((int) anyLong())).thenReturn(Optional.of(u));
//		
//		try {
//			mockMvc
//			  .perform(post("/user/update/1"))
//			  .andExpect(view().name("redirect:/user/list"));
//			//  .andExpect(model().attribute("ruleName", ruleName));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * @Description test get for access userH interface
	 */
	@Test
	public void getaccessUserHTest() {
		try {
			mockMvc
			  .perform(get("/user/userH"))
			  .andExpect(view().name("user/userH"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
