package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

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


import javax.validation.Valid;

@Controller
public class TradeController {
   
	@Autowired
	TradeService tradeService;
	
	@Autowired
	TradeRepository tradeRepository;
	
	private static final Logger log = LogManager.getLogger(TradeController.class);

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
			log.info("success for  create trade");
			return "trade/list";
		} else {
		log.warn("error for  create trade");
        return "trade/add";
		}
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    	model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
			model.addAttribute("trade", trade);
			tradeService.updateTrade(id, trade);
			log.info("success for  update trade");
			return "redirect:/trade/list";
		}
    	 log.warn("error for  update trade");
    	 return "trade/update";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeService.deleteTrade(id);
		model.addAttribute("trade", tradeRepository.findAll());
		log.info("success for  delete trade");
        return "redirect:/trade/list";
    }
}
