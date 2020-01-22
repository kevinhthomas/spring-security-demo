package com.kthomas.springsecurity.demo.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/login")
	public String getLogin() {
		
		logger.info("INSIDE GET LOGIN");

		return "login";
	}
	
	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "access-denied";
	}
	
}
