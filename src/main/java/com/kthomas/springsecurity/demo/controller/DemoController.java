package com.kthomas.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/manager")
	public String getManager() {
		return "manager";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin";
	}
}
