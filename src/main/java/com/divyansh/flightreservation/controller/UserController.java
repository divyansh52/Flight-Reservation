package com.divyansh.flightreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.divyansh.flightreservation.entity.User;
import com.divyansh.flightreservation.repo.UserRepo;
import com.divyansh.flightreservation.service.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		
		LOGGER.info("Inside showRegistrationPage()");
		
		return "login/registerUser";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user) {
		
		LOGGER.info("Inside registerUser(): " + user);
		
		// encrypting the password before saving in db
		user.setPassword(encoder.encode(user.getPassword()));
		
		userRepo.save(user);
		return "login/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		
		System.out.println("Email passed " + email);
		
		LOGGER.info("Inside login() and the email is: " + email);
		
//		LOGGER.error("ERROR");
//		LOGGER.warn("WARN");
//		LOGGER.info("INFO");
//		LOGGER.debug("DEBUG");
//		LOGGER.trace("TRACE");
		
		boolean loginResponse = securityService.login(email, password);
		
		// Code used before implementing security
		
//		if(user!=null && user.getPassword().equals(password)) {
//			return "findFlights";
//		}else {
//			modelMap.addAttribute("msg", "Incorrect username or password. Please try again!!!");
//		}
		
		// Code used after implementing security concepts
		if(loginResponse) {
			return "findFlights";
		}else {
			modelMap.addAttribute("msg", "Incorrect username or password. Please try again!!!");
		}
		
		return "login/login";
	}

}
