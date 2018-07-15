 package com.bharath.flightreservation.contollers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.User;
import com.bharath.flightreservation.repos.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class); 

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "/login/registerUser";
	}
	
	
	@RequestMapping("/showLogin")
	public String showLogIn() {
		return "/login/login";
	}
	
	
	@RequestMapping(value="/registerUser" , method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		userRepository.save(user);
		
		LOGGER.info("CN : UserController , inside  register ");
	
		return "login/login";
	}
	/*
	 * this method takes input as email and password 
	 * and send the response using modelmap 
	 */
	
	
	@RequestMapping(value="/login" , method= RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelmap) {
		

		LOGGER.info("CN : UserController , inside  login ");
		
		User user = userRepository.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}else {
			modelmap.addAttribute("msg" , "invalid username or password. please try again");
		}
		
		return "login/login";
	}
}
