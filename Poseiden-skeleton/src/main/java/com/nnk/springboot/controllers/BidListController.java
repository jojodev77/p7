package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
    	model.addAttribute("bidList", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			model.addAttribute("bidList", bidListService.addBidList(bid));
			model.addAttribute("bidList", bidListRepository.findAll());
			return "bidList/list";
    	} else {
    		 return "bidList/add";
    	}
       
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model,BidList bidList ) {
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (!result.hasErrors()) {
			model.addAttribute("bidList", bidList );
			bidListService.updateBidList(id, bidList);
			return "redirect:/rating/list";
		}
        return "bidList/update";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	bidListService.deleteBidList(id);
		model.addAttribute("bidList", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
