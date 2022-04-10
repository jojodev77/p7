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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {
   
	@Autowired
	RuleNameRepository ruleNameRepository;
	
	@Autowired
	RuleNameService ruleNameService;
	
	private static final Logger log = LogManager.getLogger(RuleNameController.class);

	/**
	 * @Description get list ruleName, , get list ruleName for repository. 
	 * @param model
	 * @return
	 */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	// get and passation object beetween front and service
    	model.addAttribute("ruleName", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    /**
     * @Description get page from add ruleName, build and create forms
     * @param bid
     * @return
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * @Description method post for create ruleName,
	 * call method addRating in service and call list  from repository with new creation when create is success,
	 * if error, a log.warn is visibility in console and an error message is displayed on the user side
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	// check the solution is compliant
    	if (!result.hasErrors()) {
    		// get and passation object beetween front and service
			model.addAttribute("ruleName", ruleNameService.addBidList(ruleName));
			model.addAttribute("ruleName", ruleNameRepository.findAll());
			log.info("success for  create ruleName");
			return "ruleName/list";
    	} else {
    	log.warn("error for  create ruleName");
        return "ruleName/add";
    	}
    }

    /**
     * @Decsription method get for page update ruleName, 
this view builds a form by inserting the data of the object modified thanks to its id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	RuleName ruleName = ruleNameRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    	// get and passation object beetween front and service
    	model.addAttribute("ruleName", ruleName);
    	return "ruleName/update";
    }

    /**
     * @Description method post for update ruleNamechecks the correct conformity of the expected values, calls the update method in the service, if the update is successful,
	 *  it returns to the updated list, otherwise an error is displayed on the consoles and users side
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	// check the solution is compliant
    	if (!result.hasErrors()) {
    		if (ruleNameService.updateBidList(id, ruleName).getStatusCodeValue() != 200) {
    			  model.addAttribute("error", "Sorry, error as occured");
			}
    		// get and passation object beetween front and service
			model.addAttribute("ruleName", ruleName);
			ruleNameService.updateBidList(id, ruleName);
			log.info("success for  update ruleName");
			return "redirect:/ruleName/list";
		}
    	 log.warn("error for  update ruleName");
    	 return "ruleName/update";
    }

    /***
     * @Description method get for delete ruleName,calls the delete method deleteById in the service, if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	ruleNameService.deleteBidList(id);
    	// check the solution is compliant
    	if (ruleNameService.deleteBidList(id).getStatusCodeValue() != 200) {
    		// get and passation object beetween front and service
			  model.addAttribute("error", "Sorry, error as occured");
		}
    	// get and passation object beetween front and service
		model.addAttribute("ruleName", ruleNameRepository.findAll());
		log.info("success for  delete ruleName");
        return "redirect:/ruleName/list";
    }
}
