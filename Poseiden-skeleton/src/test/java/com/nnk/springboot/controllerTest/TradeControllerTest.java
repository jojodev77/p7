package com.nnk.springboot.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.RuleNameService;
import com.nnk.springboot.services.TradeService;

@ExtendWith(MockitoExtension.class)
public class TradeControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private static TradeController tradeController;

	@Mock
	TradeRepository tradeRepository;

	@Mock
	TradeService tradeService;

	@Mock
	Trade trade = new Trade();
	

	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
	}

	/**
	 * @Description test get list trade
	 */
	@Test
	public void home() {
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		lenient().when(tradeRepository.findAll()).thenReturn(lt);

		try {
			mockMvc.perform(get("/trade/list")).andExpect(status().isOk()).andExpect(view().name("trade/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description test get add trade
	 */
	@Test
	public void getaddTrade() {
		try {
			mockMvc.perform(get("/trade/add")).andExpect(status().isOk()).andExpect(view().name("trade/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description test post add trade
	 */
	@Test
	public void postAddTrade() {
		
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		trade.setBuyPrice(5);
		trade.setSecurity("ghvg");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		lenient().when(tradeRepository.findAll()).thenReturn(lt);
		try {
			mockMvc.perform(post("/trade/validate"))
			.andExpect(status().isOk())
					.andExpect(view().name("trade/list"))
					.andExpect(model().attribute("trade", lt));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description test get update trade
	 */
	@Test
	public void getUpdateTrade() {
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));

		try {
			mockMvc.perform(get("/trade/update/1")).andExpect(status().isOk()).andExpect(view().name("trade/update"))
					.andExpect(model().attribute("trade", trade));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description test post update trade
	 */
	@Test
	public void postUpdateTrade() {
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		BindingResult result = mock(BindingResult.class);
		lenient().when(result.hasErrors()).thenReturn(false);
		lenient().when(tradeService.updateTrade(1, trade)).thenReturn(trade);
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));

		try {
			mockMvc.perform(post("/trade/update/1")).andExpect(status().isOk()).andExpect(view().name("trade/update"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description test post update trade
	 */
	@Test
	public void getDeleteTrade() {
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		lenient().when(tradeService.deleteTrade(anyLong())).thenReturn("");
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
		lenient().when(tradeRepository.findAll()).thenReturn(lt);

		try {
			mockMvc.perform(get("/trade/delete/1")).andExpect(view().name("redirect:/trade/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
