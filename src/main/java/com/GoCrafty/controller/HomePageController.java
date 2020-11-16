package com.GoCrafty.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomePageController {
	
	@RequestMapping("/userLogin")
	public String UserLogin(Model theModel,@ModelAttribute("message")String message,@RequestParam("role") String role)
	{
			
		if(role.equals("instructor")) {
			theModel.addAttribute("message",message);
			theModel.addAttribute("role",role);
			Instructor instructor = new Instructor();
			theModel.addAttribute("instructor",instructor);
			return "instructor-login-form";
		}
		
		else if (role.equals("student")) {
			theModel.addAttribute("role",role);
			theModel.addAttribute("message",message);
			Student student = new Student();
			theModel.addAttribute("student",student);
			return "student-login-form";	
		}
		
		else return "error-page";
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

	@RequestMapping("/logOut")
	public String logOut(@SessionAttribute(name="tempSession") HashMap<String,String> userSession, HttpServletRequest request) {
	try {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
		}
	catch (Exception e) {
		
		return "redirect:/";
		}
	}
}
