package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.config.ExcludeFromJacocoGeneratedReport;
import com.nnk.springboot.repositories.UserRepository;


@Controller
@RequestMapping("app")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	/**
	 * @Description  * @Description build and display the authentication page, display the error message in case of bad authentication
	 * @return
	 */
	@ExcludeFromJacocoGeneratedReport
    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }


	/**
	 * @Description displays a custom 403 error page
	 * @return
	 */
	@ExcludeFromJacocoGeneratedReport
    @GetMapping("error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
