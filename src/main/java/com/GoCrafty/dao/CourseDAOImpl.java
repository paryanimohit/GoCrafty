package com.GoCrafty.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Course;
@Repository
public class CourseDAOImpl implements CourseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> showCategories() {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		List<String> categories = currentSession.createQuery(
			    "select distinct c.category " +
			    "from Course c", String.class)
			.getResultList();
		
		
		return categories;
	}

	@Override
	public List<Course> showCourse(String category) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Course c WHERE c.category= :category");
		query.setParameter("category", category);
		@SuppressWarnings("unchecked")
		List<Course> course = query.getResultList();
		return course;
	}

}
