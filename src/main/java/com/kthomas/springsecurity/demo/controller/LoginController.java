package com.kthomas.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String getAccessDenied() {
		return "access-denied";
	}
	
}
