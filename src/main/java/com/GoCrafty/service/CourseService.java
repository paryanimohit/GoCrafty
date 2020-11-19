package com.GoCrafty.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.GoCrafty.entity.Course;

public interface CourseService {

	public List<String> showCategories();

	public List<Course> showCoursesByCategory(String category);

	public HashMap<String, String> getInstructorNames(List<Course> course);
	
	public Course getCourseById(String id);

	public String enroll(String useId, String courseId);

	public List<Course> getEnrolledCourses(int id);
}
