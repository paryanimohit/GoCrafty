package com.GoCrafty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;

@Controller
@RequestMapping("/home")
public class HomePageController {
	
	@RequestMapping("/showUserLogin")
	public String showUserLogin(@RequestParam("role") String role,Model theModel)
	{
		theModel.addAttribute("role",role);
		if (role.equals("student"))
		{
			Student theStudent = new Student();
			theModel.addAttribute("theUser",theStudent);
			
		}
		else if (role.equals("instructor"))
		{
			Instructor theInstructor = new Instructor();
			theModel.addAttribute("theUser",theInstructor);
		}
		return "user-login-form";
	}
	
	@RequestMapping("/createUser")
	public String createUser(@RequestParam("role") String role, Model theModel)
	{
		if(role.equals("instructor")) {
			return "instructor-signp";
		}
		
		else if (role.equals("student")) {
			return "student-signup";	
		}
		
		else return "error-page";
	}
}
