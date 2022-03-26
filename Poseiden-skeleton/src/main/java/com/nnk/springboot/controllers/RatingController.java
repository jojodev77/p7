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

import com.nnk.springboot.domain.CurvePoint;
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

	@RequestMapping("/rating/list")
	public String home(Model model) {
		model.addAttribute("rating", ratingRepository.findAll());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("rating", ratingService.addRating(rating));
			model.addAttribute("rating", ratingRepository.findAll());
			log.info("success for  create rating");
			return "rating/list";
		} else {
			log.warn("error for  create bidList");
			return "rating/add";
		}

	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("rating", rating);
			ratingService.updateRating(id, rating);
			log.info("success for  update rating");
			return "redirect:/rating/list";
		}
		log.warn("error for  create bidList");
		return "rating/update";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") long id, Model model) {
		ratingService.deleteRating(id);
		model.addAttribute("rating", ratingRepository.findAll());
		log.info("success for  delete bidList");
		return "redirect:/rating/list";
	}
}
