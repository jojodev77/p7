package com.nnk.springboot.controllers;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    UserService userService;
    
    private static final Logger log = LogManager.getLogger(UserController.class);
    
    @GetMapping("/user/login")
    public String login( @ModelAttribute SigninDto signin, BindingResult binding, Model model) {
    	if (binding.hasErrors()) {
    		ResponseEntity.status(400).body("");
		}
    	model.addAttribute("signin", signin);
    	return "user/login";
    }
    
    @PostMapping("/user/login")
    public String signin( @Valid SigninDto signin, BindingResult binding, Model model) {
    	model.addAttribute("signin", signin);
    	model.addAttribute("userDetails", userService.autoLogin(signin));
    	if (userService.autoLogin(signin).getUsername() != null) {
    		log.info("success for  signin");
    		 return "user/userH";
		} else {
			log.warn("error for  signin");
			return "user/login";
		}
    	
    }
    
    @GetMapping("/user/userH")
    public String userHome( BindingResult binding, Model model) {
    	return "user/userH";
    }
    
    

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            log.info("success for  create user");
            return "redirect:/user/list";
        } else {
        	log.warn("error for  create user");
        	ResponseEntity.status(500).body("Error with create user");
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        log.info("success for  update user");
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
        	log.error("error for  update user");
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        log.info("success for  update user");
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        log.info("success for  delete user");
        return "redirect:/user/list";
    }
}
