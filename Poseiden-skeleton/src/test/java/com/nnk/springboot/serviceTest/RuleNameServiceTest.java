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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.RuleNameService;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {
	@Spy
	@InjectMocks
	private RuleNameService ruleNameService = new RuleNameService();

	@Mock
	RuleNameRepository ruleNameRepository;

	/**
	 * @Description method for add curvePoint with succes
	 */
	@Test
	public void addRradeTest() {
		// GIVEN
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		// WHEN
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		// THEN
		ruleNameService.addBidList(ruleName);
		verify(ruleNameService).addBidList(ruleName);

	}

	/**
	 * @Description method for add curvePoint with error
	 */
	@Test
	public void addRradeTestwithError() {
		// GIVEN
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		// WHEN
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		// THEN
		// THEN
		// THEN
		assertEquals(ruleNameService.addBidList(ruleName), "ruleName add with success");

	}
	
	/**
	 * @Description method for update curvePoint with succes
	 */
	@Test
	public void updateRadeTest() {
		// GIVEN
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		// WHEN
		ruleNameRepository.save(ruleName);
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		// THEN
		ruleNameService.updateBidList(1, ruleName);
		verify(ruleNameService).updateBidList(1, ruleName);

	}
	
	/**
	 * @Description method for delete curvePoint with succes
	 */
	@Test
	public void deleteRadeTest() {
		// GIVEN
		RuleName ruleName = new RuleName();
		ruleName.setDescription("fff");
		ruleName.setJson("ghj");
		ruleName.setSqlPart("hghgh");
		ruleName.setSqlStr("fff");
		List<RuleName> lr = new ArrayList<>();
		lr.add(ruleName);
		// WHEN
		ruleNameRepository.save(ruleName);
		lenient().when(ruleNameRepository.findById(anyLong())).thenReturn(Optional.of(ruleName));
		// THEN
		ruleNameService.deleteBidList(1);
		verify(ruleNameService).deleteBidList(1);

	}
}

