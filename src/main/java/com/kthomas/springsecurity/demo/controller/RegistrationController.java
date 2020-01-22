package com.kthomas.springsecurity.demo.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kthomas.springsecurity.demo.client.ClientUser;
import com.kthomas.springsecurity.demo.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	
	@GetMapping("/register") 
	public String getRegister(Model model) {
		
		logger.info("INSIDE GET REGISTER");
		
		model.addAttribute("clientUser", new ClientUser());
		return "get-register";
	}
	
	@PostMapping("/register")
	public String postRegister(
			@Valid @ModelAttribute("clientUser") ClientUser cUser,
			BindingResult bindingResult,
			Model model
			) {
		
		if (bindingResult.hasErrors()) {
			return "get-register";
		}
		
		if (userService.findByUserName(cUser.getUserName()) != null) {
			model.addAttribute("clientUser", cUser);
			model.addAttribute("registrationError", "User name already exists.");

		}
		
		userService.save(cUser);
		
		return "/register-complete";
	}
}
