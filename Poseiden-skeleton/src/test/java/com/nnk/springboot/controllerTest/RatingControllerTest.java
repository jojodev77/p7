package com.nnk.springboot.controllerTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private static RatingController ratingController;
	
	@Mock
	RatingRepository ratingRepository;
	
	@Mock
	RatingService ratingService;
	
	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
	}
	
	/**
	 * @Description test get list rating
	 */
	@Test
	public void home() {
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		lenient().when(ratingRepository.findAll()).thenReturn(lr);
		
		try {
			mockMvc
			  .perform(get("/rating/list"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("rating/list"))
			  .andExpect(model().attribute("rating", lr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get add rating
	 */
	@Test
	public void getAddRating() {
		try {
			mockMvc
			  .perform(get("/rating/add"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("rating/add"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post add rating
	 */
	@Test
	public void postAddRating() {
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		BindingResult result = mock(BindingResult.class);
		lenient().when(result.hasErrors()).thenReturn(true);
		lenient().when(ratingRepository.findAll()).thenReturn(lr);
		
		try {
			mockMvc
			  .perform(post("/rating/validate"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("rating/list"))
			  .andExpect(model().attribute("rating", lr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test get update rating
	 */
	@Test
	public void getUpdateRating() {
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		
		try {
			mockMvc
			  .perform(get("/rating/update/1"))
			  .andExpect(status().isOk())
			  .andExpect(view().name("rating/update"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void postUpdateRating() {
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		BindingResult result = mock(BindingResult.class);
		lenient().when(result.hasErrors()).thenReturn(true);
		ResponseEntity resp = new ResponseEntity<>("rating update with success", HttpStatus.OK);
		lenient().when(ratingService.updateRating(1, rating)).thenReturn(resp);
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		
		try {
			mockMvc
			  .perform(post("/rating/update/1"))
			  .andExpect(view().name("redirect:/rating/list"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description test post update rating
	 */
	@Test
	public void getDeleteRating() {
		Rating rating = new Rating();
		rating.setFitchRating("fg");
		rating.setOrderNumber(4);
		List<Rating> lr = new ArrayList<>();
		lr.add(rating);
		ResponseEntity resp = new ResponseEntity<>("ruleName delete with success", HttpStatus.OK);
		lenient().when(ratingRepository.findById(anyLong())).thenReturn(Optional.of(rating));
		lenient().when(ratingService.deleteRating(anyLong())).thenReturn(resp);
		lenient().when(ratingRepository.findAll()).thenReturn(lr);
		
		try {
			mockMvc
			  .perform(get("/rating/delete/1"))
			  .andExpect(view().name("redirect:/rating/list"))
			  .andExpect(model().attribute("rating", lr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
