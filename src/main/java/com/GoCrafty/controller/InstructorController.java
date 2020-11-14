package com.GoCrafty.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.service.InstructorService;


@Controller
@RequestMapping("home/instructor")
public class InstructorController {
	@Autowired
	private InstructorService InstructorService;
	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute("theUser") Instructor instructor, Model m,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) 
		{
		
		String email = instructor.getEmail();
		String password = instructor.getPassword();
		
		//checking for null input values
		
		if (email.isEmpty() || password.isEmpty())
		{
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/instructorLogin";
		}
	 
		String id = InstructorService.instructorLogin(email,password);
		
		if(id == null)
		{	
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/instructorLogin";
		}
		else {
			instructorSession.put("id", id);
			return "redirect:/home/instructor/viewProfile";
			}
		}
	
	@GetMapping("/viewProfile")
	String viewProfile(Model m,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession)
	{
		int id=Integer.parseInt(instructorSession.get("id"));
		try {
			Instructor instructorList= InstructorService.getInstructor(id);
			String image = InstructorService.getImage(id);
			instructorSession.put("firstName",instructorList.getFirstName());
			instructorSession.put("lastName",instructorList.getLastName());
			instructorSession.put("email",instructorList.getEmail());
			
			m.addAttribute("img",image);
			m.addAttribute("instructorlist",instructorList);
			sendToHeader(m);
			return "instructor-profile";
			}
		catch (NullPointerException e)
			{
			return "errorPage";
			}
	}
	
	String sendToHeader(Model m) 
	{
		return "user-header";
	}
}