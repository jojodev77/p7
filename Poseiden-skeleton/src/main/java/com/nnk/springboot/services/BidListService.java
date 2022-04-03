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
	public ResponseEntity<?> addBidList(BidList bidlist) {
		if (bidlist == null) {
			new ResponseEntity<>("BidList is null", HttpStatus.NOT_ACCEPTABLE);
			new RuntimeException();
				}
		LocalDateTime now = LocalDateTime.now();
		bidlist.setCreationDate(now);
		Optional<BidList> b = bidListRepository.findById(bidlist.getId());
		if (b.isPresent()) {
			new ResponseEntity<>("BidList exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		bidListRepository.save(bidlist);
		return new ResponseEntity<>("bidList add with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update BidList
	 */
	public ResponseEntity<?> updateBidList(int id, BidList bidList) {
		LocalDateTime now = LocalDateTime.now();
		bidList.setRevisionDate(now);
		Optional<BidList> b = bidListRepository.findById( id);
		if (b.isEmpty()) {
			new ResponseEntity<>("BidList not exist in database", HttpStatus.NOT_ACCEPTABLE);
			new RuntimeException();
		}
		
		bidListRepository.save(bidList);
		return new ResponseEntity<>("bidList update with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update BidList
	 */
	public ResponseEntity<?> deleteBidList(long id) {
		Optional<BidList> b = bidListRepository.findById(id);
		bidListRepository.delete(b.get());
		return new ResponseEntity<>("bidList delete with success", HttpStatus.OK);
	}
}
