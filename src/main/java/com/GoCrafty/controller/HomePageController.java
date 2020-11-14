package com.GoCrafty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;

@Controller
@RequestMapping("/home")
public class HomePageController {
	
	@RequestMapping("/studentLogin")
	public String UserLogin(Model theModel,@ModelAttribute("message")String message)
	{
		theModel.addAttribute("message",message);

		
			Student theStudent = new Student();
			theModel.addAttribute("theUser",theStudent);
			return "student-login-form";

	}
	
	@RequestMapping("/instructorLogin")
	public String instructorLogin(Model theModel,@ModelAttribute("message")String message)
	{
		theModel.addAttribute("message",message);

		Instructor theInstructor = new Instructor();
		theModel.addAttribute("theUser",theInstructor);
		return "instructor-login-form";
		
	}
	
	@RequestMapping("/createUser")
	public String createUser(@RequestParam("role") String role, Model theModel)
	{
		if(role.equals("instructor")) {
			Instructor instructor = new Instructor();
			theModel.addAttribute("instructor",instructor);
			return "instructor-signup";
		}
		
		else if (role.equals("student")) {
			Student student = new Student();
			theModel.addAttribute("student",student);
			return "student-signup";	
		}
		
		else return "error-page";
	}
}
