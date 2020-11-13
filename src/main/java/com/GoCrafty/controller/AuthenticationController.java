package com.GoCrafty.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.GoCrafty.service.AuthenticationService;

@Controller
@RequestMapping("home/userAuthentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	@PostMapping("/login")
	public String login(@ModelAttribute("role")String role,@ModelAttribute("theUser") Object theUser,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		String id = authenticationService.userLogin(theUser,role);
		
		if(id == null)
		{
			theModel.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/showUserLogin";
		}
		else {
			studentSession.put("id", id);
			return "redirect:/home/student/viewProfile";
			}
	}
}
