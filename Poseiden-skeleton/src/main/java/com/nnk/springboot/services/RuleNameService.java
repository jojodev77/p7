package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public String addBidList(RuleName ruleName) {
	if (ruleName == null) {
		ResponseEntity.status(400).body("ruleName is null");
	}
	Optional<RuleName> c = ruleNameRepository.findById(ruleName.getId());
	if (c.isPresent()) {
		ResponseEntity.status(400).body("BidList exist in database");
	}
	ruleNameRepository.save(ruleName);
	return "ruleName add with success";
}

/**
 * @Description method for update BidList
 */
public RuleName updateBidList(int id, RuleName ruleName) {
	Optional<RuleName> r = ruleNameRepository.findById( id);
	if (r.isEmpty()) {
		ResponseEntity.status(400).body("ruleName not exist in database");
	}
	ruleNameRepository.save(ruleName);
	return r.get();
}

/**
 * @Description method for update BidList
 */
public String deleteBidList(long id) {
	Optional<RuleName> r = ruleNameRepository.findById(id);
	ruleNameRepository.delete(r.get());
	return "ruleName delete with success";
}
}