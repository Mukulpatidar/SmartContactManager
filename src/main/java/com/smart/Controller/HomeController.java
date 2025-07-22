package com.smart.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.entities.User;


@Controller
public class HomeController {
	
	

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title","Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title","Register - Smart Contact Manager");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	
//	@RequestMapping("/login")
//	public String login(Model model) {
//		model.addAttribute("title","login - Smart Contact Manager");
//		return "login";
//	}
}
