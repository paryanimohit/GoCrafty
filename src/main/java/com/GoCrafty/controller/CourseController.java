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
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			Course myCourse = courseService.addCourse(course);
			int newId = myCourse.getId();
			instructorSession.put("newCourseId", String.valueOf(newId));
			return "redirect:/home/course/showCourseHomeToInstructor";
		}
	}
	
	@RequestMapping("/showCourseHomeToInstructor")
	public String showCourseHomeToInstructor(@RequestParam("id")int id,@SessionAttribute(name="tempSession") HashMap<String,String> instructorSession,Model theModel) {
		
		String userId=instructorSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {
			String newCourseId = null;
			if(instructorSession.containsKey("newCourseId")) {
				newCourseId = instructorSession.get("newCourseId");
			}
			else {
				newCourseId = String.valueOf(id);
				instructorSession.put("newCourseId", String.valueOf(newCourseId));
				
			}
			Course newCourse = courseService.getCourseById(String.valueOf(newCourseId));
			theModel.addAttribute("course",newCourse);
			return "course-home";
		}
	}
	

	@RequestMapping("/course-home-student")
	public String course_home_student(@RequestParam("courseId")String courseId,Model theModel,@SessionAttribute(name="tempSession") HashMap<String,String> studentSession
							,@RequestParam("vId")String videoId)
	{
		String userId=studentSession.get("id");
		
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=student";
		}
		else 
		{
			Course theCourse=courseService.getCourseById(courseId);
			List<Course> course =new ArrayList<Course>();
			course.add(theCourse);
			HashMap<String, String> instructorName=courseService.getInstructorNames(course);
			
			//getVideoLinks
			HashMap<String, String> videos=Utilities.getVideoLinks(theCourse.getVideoLink());
			
		
			//convert youtube url to embeded url
				if(!(videoId.equals("1"))) 
				{
				String embededLink=Utilities.getEmbededLink(videos.get(videoId));
				
				theModel.addAttribute("embededLink",embededLink);
				}
			theModel.addAttribute("courseId",courseId);
			theModel.addAttribute("theCourse",theCourse);
			theModel.addAttribute("instructorName",instructorName);
			theModel.addAttribute("videos",videos);
			
			return "course-home-student";
		}
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
				HashMap<String, String> myQuizes = Utilities.getQuizLinks(course.getQuizLink());
				
				if(myVideos.containsKey("null") ) {
					theModel.addAttribute("videoListSize", 0);
				}
				else {
					theModel.addAttribute("videoList",myVideos);
				}
				
				if(myQuizes.containsKey("null") ) {
					theModel.addAttribute("quizListSize", 0);
				}
				else {
					theModel.addAttribute("quizList",myQuizes);
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
	
	@RequestMapping("/modifyVideos")
	public String modifyVideos(@RequestParam("videoName")String videoName, @RequestParam("youtubeLink")String youtubeLink, Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			String uploadVideo = videoName+"@"+youtubeLink;
			courseService.uploadVideo(uploadVideo,courseId);
		return "redirect:/home/course/showModifyCourse";
		}
	}
	
	@RequestMapping("/modifyQuiz")
	public String modifyQuiz(@RequestParam("quizName")String quizName, @RequestParam("docsLink")String docsLink, Model theModel, @SessionAttribute(name="tempSession") HashMap<String,String> instructorSession) {
		
		String courseId = instructorSession.get("newCourseId");
		String userId=instructorSession.get("id");
		if (userId==null || userId.equals("temp"))
		{
			return "redirect:/home/userLogin?role=instructor";
		}
		else {	
			String uploadQuiz = quizName+"@"+docsLink;
			courseService.uploadQuiz(uploadQuiz,courseId);
		return "redirect:/home/course/showModifyCourse";
		}
	}
}