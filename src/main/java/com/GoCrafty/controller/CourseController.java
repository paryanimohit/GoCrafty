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
import com.GoCrafty.service.CourseService;

@Controller
@RequestMapping("home/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/showCategories")
	public String showCategories(Model theModel)
	{
		
		List<String> categories=courseService.showCategories();
		theModel.addAttribute("category",categories);
		return "show-category";
	}

	@PostMapping("/showCoursesByCategory")
	public String showCoursesByCategory(Model theModel,@RequestParam("category") String category)
	{
		List<Course> course=courseService.showCoursesByCategory(category);
		 HashMap<String, String> hmap=courseService.getInstructorNames(course);
		 theModel.addAttribute("instructor", hmap);
		theModel.addAttribute("course", course);
		
		return "courses-by-category";
	}

	@RequestMapping("/viewCourse")
	public String viewCourse(@RequestParam("id")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		Course theCourse=courseService.getCourseById(courseId);
		List<Course> course =new ArrayList<Course>();
		course.add(theCourse);
		HashMap<String, String> instructorName=courseService.getInstructorNames(course);
		
		theModel.addAttribute("theCourse",theCourse);
		theModel.addAttribute("instructorName",instructorName);
		
		//if(studentSession.get(""))
		return "view-course";
	}
	
	@RequestMapping("/enrollCourse")
	public String enrollCourse(@RequestParam("id")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		String userId=studentSession.get("id");
		studentSession.put("courseId", courseId);
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else
		{
			String msg= courseService.enroll(userId,courseId);
			if(!(msg.equals("Enrolled")))
					{
						return "error-page";
					}
		}
		
		return "redirect:/home/student/viewProfile";

	}
	
	@RequestMapping("/addCourse")
	public String addCourse(@ModelAttribute(name="course") Course course,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.addCourse(course);
			int newId = myCourse.getId();
			Course newCourse = courseService.getCourseById(String.valueOf(newId));
			theModel.addAttribute("course",newCourse);
			return "redirect:/home/course/showCourseHome";
		}
	}
}