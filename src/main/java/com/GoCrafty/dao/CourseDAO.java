package com.GoCrafty.dao;

import java.util.List;

import com.GoCrafty.entity.Course;

public interface CourseDAO {
	public List<String> showCategories();

	public List<Course> showCourse(String category);


}
