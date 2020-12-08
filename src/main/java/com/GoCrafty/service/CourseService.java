package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.CourseEnrolled;
import com.GoCrafty.entity.Student;

public interface CourseService {

	public List<String> showCategories();

	public List<Course> showCoursesByCategory(String category);

	public HashMap<String, String> getInstructorNames(List<Course> course);
	
	public Course getCourseById(String id);

	public String enroll(String useId, String courseId);

	public Course addCourse(Course course);

	public List<Course> getEnrolledCourses(int id);

	public Course modifyCourse(Course course, String courseId);
	
	public float getScore(String userEmail, List<String> reponselink);

	public String uploadVideo(String uploadVideo, String courseId);

	public String uploadQuiz(String uploadQuiz, String courseId);

	public ArrayList<Student> getStudentsEnrolled(String newCourseId);


}
