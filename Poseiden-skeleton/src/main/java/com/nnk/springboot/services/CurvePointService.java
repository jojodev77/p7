package com.nnk.springboot.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository curvePointRepository;
	
	/**
	 * @Description method for add curvePoint
	 */
	public ResponseEntity<?> addCurvePoint(CurvePoint curvePoint) {
		if (curvePoint == null) {
			new ResponseEntity<>("curvePoint is null", HttpStatus.NOT_ACCEPTABLE);
		}
		Optional<CurvePoint> c = curvePointRepository.findById(curvePoint.getId());
		if (c.isPresent()) {
			new ResponseEntity<>("curvePoint exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		LocalDateTime now = LocalDateTime.now();
		curvePoint.setCreationDate(now);
		
		curvePointRepository.save(curvePoint);
		 return new ResponseEntity<>("curvePoint add with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update curvePoint
	 */
	public ResponseEntity<?> updateCurvePoint(int id, CurvePoint curvePoint) {
		Optional<CurvePoint> c = curvePointRepository.findById( id);
		if (c.isEmpty()) {
			new ResponseEntity<>("curvePoint not exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		LocalDateTime now = LocalDateTime.now();
		curvePoint.setAsOfDate(now);
		curvePointRepository.save(curvePoint);
		return new ResponseEntity<>("curvePoint update with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update curvePoint
	 */
	public ResponseEntity<?> deleteCurvePoint(long id) {
		Optional<CurvePoint> c = curvePointRepository.findById(id);
		curvePointRepository.delete(c.get());
		return new ResponseEntity<>("curvePoint delete with success", HttpStatus.OK);
	}
}
