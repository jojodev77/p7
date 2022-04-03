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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@ExtendWith(MockitoExtension.class)
public class CurveServiceTest {

	@Spy
	@InjectMocks
	private CurvePointService curvePointService = new CurvePointService();

	@Mock
	CurvePointRepository curvePointRepository;

	/**
	 * @Description method for add curvePoint with succes
	 */
	@Test
	public void addRradeTest() {
		// GIVEN
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		// WHEN
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		// THEN
		curvePointService.addCurvePoint(curve);
		verify(curvePointService).addCurvePoint(curve);

	}

	/**
	 * @Description method for add curvePoint with error
	 */
	@Test
	public void addRradeTestwithError() {
		// GIVEN
		CurvePoint curve = new CurvePoint();
		curve.setId(1);
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		ResponseEntity resp = new ResponseEntity<>("curvePoint add with success", HttpStatus.OK);
		// WHEN
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		// THEN
		// THEN
		// THEN
		assertEquals(curvePointService.addCurvePoint(curve), resp);

	}
	
	/**
	 * @Description method for update curvePoint with succes
	 */
	@Test
	public void updateRadeTest() {
		// GIVEN
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		// WHEN
		curvePointRepository.save(curve);
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		// THEN
		curvePointService.updateCurvePoint(1, curve);
		verify(curvePointService).updateCurvePoint(1, curve);

	}
	
	/**
	 * @Description method for delete curvePoint with succes
	 */
	@Test
	public void deleteRadeTest() {
		// GIVEN
		CurvePoint curve = new CurvePoint();
		curve.setTerm(2);
		curve.setValue(4);
		List<CurvePoint> lc = new ArrayList<>();
		lc.add(curve);
		// WHEN
		curvePointRepository.save(curve);
		lenient().when(curvePointRepository.findById(anyLong())).thenReturn(Optional.of(curve));
		// THEN
		curvePointService.deleteCurvePoint(1);
		verify(curvePointService).deleteCurvePoint(1);

	}
}
