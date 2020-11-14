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
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.InstructorService;


@Controller
@RequestMapping("home/instructor")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute("theUser") Instructor instructor, Model m,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) 
		{
		
		String email = instructor.getEmail();
		String password = instructor.getPassword();
		
		//checking for null input values
		
		if (email.isEmpty() || password.isEmpty())
		{
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/showUserLogin";
		}
	 
		String id = instructorService.instructorLogin(email,password);
		
		if(id == null)
		{	
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/showUserLogin";
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
			Instructor instructorList= instructorService.getInstructor(id);
			String image = instructorService.getImage(id);
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
	
	@PostMapping("/createAccount")
	public String createAcount(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,@ModelAttribute(name="instructor") Instructor theInstructor,Model theModel) {
		
		String message= instructorService.createAccount(theInstructor);
		if (message.equals("Cannot create user! Please try again"))
		{
			theModel.addAttribute("message", message);
			return "user-form";
		}
		else
		{
			theModel.addAttribute("message", message);
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/getUsers";
			}
			else
				return "redirect:/home/showUserLogin?role=student";
		}
	}
}