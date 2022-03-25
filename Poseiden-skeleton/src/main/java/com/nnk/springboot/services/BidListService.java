package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		Optional<BidList> b = bidListRepository.findById(bidlist.getId());
		if (b.isPresent()) {
			ResponseEntity.status(400).body("BidList exist in database");
		}
		bidListRepository.save(bidlist);
		return "BidList add with success";
	}
	
	/**
	 * @Description method for update BidList
	 */
	public BidList updateBidList(int id) {
		Optional<BidList> b = bidListRepository.findById( id);
		if (b.isEmpty()) {
			ResponseEntity.status(400).body("BidList not exist in database");
		}
		bidListRepository.save(b.get());
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
