package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String addCurvePoint(CurvePoint curvePoint) {
		if (curvePoint == null) {
			ResponseEntity.status(400).body("rating is null");
		}
		Optional<CurvePoint> c = curvePointRepository.findById(curvePoint.getId());
		if (c.isPresent()) {
			ResponseEntity.status(400).body("curvePoint exist in database");
		}
		curvePointRepository.save(curvePoint);
		return "trade add with success";
	}
	
	/**
	 * @Description method for update curvePoint
	 */
	public CurvePoint updateCurvePoint(int id, CurvePoint curvePoint) {
		Optional<CurvePoint> c = curvePointRepository.findById( id);
		if (c.isEmpty()) {
			ResponseEntity.status(400).body("curvePoint not exist in database");
		}
		curvePointRepository.save(curvePoint);
		return c.get();
	}
	
	/**
	 * @Description method for update curvePoint
	 */
	public String deleteCurvePoint(long id) {
		Optional<CurvePoint> c = curvePointRepository.findById(id);
		curvePointRepository.delete(c.get());
		return "curvePoint delete with success";
	}
}
