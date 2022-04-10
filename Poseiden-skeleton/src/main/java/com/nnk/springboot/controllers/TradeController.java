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

	/**
	 * @Description method for get list trade, get list trade for repository.
	 * @param model
	 * @return
	 */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
    	// get and passation object beetween front and service
    	model.addAttribute("trade", tradeRepository.findAll());
        return "trade/list";
    }

    /**
     * @Description method get for page add trade, build and create forms
     * @param bid
     * @return
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * @Description method post for add trade,
	 * call method addRating in service and call list  from repository with new creation when create is success,
	 * if error, a log.warn is visibility in console and an error message is displayed on the user side
     * @param trade
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	// get and passation object beetween front and service
			model.addAttribute("trade", tradeService.addRrade(trade));
			model.addAttribute("trade", tradeRepository.findAll());
			log.info("success for  create trade");
			return "trade/list";
		
    }

    /**
     * @Description method get for page update trade,
this view builds a form by inserting the data of the object modified thanks to its id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    	// get and passation object beetween front and service
    	model.addAttribute("trade", trade);
        return "trade/update";
    }
    

    /**
     * @Description method post for update tradechecks the correct conformity of the expected values, calls the update method in the service, if the update is successful,
	 *  it returns to the updated list, otherwise an error is displayed on the consoles and users side
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
			model.addAttribute("trade", trade);
			tradeService.updateTrade(id, trade);
			// check the solution is compliant
			if (tradeService.updateTrade(id, trade).getStatusCodeValue() != 200) {
				  model.addAttribute("error", "Sorry, error as occured");
			}
			log.info("success for  update trade");
			return "redirect:/trade/list";
    }

    /**
     * @Description method get for delete trade,calls the delete method deleteById in the service, if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeService.deleteTrade(id);
    	// check the solution is compliant
    	if (tradeService.deleteTrade(id).getStatusCodeValue() != 200) {
			  model.addAttribute("error", "Sorry, error as occured");
		}
		model.addAttribute("trade", tradeRepository.findAll());
		log.info("success for  delete trade");
        return "redirect:/trade/list";
    }
}
