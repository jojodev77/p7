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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {
private MockMvc mockMvc;
	
	@InjectMocks
	private static RuleNameController ruleNameController;
	
	@Mock
	RuleNameRepository ruleNameRepository;
	
	@Mock
	RuleNameService ruleNameService;
	
	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(ruleNameController).build();
	}
	
	/**
	 * @Description test get list ruleName
	 */
	@Test
	public void home() {
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		lenient().when(ruleNameRepository.findAll()).thenReturn(lr);
		
		try {
			mockMvc
			  .perform(get("/ruleName/list"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("ruleName/list"));
			 // .andExpect(model().attribute("ruleName", ruleName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get add ruleName
	 */
	@Test
	public void getRuleName() {
		try {
			mockMvc
			  .perform(get("/ruleName/add"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("ruleName/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post add ruleName
	 */
	@Test
	public void postAddRuleName() {
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		
		try {
			mockMvc
			  .perform(post("/ruleName/validate"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("ruleName/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get update ruleName
	 */
	@Test
	public void getUpdateRuleName() {
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		
		try {
			mockMvc
			  .perform(get("/ruleName/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("ruleName/update"))
			  .andExpect(model().attribute("ruleName", ruleName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void postUpdateRuleName() {
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		ResponseEntity resp = new ResponseEntity<>("ruleName update with success", HttpStatus.OK);
		lenient().when(ruleNameService.updateBidList(1, ruleName)).thenReturn(resp);
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		
		try {
			mockMvc
			  .perform(post("/ruleName/update/1"))
			  .andExpect(view().name("redirect:/ruleName/list"));
			//  .andExpect(model().attribute("ruleName", ruleName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void getDeleteRuleName() {
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		ResponseEntity resp = new ResponseEntity<>("ruleName delete with success", HttpStatus.OK);
		lenient().when(ruleNameService.deleteBidList(anyLong())).thenReturn(resp);
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		lenient().when(ruleNameRepository.findAll()).thenReturn(lr);
		
		try {
			mockMvc
			  .perform(get("/ruleName/delete/1"))
			  .andExpect(view().name("redirect:/ruleName/list"))
			  .andExpect(model().attribute("ruleName", lr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
