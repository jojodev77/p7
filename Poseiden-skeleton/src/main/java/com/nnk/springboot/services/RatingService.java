package com.nnk.springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> addRating(Rating rating) {
		if (rating == null) {
			new ResponseEntity<>("rating is null", HttpStatus.NOT_ACCEPTABLE);
			new RuntimeException();
		}
		Optional<Rating> r = ratingRepository.findBySandPRating(rating.getFitchRating());
		if (r.isPresent()) {
			new ResponseEntity<>("rating exist in database", HttpStatus.NOT_ACCEPTABLE);
		}
		ratingRepository.save(rating);
		return new ResponseEntity<>("rating add with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update rating
	 */
	public ResponseEntity<?> updateRating(int id, Rating rating) {
		Optional<Rating> r = ratingRepository.findById( id);
		if (r.isEmpty()) {
			new ResponseEntity<>("rating not exist in database", HttpStatus.NOT_ACCEPTABLE);
			new RuntimeException();
		}
		ratingRepository.save(rating);
		return new ResponseEntity<>("rating update with success", HttpStatus.OK);
	}
	
	/**
	 * @Description method for update rating
	 */
	public ResponseEntity<?> deleteRating(long id) {
		Optional<Rating> r = ratingRepository.findById(id);
		ratingRepository.delete(r.get());
		return new ResponseEntity<>("rating delete with success", HttpStatus.OK);
	}
}
