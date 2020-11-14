package com.GoCrafty.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.entity.User;
import com.GoCrafty.service.AuthenticationService;

@Controller
@RequestMapping("home/userAuthentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	@PostMapping("/login")
	public String login(@RequestParam("role")String role,@ModelAttribute("theUser") User theUser,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		Student su=new Student();
				su=(Student) theUser;
		System.out.println("name"+su.getFirstName());
		
//		Student student = null;
//		Instructor instructor =null;
//		
//		if(role.equals("student"))
//		{
//			student=(Student) theUser;	
//		}
//		
//		else if(role.equals("instructor"))
//		{
//			instructor=(Instructor) theUser;
//		}
//		
//		String email = theUser;
//		String password = student.getPassword();
//		if (email.isEmpty() || password.isEmpty())
//		{
//			m.addAttribute("message","Incorrect Email or Password");
//			return "redirect:/home/showUserLogin";
//		}
//		
//	 
		System.out.println("contoller   role:|"+role+"|");
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
