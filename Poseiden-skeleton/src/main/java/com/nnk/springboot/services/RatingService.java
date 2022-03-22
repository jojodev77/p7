package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nnk.springboot.Dto.RatingDto;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	RatingRepository ratingRepository;

	/**
	 * @Description method for add rating
	 */
	public String addRating(RatingDto rating) {
		Optional<Rating> r = ratingRepository.findBySandPRating(rating.getFitchRating());
		if (r.isPresent()) {
			ResponseEntity.status(400).body("rating exist in database");
		}
		ratingRepository.save(r.get());
		return "rating add with success";
	}
	
	/**
	 * @Description method for update rating
	 */
	public String updateRating(RatingDto rating) {
		Optional<Rating> r = ratingRepository.findBySandPRating(rating.getFitchRating());
		if (r.isEmpty()) {
			ResponseEntity.status(400).body("rating not exist in database");
		}
		ratingRepository.save(r.get());
		return "rating update with success";
	}
	
	/**
	 * @Description method for update rating
	 */
	public String deleteRating(long id) {
		Optional<Rating> r = ratingRepository.findById((int) id);
		if (r.isEmpty()) {
			ResponseEntity.status(400).body("rating not exist in database");
		}
		ratingRepository.delete(r.get());
		return "rating delete with success";
	}
}
