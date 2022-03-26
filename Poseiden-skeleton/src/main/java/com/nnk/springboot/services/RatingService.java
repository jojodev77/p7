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
	public String addRating(Rating rating) {
		if (rating == null) {
			ResponseEntity.status(400).body("rating is null");
		}
		Optional<Rating> r = ratingRepository.findBySandPRating(rating.getFitchRating());
		if (r.isPresent()) {
			ResponseEntity.status(400).body("rating exist in database");
		}
		ratingRepository.save(rating);
		return "rating add with success";
	}
	
	/**
	 * @Description method for update rating
	 */
	public Rating updateRating(int id, Rating rating) {
		Optional<Rating> r = ratingRepository.findById( id);
		if (r.isEmpty()) {
			ResponseEntity.status(400).body("rating not exist in database");
		}
		ratingRepository.save(rating);
		return r.get();
	}
	
	/**
	 * @Description method for update rating
	 */
	public String deleteRating(long id) {
		Optional<Rating> r = ratingRepository.findById(id);
		ratingRepository.delete(r.get());
		return "rating delete with success";
	}
}
