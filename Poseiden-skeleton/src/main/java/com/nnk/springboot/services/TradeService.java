package com.nnk.springboot.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> addRrade(Trade trade) {
		if (trade == null) {
			new ResponseEntity<>("rating is null", HttpStatus.NOT_ACCEPTABLE);
		}
		Optional<Trade> t = tradeRepository.findById(trade.getId());
		if (t.isPresent()) {
			new ResponseEntity<>("trade exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		LocalDateTime now = LocalDateTime.now();
		trade.setCreationDate(now);
		tradeRepository.save(trade);
		return new ResponseEntity<>("trade add with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update trade
	 */
	public ResponseEntity<?> updateTrade(int id, Trade trade) {
		Optional<Trade> t = tradeRepository.findById( id);
		if (t.isEmpty()) {
			new ResponseEntity<>("trade not exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		LocalDateTime now = LocalDateTime.now();
		trade.setRevisionDate(now);
		tradeRepository.save(trade);
		return new ResponseEntity<>("trade update with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update trade
	 */
	public ResponseEntity<?> deleteTrade(long id) {
		Optional<Trade> t = tradeRepository.findById(id);
		tradeRepository.delete(t.get());
		return new ResponseEntity<>("trade delete with success", HttpStatus.OK);
	}
}
