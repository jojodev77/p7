package com.nnk.springboot.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	BidListRepository bidListRepository;
	
	/**
	 * @Description method for add BidList
	 */
	public String addBidList(BidList bidlist) {
		if (bidlist == null) {
			ResponseEntity.status(400).body("rating is null");
		}
		LocalDateTime now = LocalDateTime.now();
		bidlist.setCreationDate(now);
		Optional<BidList> b = bidListRepository.findById(bidlist.getId());
		if (b.isPresent()) {
			new ResponseEntity<>("BidList exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		bidListRepository.save(bidlist);
		return "BidList add with success";
	}
	
	/**
	 * @Description method for update BidList
	 */
	public BidList updateBidList(int id, BidList bidList) {
		LocalDateTime now = LocalDateTime.now();
		bidList.setRevisionDate(now);
		Optional<BidList> b = bidListRepository.findById( id);
		if (b.isEmpty()) {
			new ResponseEntity<>("BidList not exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		
		bidListRepository.save(bidList);
		return b.get();
	}
	
	/**
	 * @Description method for update BidList
	 */
	public String deleteBidList(long id) {
		Optional<BidList> b = bidListRepository.findById(id);
		bidListRepository.delete(b.get());
		return "BidList delete with success";
	}
}
