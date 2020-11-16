package com.GoCrafty.service;

import java.util.List;

import com.GoCrafty.entity.Course;

public interface CourseService {

	public List<String> showCategories();

	public List<Course> showCourse(String category);
}
