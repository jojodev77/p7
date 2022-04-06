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
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurveController {

	@Autowired
	CurvePointService curvePointService;

	@Autowired
	CurvePointRepository curvePointRepository;

	private static final Logger log = LogManager.getLogger(CurveController.class);

	/**
	 * @Description method get for list curvePoint
	 * @param model
	 * @return
	 */
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("curvePoint", curvePointRepository.findAll());
		return "curvePoint/list";
	}

	/**
	 * @Description method for get page add curvePoint
	 * @param bid
	 * @return
	 */
	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	/**
	 * @Description method post for add curvePoint
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("curvePoint", curvePointService.addCurvePoint(curvePoint));
			model.addAttribute("curvePoint", curvePointRepository.findAll());
			log.info("success for  create curvePoint");
			return "curvePoint/list";
		} else {
			log.warn("error for  create curvePoint");
			return "curvePoint/add";
		}
	}

	/**
	 * @Description method for get page updateCurvePoint
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	/**
	 * @Description method post for update curvePoint
	 * @param id
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		if (!result.hasErrors()) {
			model.addAttribute("curvePoint", curvePoint);
			curvePointService.updateCurvePoint(id, curvePoint);
			if (curvePointService.updateCurvePoint(id, curvePoint).getStatusCodeValue() != 200) {
				  model.addAttribute("error", "Sorry, error as occured");
			}
			log.info("success for  update curvePoint");
			return "redirect:/curvePoint/list";
		}
		log.info("error for  update curvePoint");
		return "curvePoint/update";
	}

	/**
	 * @Description method get for delete CurvePoint
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curvePointService.deleteCurvePoint(id);
		if (curvePointService.deleteCurvePoint(id).getStatusCodeValue() != 200) {
			  model.addAttribute("error", "Sorry, error as occured");
		}
		model.addAttribute("curvePoint", curvePointRepository.findAll());
		log.info("success for  delete curvePoint");
		return "redirect:/curvePoint/list";
	}
}
