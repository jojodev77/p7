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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.RatingService;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
	@Spy
	@InjectMocks
	private RatingService ratingService = new RatingService();

	@Mock
	RatingRepository ratingRepository;

	/**
	 * @Description method for add curvePoint with succes
	 */
	@Test
	public void addRradeTest() {
		// GIVEN
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		// WHEN
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		// THEN
		ratingService.addRating(rating);
		verify(ratingService).addRating(rating);

	}

	/**
	 * @Description method for add curvePoint with error
	 */
	@Test
	public void addRradeTestwithError() {
		// GIVEN
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		ResponseEntity resp = new ResponseEntity<>("rating add with success", HttpStatus.OK);
		// WHEN
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		// THEN
		// THEN
		// THEN
		assertEquals(ratingService.addRating(rating), resp);

	}
	
	/**
	 * @Description method for update curvePoint with succes
	 */
	@Test
	public void updateRadeTest() {
		// GIVEN
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		rating.setId(1);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		// WHEN
		ratingRepository.save(rating);
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		// THEN
		ratingService.updateRating(1, rating);
		verify(ratingService).updateRating(1, rating);

	}
	
	/**
	 * @Description method for delete curvePoint with succes
	 */
	@Test
	public void deleteRadeTest() {
		// GIVEN
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		// WHEN
		ratingRepository.save(rating);
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		// THEN
		ratingService.deleteRating(1);
		verify(ratingService).deleteRating(1);

	}
}

