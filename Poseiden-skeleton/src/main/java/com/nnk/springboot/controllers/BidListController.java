package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;


@Controller
public class BidListController {
   
	@Autowired
	BidListRepository bidListRepository;
	
	@Autowired
	BidListService bidListService;
	
	private static final Logger log = LogManager.getLogger(BidListController.class);

	/**
	 * @Description methog get for page list of bidList, get list bidlist for repository.
	 * @param model
	 * @return
	 */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
    	model.addAttribute("bidList", bidListRepository.findAll());
        return "bidList/list";
    }

    /**
     * @Description method get page create bidList, build and create forms
     * @param bid
     * @return
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /***
     * @Description method post for create bidList,
	 * call method addRating in service and call list  from repository with new creation when create is success,
	 * if error, a log.warn is visibility in console and an error message is displayed on the user side
     * @param bid
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
			model.addAttribute("bidList", bidListService.addBidList(bid));
			model.addAttribute("bidList", bidListRepository.findAll());
			log.info("success for  create bidList");
			return "bidList/list";
    }

    /**
     * @Description method get for page update bidList,
this view builds a form by inserting the data of the object modified thanks to its id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model ) {
    	BidList bidList = bidListRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    	model.addAttribute("bidList", bidList );
        return "bidList/update";
    }

    /**
     * @Description method post for update bidListchecks the correct conformity of the expected values, calls the update method in the service, if the update is successful,
	 *  it returns to the updated list, otherwise an error is displayed on the consoles and users side
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
			model.addAttribute("bidList", bidList );
			bidListService.updateBidList(id, bidList);
			// check the solution is compliant
			if (bidListService.updateBidList(id, bidList).getStatusCodeValue() != 200) {
				  model.addAttribute("error", "Sorry, error as occured");
			}
			log.info("success for  update bidList");
			return "redirect:/rating/list";
    }

    /**
     * @Description method get for delete bidList,calls the delete method deleteById in the service, if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	bidListService.deleteBidList(id);
    	// check the solution is compliant
    	if (bidListService.deleteBidList(id).getStatusCodeValue() != 200) {
			  model.addAttribute("error", "Sorry, error as occured");
		}
		model.addAttribute("bidList", bidListRepository.findAll());
		log.info("success for  delete bidList");
        return "redirect:/bidList/list";
    }
}
