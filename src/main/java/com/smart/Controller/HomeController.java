package com.smart.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.Dao.UserRepository;
import com.smart.entities.User;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;

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
	
	
	//handler for registering user
	
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement",defaultValue = "false") boolean agreement,Model model ) {
		
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		
		System.out.println(agreement);
		System.out.println(user);
		
		
		User result=this.userRepository.save(user);
		
		return "signup";
	}
	
	
	
//	@RequestMapping("/login")
//	public String login(Model model) {
//		model.addAttribute("title","login - Smart Contact Manager");
//		return "login";
//	}
}
