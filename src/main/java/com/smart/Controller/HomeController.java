package com.smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.Dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	// handler for registering user
	@PostMapping("/do_register")
	public String registerUser(
			@Valid @ModelAttribute("user") User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
			Model model,
			BindingResult result,
			HttpSession session) {
		
		try {
			if (!agreement) {
				System.out.println("You have not agreed to the terms and conditions");
				throw new Exception("You have not agreed to the terms and conditions");
			}
			
			if (result.hasErrors()) {
				System.out.println("ERROR: " + result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			System.out.println(agreement);
			System.out.println(user);
			
			// ✅ FIXED: renamed to savedUser to avoid duplicate variable
			User savedUser = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Registered!", "alert-success"));
			return "signup";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something went wrong!! " + e.getMessage(), "alert-danger"));
			return "signup";
		}
	}
}
