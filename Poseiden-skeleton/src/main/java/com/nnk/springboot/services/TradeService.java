package com.nnk.springboot.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nnk.springboot.Dto.TradeDto;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {
	
	@Autowired
	TradeRepository tradeRepository;
	

	/**
	 * @Description method for add trade
	 */
	public String addRrade(Trade trade) {
		if (trade == null) {
			ResponseEntity.status(400).body("rating is null");
		}
		Optional<Trade> t = tradeRepository.findById(trade.getId());
		if (t.isPresent()) {
			ResponseEntity.status(400).body("trade exist in database");
		}
		LocalDateTime now = LocalDateTime.now();
		trade.setCreationDate(now);
		tradeRepository.save(trade);
		return "trade add with success";
	}
	
	/**
	 * @Description method for update trade
	 */
	public Trade updateTrade(int id, Trade trade) {
		Optional<Trade> t = tradeRepository.findById( id);
		if (t.isEmpty()) {
			ResponseEntity.status(400).body("trade not exist in database");
		}
		LocalDateTime now = LocalDateTime.now();
		trade.setRevisionDate(now);
		tradeRepository.save(trade);
		return t.get();
	}
	
	/**
	 * @Description method for update trade
	 */
	public String deleteTrade(long id) {
		Optional<Trade> t = tradeRepository.findById(id);
		tradeRepository.delete(t.get());
		return "trade delete with success";
	}
}
