package com.nnk.springboot.controllers;

import com.nnk.springboot.Dto.RatingDto;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class RatingController {

	@Autowired
	RatingService ratingService;

	@Autowired
	RatingRepository ratingRepository;

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
			return "rating/list";
		} else {
			return "rating/add";
		}

	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("rating", ratingService.updateRating(id));
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("rating", ratingService.updateRating(id));
			return "redirect:/rating/list";
		}
		return "rating/update";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") long id, Model model) {
		ratingService.deleteRating(id);
		model.addAttribute("rating", ratingRepository.findAll());
		return "redirect:/rating/list";
	}
}
