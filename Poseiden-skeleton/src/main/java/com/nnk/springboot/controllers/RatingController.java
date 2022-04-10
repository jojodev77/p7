package com.nnk.springboot.controllers;


import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {

	@Autowired
	RatingService ratingService;

	@Autowired
	RatingRepository ratingRepository;
	
	private static final Logger log = LogManager.getLogger(RatingController.class);

	/**
	 * @Description method get for list rating
	 * @param model
	 * @return
	 */
	@RequestMapping("/rating/list")
	public String home(Model model) {
		// get and passation object beetween front and service
		model.addAttribute("rating", ratingRepository.findAll());
		return "rating/list";
	}

	/**
	 * @Description method get for page add rating, build and create forms
	 * @param rating
	 * @return
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	/***
	 * @Description method post for add rating,
	 * call method addRating in service and call list  from repository with new creation when create is success,
	 * if error, a log.warn is visibility in console and an error message is displayed on the user side
	 * @param rating
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// check the solution is compliant
		if (!result.hasErrors()) {
			// get and passation object beetween front and service
			model.addAttribute("rating", ratingService.addRating(rating));
			model.addAttribute("rating", ratingRepository.findAll());
			log.info("success for  create rating");
			return "rating/list";
		} else {
			log.warn("error for  create bidList");
			return "rating/add";
		}

	}

	/**
	 * @Description method get for page update rating,
this view builds a form by inserting the data of the object modified thanks to its id
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		// get and passation object beetween front and service
		model.addAttribute("rating", rating);
		return "rating/update";
	}
	

	/**
	 * @Descriptiion method post for update rating,retrieves the content of the form, checks the correct conformity of the expected values, calls the update method in the service, if the update is successful,
	 *  it returns to the updated list, otherwise an error is displayed on the consoles and users side
	 * @param id
	 * @param rating
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		// check the solution is compliant
		if (!result.hasErrors()) {
			// get and passation object beetween front and service
			model.addAttribute("rating", rating);
			ratingService.updateRating(id, rating);
			// check the solution is compliant
			if (ratingService.updateRating(id, rating).getStatusCodeValue() != 200) {
				  model.addAttribute("error", "Sorry, error as occured");
			}
			log.info("success for  update rating");
			return "redirect:/rating/list";
		}
		log.warn("error for  create rating");
		return "rating/update";
	}

	/**
	 * @Description Method get for delete rating,calls the delete method deleteById in the service, if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") long id, Model model) {
		ratingService.deleteRating(id);
		// check the solution is compliant
		if (ratingService.deleteRating(id).getStatusCodeValue() != 200) {
			// get and passation object beetween front and service
			  model.addAttribute("error", "Sorry, error as occured");
		}
		// get and passation object beetween front and service
		model.addAttribute("rating", ratingRepository.findAll());
		log.info("success for  delete rating");
		return "redirect:/rating/list";
	}
}
