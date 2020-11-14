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

import com.GoCrafty.entity.Student;
//import com.GoCrafty.service.AuthenticationService;
import com.GoCrafty.service.StudentService;


@Controller
@RequestMapping("home/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute("theUser") Student student, Model m,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession,@ModelAttribute("role") String role) 
		{
		

		String email = student.getEmail();
		String password = student.getPassword();
	//checking for null input values
		if (email.isEmpty() || password.isEmpty())
		{
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/showUserLogin";
		}
	 
		String id = studentService.studentLogin(email,password);
		
		if(id == null)
		{
			
			m.addAttribute("message","Incorrect Email or Password");
			return "redirect:/home/studentLogin";
		}
		else {
			studentSession.put("id", id);
			return "redirect:/home/student/viewProfile";
			}
			

			
		}
	
	@GetMapping("/viewProfile")
	String viewProfile(Model m,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		int id=Integer.parseInt(studentSession.get("id"));
		try {
			Student studentList= studentService.getStudent(id);
			String image = studentService.getImage(id);
			studentSession.put("firstName",studentList.getFirstName());
			studentSession.put("lastName",studentList.getLastName());
			studentSession.put("email",studentList.getEmail());
			
			m.addAttribute("img",image);
			m.addAttribute("studentlist",studentList);
			sendToHeader(m);
			System.out.println("login success");
			return "student-profile";
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
	
	
	
	@PostMapping("/studentSignup")
	public String studentSignup(@SessionAttribute(name="tempSession") HashMap<String,String> studentSession,@ModelAttribute(name="theStudent") Student theStudent,Model m) {
		
		String message=studentService.studentSignup(theStudent);
		if (message.equals("Cannot create user! Please try again"))
		{
			m.addAttribute("message", message);
			return "user-form";
		}
		else
		{
			m.addAttribute("message", message);
			if(studentSession.containsKey("status")){
				return "redirect:/home/admin/getUsers";
			}
			else
				return "redirect:/home/showUserLogin?role=student";
		}
	}
}
