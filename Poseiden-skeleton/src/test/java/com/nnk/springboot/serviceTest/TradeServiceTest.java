package com.nnk.springboot.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

	@Spy
	@InjectMocks
	private TradeService tradeService = new TradeService();

	@Mock
	TradeRepository tradeRepository;

	/**
	 * @Description method for add trade with succes
	 */
	@Test
	public void addRradeTest() {
		// GIVEN
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		// WHEN
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
		// THEN
		tradeService.addRrade(trade);
		verify(tradeService).addRrade(trade);

	}

	/**
	 * @Description method for add trade with error
	 */
	@Test
	public void addRradeTestwithError() {
		// GIVEN
		Trade trade = new Trade();
		trade.setId(1);
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		// WHEN
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
		// THEN
		// THEN
		// THEN
		assertEquals(tradeService.addRrade(trade), "trade add with success");

	}
	
	/**
	 * @Description method for update trade with succes
	 */
	@Test
	public void updateRadeTest() {
		// GIVEN
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		// WHEN
		tradeRepository.save(trade);
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
		// THEN
		tradeService.updateTrade(1, trade);
		verify(tradeService).updateTrade(1, trade);

	}
	
	/**
	 * @Description method for delete trade with succes
	 */
	@Test
	public void deleteRadeTest() {
		// GIVEN
		Trade trade = new Trade();
		trade.setBook("fff");
		trade.setCreationName("ghj");
		trade.setAccount("hghgh");
		trade.setDealName("fff");
		List<Trade> lt = new ArrayList<>();
		lt.add(trade);
		// WHEN
		tradeRepository.save(trade);
		lenient().when(tradeRepository.findById(anyLong())).thenReturn(Optional.of(trade));
		// THEN
		tradeService.deleteTrade(1);
		verify(tradeService).deleteTrade(1);

	}
}
