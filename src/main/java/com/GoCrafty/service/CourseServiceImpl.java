package com.GoCrafty.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.CourseDAO;
import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.Student;
@Service
public class CourseServiceImpl implements CourseService {

	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	@Transactional
	public List<String> showCategories() {
		return courseDAO.showCategories();
	}

	@Override
	@Transactional
	public List<Course> showCoursesByCategory(String category) {
		return courseDAO.showCoursesByCategory(category);
	}

	@Override
	@Transactional
	public HashMap<String, String> getInstructorNames(List<Course> course) {
		return courseDAO.getInstructorNames(course);	}

	@Override
	@Transactional
	public Course getCourseById(String id) {
		return courseDAO.getCourseById(id);	
		}

	@Override
	@Transactional
	public String enroll(String useId, String courseId) {
		return courseDAO.enroll(useId,courseId );	
	}

	@Override
	@Transactional
	public Course addCourse(Course course) {
		return courseDAO.addCourse(course);
	}

	@Override
	@Transactional
	public List<Course> getEnrolledCourses(int id) {
		return courseDAO.getEnrolledCourses(id);

	}

	@Override
	@Transactional
	public Course modifyCourse(Course course, String courseId) {
		return courseDAO.modifyCourse(course,courseId);
	}
	
	@Override
	@Transactional
	public float getScore(String email, List<String> repsonseLink, int courseID) {
		return courseDAO.getScore(email, repsonseLink, courseID);
	}

	@Override
	@Transactional
	public String uploadVideo(String uploadVideo, String courseId) {
		// TODO Auto-generated method stub
		return courseDAO.uploadVideo(uploadVideo,courseId);
	}

	@Override
	@Transactional
	public String uploadQuiz(String uploadQuiz, String courseId) {
		// TODO Auto-generated method stub
		return courseDAO.uploadQuiz(uploadQuiz,courseId);
	}

	@Override
	@Transactional
	public ArrayList<Student> getStudentsEnrolled(String newCourseId) {
		// TODO Auto-generated method stub
		return courseDAO.getStudentsEnrolled(newCourseId);
	}

}
