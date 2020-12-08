package com.GoCrafty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.service.CourseService;
import com.GoCrafty.service.Encryption;
import com.GoCrafty.service.InstructorService;


@Controller
@RequestMapping("home/instructor")
public class InstructorController {
	@Autowired
	private InstructorService instructorService;
	private CourseService courseService;
	
	@GetMapping("/viewProfile")
	String viewProfile(Model m,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession)
	{
		try {
			int id=Integer.parseInt(instructorSession.get("id"));
			Instructor instructor= instructorService.getInstructor(id);
			String image = instructorService.getImage(id);
			
			instructorService.setCurrentLogin(id);
			
			List<Course> course = instructorService.getCourseByInstructorId(id);
			m.addAttribute("course",course);
			
			instructorSession.put("firstName",instructor.getFirstName());
			instructorSession.put("lastName",instructor.getLastName());
			instructorSession.put("email",instructor.getEmail());
			
			m.addAttribute("img",image);
			m.addAttribute("instructor",instructor);
			sendToHeader(m);
			return "instructor-profile";
			}
		catch (NullPointerException e)
			{
			e.printStackTrace();
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
			return "instructor-login-form?role=instructor";
		}
		else
		{
			theModel.addAttribute("message", message);
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/getInstructor";
			}
			else
				return "redirect:/home/userLogin?role=instructor";
		}
	}
	
	@GetMapping("/showEditProfile")
	public String showEditProfile(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		String localId = instructorSession.get("id");
		if(!(localId.contentEquals("temp") || localId.equals(null))) {
			int id = Integer.parseInt(localId);
			Instructor instructor = instructorService.getInstructor(id);
			Encryption encr= new Encryption();
			String encryptedPass= instructor.getPassword();
			String decryptedPass=encr.decrypt(encryptedPass);
			instructor.setPassword(decryptedPass);
			
			theModel.addAttribute("instructor", instructor);
			return "edit-profile-instructor";
		}
		else { 
			theModel.addAttribute("Message", "Your Session was Expired! Please login again to continue");
			return "redirect:/home/authentication/userLogin?role=instructor";
		
		}
	}
	
	@PostMapping("/editProfile")
	public String editProfile(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,
									@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("password") String password, 
									Model model) {
		
		String localId = instructorSession.get("id");
		if(!(localId.contentEquals("temp") || localId.equals(null))) {
			
			ArrayList<String> updatedInstructor = new ArrayList<>();
			updatedInstructor.add(firstName);
			updatedInstructor.add(lastName);
			updatedInstructor.add(password);
			
			Instructor instructor = instructorService.editProfile(updatedInstructor,localId);
			if(updatedInstructor == null || localId == null)
			{
				return "redirect:/home/instructor/viewProfile";
			}
			else {
				model.addAttribute("instructor",instructor);
				instructorSession.put("firstName",instructor.getFirstName());
				instructorSession.put("lastName",instructor.getLastName());
				instructorSession.put("email",instructor.getEmail());
				return "redirect:/home/instructor/viewProfile";
				}		
		}
		else {
			model.addAttribute("Message", "Your Session was Expired! Please login again to continue");
			return "redirect:/home/authentication/userLogin?role=instructor";
		}
	}
	
	@GetMapping("/showAddCourse")
	public String showAddCourse(Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			theModel.addAttribute("userId",userId);
			Course course = new Course();
			theModel.addAttribute("course",course);
			return "add-course";
		}
	}

}