package com.nnk.springboot.controllers;

import com.nnk.springboot.Dto.SigninDto;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    /**
     * @Description build and display the authentication page, display the error message in case of bad authentication
     * @param signin
     * @param binding
     * @param model
     * @return
     */
    @GetMapping("/user/login")
    public String login( @ModelAttribute SigninDto signin, BindingResult binding, Model model) {
    	// check the solution is compliant
    	if (binding.hasErrors()) {
    		new ResponseEntity<>("login is incorrect", HttpStatus.NOT_ACCEPTABLE);
		}
    	// get and passation object beetween front and service
    	model.addAttribute("signin", signin);
    	return "user/login";
    }
    
    /**
     * @Description retrieve the content of the form, check the expected consistency, call the method in the service, if successful, 
     * return to the user menu, otherwise redisplay the login page and display an error message for the user
     * @param signin
     * @param binding
     * @param model
     * @return
     */
    @PostMapping("/user/login")
    public String signin( @Valid SigninDto signin, BindingResult binding, Model model) {
    	model.addAttribute("signin", signin);
    	// get and passation object beetween front and service
    	model.addAttribute("userDetails", userService.autoLogin(signin));
    	// check the solution is compliant
    	if (userService.autoLogin(signin).getUsername() != null) {
    		log.info("success for  signin");
    		 return "user/userH";
		} else {
			log.warn("error for  signin");
			return "user/login";
		}
    	
    }
    
    /**
     * @Description call and build the user menu page
     * @param binding
     * @param model
     * @return
     */
    @GetMapping("/user/userH")
    public String userHome( BindingResult binding, Model model) {
    	return "user/userH";
    }
    
    

    /**
     * @Description call and build the user list page by calling the findAll method of the repository
     * @param model
     * @return
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    /**
     * @Description calls and constructs the add user page by constructing a form based on the user object
     * @param bid
     * @return
     */
    @GetMapping("/user/add") 
    public String addUser(User bid) {
        return "user/add";
    }

    /**
     * @Description retrieves the content of the form, checks the expected conformity, calls the method in the service, if successful, returns to the user list,
     *  otherwise redisplays the user creation page and displays an error message for the user
     * @param user
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
    	// check the solution is compliant
        if (!result.hasErrors()) {
        	// check the solution is compliant
        	if (userService.isValid(user.getPassword()) == false) {
        		// get and passation object beetween front and service
        		  model.addAttribute("error", "Your password is not reglementary");
        		return "user/add";
			}
          userService.createUser(user);
       // get and passation object beetween front and service
            model.addAttribute("users", userRepository.findAll());
            log.info("success for  create user");
            return "redirect:/user/list";
        } else {
        	log.warn("error for  create user");
        	// get and passation object beetween front and service
        	  model.addAttribute("error", new ResponseEntity<>("user is not create", HttpStatus.NOT_ACCEPTABLE));
        	 return "user/add";
        }
       
    }

    /**
     * @Description displays and builds a form with fields already filled in with the user to be modified,
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	// check is user exist in db
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
     // get and passation object beetween front and service
        model.addAttribute("user", user);
        log.info("success for  update user");
        return "user/update";
    }

    /**
     * @Description retrieves the value of the form input fields, calls the user update service, if successful, the user is sent to the user list page,
     *  otherwise the user remains on the same page and a message d error is displayed to the user
     * @param id
     * @param user
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
        	log.error("error for  update user");
            return "user/update";
        }

        // method to encrypt the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
     // get and passation object beetween front and service
        model.addAttribute("users", userRepository.findAll());
        log.info("success for  update user");
        return "redirect:/user/list";
    }

    /**
     * @Description if the deletion is successful, it returns to the updated list, 
     * otherwise an error is displayed on the console and user side
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
    	// check is user is exist
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
     // get and passation object beetween front and service
        model.addAttribute("users", userRepository.findAll());
        log.info("success for  delete user");
        return "redirect:/user/list";
    }
}
