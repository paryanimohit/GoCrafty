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
import com.GoCrafty.service.Utilities;

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

	@RequestMapping("/viewCourseDescription")
	public String viewCourseDescription(@RequestParam("id")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession)
	{
		Course theCourse=courseService.getCourseById(courseId);
		List<Course> course =new ArrayList<Course>();
		course.add(theCourse);
		HashMap<String, String> instructorName=courseService.getInstructorNames(course);
		
		theModel.addAttribute("theCourse",theCourse);
		theModel.addAttribute("instructorName",instructorName);
		
		//if(studentSession.get(""))
		return "view-course-description";
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
		System.out.println(userId);
		if (userId==null || userId.equals("temp"))
		{
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/showAdminLogin";
			}
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.addCourse(course);
			int newId = myCourse.getId();
			instructorSession.put("newCourseId", String.valueOf(newId));
			if(instructorSession.containsKey("status")){
				return "redirect:/home/admin/getCourse";
			}
			return "redirect:/home/course/showCourseHomeToInstructor";
		}
	}
	
	@RequestMapping("/showCourseHomeToInstructor")
	public String showCourseHomeToInstructor(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			String newCourseId = instructorSession.get("newCourseId");
			Course newCourse = courseService.getCourseById(String.valueOf(newCourseId));
			theModel.addAttribute("course",newCourse);
			return "course-home";
		}
	}
	

	@RequestMapping("/generateCertificate")
	public String generateCert(@RequestParam("courseId")String courseId, @SessionAttribute(name="tempSession") HashMap<String,String> studentSession, Model theModel) {
		
		String userId=studentSession.get("id");
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else {
			String userEmail = studentSession.get("email");
			Course theCourse = courseService.getCourseById(courseId);
			List<String> responses = Utilities.getResponseLink(theCourse.getResponseLink());
			theModel.addAttribute("name",studentSession.get("firstName")+" "+studentSession.get("lastName"));
			theModel.addAttribute("course",theCourse.getName());
			theModel.addAttribute("email",studentSession.get("email"));
			theModel.addAttribute("percentage",String.valueOf(Math.round(courseService.getScore(userEmail, responses))));
			theModel.addAttribute("theCourse", theCourse);
			return "course-home-student";
		}
	}
	
	@RequestMapping("/course-home-student")
	public String course_home_student(@RequestParam("courseId")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession
							,@RequestParam("vId")String videoId)
	{
		Course theCourse=courseService.getCourseById(courseId);
		List<Course> course =new ArrayList<Course>();
		course.add(theCourse);
		HashMap<String, String> instructorName=courseService.getInstructorNames(course);
		
		//getVideoLinks
		HashMap<String, String> videos=Utilities.getVideoLinks(theCourse.getVideoLink()+","+theCourse.getQuizLink());
		
		
		//convert youtube url to embeded url
		if(!(videoId.equals("1"))) {
		String embededLink=Utilities.getEmbededLink(videos.get(videoId));
		System.out.println(embededLink);
		theModel.addAttribute("embededLink",embededLink);
		}
		theModel.addAttribute("courseId",courseId);
		theModel.addAttribute("theCourse",theCourse);
		theModel.addAttribute("instructorName",instructorName);
		theModel.addAttribute("videos",videos);
		
		return "course-home-student";
	}
	

	@RequestMapping("/showModifyCourse")
	public String showModifyCourse(@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			
			if(!(courseId.equals(null))) {
			
				Course course = courseService.getCourseById(courseId);
				theModel.addAttribute("course",course);
				
				HashMap<String, String> myVideos = Utilities.getVideoLinks(course.getVideoLink());
				if(myVideos.containsKey("null")) {
					theModel.addAttribute("videoListSize", 0);
				}
				else {
					theModel.addAttribute("videoList",myVideos);
				}
				
				return "modify-course";
			}
			
			else {
				return "error-page";
			}
		}
	}
	
	@RequestMapping("/modifyCourse")
	public String modifyCourse(@ModelAttribute(name="course") Course course,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.modifyCourse(course, courseId);
			int newId = myCourse.getId();
			instructorSession.put("newCourseId", String.valueOf(newId));
			return "redirect:/home/course/showCourseHomeToInstructor";
		}
	}
}