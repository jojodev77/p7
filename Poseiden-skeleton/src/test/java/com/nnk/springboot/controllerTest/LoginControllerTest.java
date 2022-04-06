package com.nnk.springboot.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.controllers.LoginController;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;
	
	private MockMvc mockMvc;
	
	@Mock
	ModelAndView mav = new ModelAndView();
	
	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	
	/**
	 * @Description test get login
	 */
	@Test
	public void getLogin() {
		
		try {
			mockMvc
			  .perform(get("login"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get secure-article-detail
	 */
	@Test
	public void getSecureArticleDetail() {
		
		try {
			mockMvc
			  .perform(get("secure/article-details"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("user/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get login
	 */
	@Test
	public void getError() {
		String errorMessage= "You are not authorized for the requested data.";
		try {
			mockMvc
			  .perform(get("error"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("403"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
