package com.GoCrafty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.GoCrafty.entity.Course;
import com.GoCrafty.service.CourseService;
import com.GoCrafty.service.StudentService;

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

	@PostMapping("/showCourse")
	public String showCourse(Model theModel,@RequestParam("category") String category)
	{
		List<Course> course=courseService.showCourse(category);
		
		theModel.addAttribute("course", course);
		
		return "course";
	}

}
