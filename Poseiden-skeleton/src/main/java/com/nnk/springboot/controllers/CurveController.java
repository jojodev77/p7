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
	 * @Description method get for list curvePoint, get list curvepoint for repository.
	 * @param model
	 * @return
	 */
	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("curvePoint", curvePointRepository.findAll());
		return "curvePoint/list";
	}

	/**
	 * @Description method for get page add curvePoint, build and create forms
	 * @param bid
	 * @return
	 */
	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	/**
	 * @Description method post for add curvePoint,
	 * call method addRating in service and call list  from repository with new creation when create is success,
	 * if error, a log.warn is visibility in console and an error message is displayed on the user side
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// check the solution is compliant
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
	 * @Description method for get page updateCurvePoint,
this view builds a form by inserting the data of the object modified thanks to its id
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
	 * @Description method post for update curvePointchecks the correct conformity of the expected values, calls the update method in the service, if the update is successful,
	 *  it returns to the updated list, otherwise an error is displayed on the consoles and users side
	 * @param id
	 * @param curvePoint
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		// check the solution is compliant
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
	 * @Description method get for delete CurvePoint,calls the delete method deleteById in the service, if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
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
