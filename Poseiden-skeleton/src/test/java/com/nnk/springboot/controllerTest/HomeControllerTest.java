package com.nnk.springboot.controllerTest;

import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.HomeController;
import com.nnk.springboot.domain.BidList;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {
	
	@InjectMocks
	private static HomeController homeController;
	
	private MockMvc mockMvc;

	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}
	
	/**
	 * @Description test get home page login
	 */
	@Test
	public void home() {
		try {
			mockMvc
			  .perform(get("/home"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("home"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get home page with url address empty
	 */
	@Test
	public void homeWithUrlEmpty() {
		try {
			mockMvc
			  .perform(get("/"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("home"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get home page login with role admin
	 */
	@Test
	public void homeAdmin() {
		try {
			mockMvc
			  .perform(get("/admin/home"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("home"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
