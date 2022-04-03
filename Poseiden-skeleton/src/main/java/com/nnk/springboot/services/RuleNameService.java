package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

	
@Autowired
RuleNameRepository ruleNameRepository;

/**
 * @Description method for add BidList
 */
public ResponseEntity<?> addBidList(RuleName ruleName) {
	if (ruleName == null) {
		new ResponseEntity<>("ruleName is null", HttpStatus.NOT_ACCEPTABLE);
		new RuntimeException();
	}
	Optional<RuleName> c = ruleNameRepository.findById(ruleName.getId());
	if (c.isPresent()) {
		new ResponseEntity<>("BidList exist in database", HttpStatus.NOT_ACCEPTABLE);
		new RuntimeException();
	}
	ruleNameRepository.save(ruleName);
	return new ResponseEntity<>("ruleName add with success", HttpStatus.OK);
}

/**
 * @Description method for update BidList
 */
public ResponseEntity<?> updateBidList(int id, RuleName ruleName) {
	Optional<RuleName> r = ruleNameRepository.findById( id);
	if (r.isEmpty()) {
		new ResponseEntity<>("ruleName not exist in database", HttpStatus.NOT_ACCEPTABLE);
		new RuntimeException();
	}
	ruleNameRepository.save(ruleName);
	 return new ResponseEntity<>("ruleName udpate with success", HttpStatus.OK);
}

/**
 * @Description method for update BidList
 */
public ResponseEntity<?> deleteBidList(long id) {
	Optional<RuleName> r = ruleNameRepository.findById(id);
	ruleNameRepository.delete(r.get());
	 return new ResponseEntity<>("ruleName delete with success", HttpStatus.OK);
}
}