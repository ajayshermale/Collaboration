package com.niit.joinme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinMeController {

	@GetMapping("/")
	public String hello(Model model) {

		model.addAttribute("name", "Ajay");

		return "welcome";
	}
}
