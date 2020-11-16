package com.GoCrafty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.CourseDAO;
import com.GoCrafty.dao.StudentDAO;
import com.GoCrafty.entity.Course;
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
	public List<Course> showCourse(String category) {
		// TODO Auto-generated method stub
		return courseDAO.showCourse(category);
	}

}
