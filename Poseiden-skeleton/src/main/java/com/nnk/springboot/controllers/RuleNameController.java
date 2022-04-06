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
	 * @Description get list ruleName
	 * @param model
	 * @return
	 */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	model.addAttribute("ruleName", ruleNameRepository.findAll());
        return "ruleName/list";
    }

    /**
     * @Description get page from add ruleName
     * @param bid
     * @return
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * @Description method post for create ruleName
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
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
     * @Decsription method get for page update ruleName
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	RuleName ruleName = ruleNameRepository.findById((long) id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    	model.addAttribute("ruleName", ruleName);
    	return "ruleName/update";
    }

    /**
     * @Description method post for update ruleName
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	if (!result.hasErrors()) {
    		if (ruleNameService.updateBidList(id, ruleName).getStatusCodeValue() != 200) {
    			  model.addAttribute("error", "Sorry, error as occured");
			}
			model.addAttribute("ruleName", ruleName);
			ruleNameService.updateBidList(id, ruleName);
			log.info("success for  update ruleName");
			return "redirect:/ruleName/list";
		}
    	 log.warn("error for  update ruleName");
    	 return "ruleName/update";
    }

    /***
     * @Description method get for delete ruleName
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	ruleNameService.deleteBidList(id);
    	if (ruleNameService.deleteBidList(id).getStatusCodeValue() != 200) {
			  model.addAttribute("error", "Sorry, error as occured");
		}
		model.addAttribute("ruleName", ruleNameRepository.findAll());
		log.info("success for  delete ruleName");
        return "redirect:/ruleName/list";
    }
}
