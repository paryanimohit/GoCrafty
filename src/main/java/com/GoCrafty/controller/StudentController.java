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
import com.GoCrafty.service.StudentService;


@Controller
@RequestMapping("home/student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login")
	public String userLogin(@ModelAttribute("user") Student student, Model m,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession) 
		{
		

		String email = student.getEmail();
		System.out.println("Email: "+ email);
		String password = student.getPassword();
		System.out.println("Email: "+ password);
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
			System.out.println("wrong email/pass");
			return "redirect:/home/showUserLogin";
		}
		else {
			System.out.println("correct email/pass");
			studentSession.put("id", id);
//			userSession.put("firstName",userList.getFirstName());
//			userSession.put("lastName",userList.getLastName());
//			userSession.put("email",userList.getEmail());
			
			

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

}
