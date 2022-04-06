package com.nnk.springboot.controllerTest;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
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
import org.springframework.validation.BindingResult;

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
		Trade t = new Trade();
		t.setType("ghj");
		t.setBuyQuantity(2);
		t.setAccount("hghgh");
		List<Trade> lt = new ArrayList<>();
		lt.add(t);
		try {
			 BindingResult result = mock(BindingResult.class);
			 lenient().when(result.hasErrors()).thenReturn(false);
			mockMvc.perform(post("/trade/validate"))
			.andExpect(view().name("trade/list"));
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
		 BindingResult result = mock(BindingResult.class);
		 lenient().when(result.hasErrors()).thenReturn(false);

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
		ResponseEntity resp = new ResponseEntity<>("trade update with success", HttpStatus.OK);
		lenient().when(tradeService.updateTrade(1, trade)).thenReturn(resp);
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));

		try {
			 BindingResult result = mock(BindingResult.class);
			 lenient().when(result.hasErrors()).thenReturn(false);
			mockMvc.perform(post("/trade/update/1"))
			.andExpect(view().name("redirect:/trade/list"));
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
		ResponseEntity resp = new ResponseEntity<>("trade delete with success", HttpStatus.OK);
		lenient().when(tradeService.deleteTrade(anyLong())).thenReturn(resp);
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
