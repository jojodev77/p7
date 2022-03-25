package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

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
public class TradeController {
   
	@Autowired
	TradeService tradeService;
	
	@Autowired
	TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	model.addAttribute("trade", tradeRepository.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			model.addAttribute("trade", tradeService.addRrade(trade));
			model.addAttribute("trade", tradeRepository.findAll());
			return "trade/list";
		} else {
        return "trade/add";
		}
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	model.addAttribute("trade", tradeService.updateTrade(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			model.addAttribute("trade", tradeService.updateTrade(id));
			return "redirect:/trade/list";
		}
    	 return "trade/update";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeService.deleteTrade(id);
		model.addAttribute("trade", tradeRepository.findAll());
        return "redirect:/trade/list";
    }
}
