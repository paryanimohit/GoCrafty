package com.GoCrafty.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Course;
import com.GoCrafty.entity.CourseEnrolled;
import com.GoCrafty.entity.Instructor;
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
	public List<Course> showCoursesByCategory(String category) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Course c WHERE c.category= :category");
		query.setParameter("category", category);
		@SuppressWarnings("unchecked")
		List<Course> course = query.getResultList();
		 
		return course;
	}

	@Override
	public HashMap<String, String> getInstructorNames(List<Course> course) {
		Session  currentSession= sessionFactory.getCurrentSession();
		HashMap<String, String> courseId_InstructorName = new HashMap<String, String>();
		Query query =null;
		Instructor theInstructor=new Instructor();
		for (Course theCourse:course)
		{
			query = currentSession.createQuery("from Instructor i WHERE i.id= :id");
			query.setParameter("id", theCourse.getInstructor_id());
			theInstructor=(Instructor) query.getSingleResult();
			courseId_InstructorName.put(String.valueOf(theCourse.getInstructor_id()), theInstructor.getFirstName()+" "+theInstructor.getLastName());
		}
		
		return courseId_InstructorName;
	}

	@Override
	public Course getCourseById(String id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Course theCourse=new Course();
		try {
			Query query=currentSession.createQuery("from Course c WHERE c.id= :id");
			query.setParameter("id", Integer.parseInt(id));
			theCourse=(Course) query.getSingleResult();
			
		}
		catch (Exception e) {
			return null;
		}
		
		return theCourse;
	}

	@Override
	public String enroll(String useId, String courseId) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			CourseEnrolled enroll= new CourseEnrolled(Integer.parseInt(courseId),Integer.parseInt(useId));
			currentSession.save(enroll);
		}
		catch (Exception e) {
			return null;
		}
		
		return "Enrolled";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getEnrolledCourses(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		ArrayList<CourseEnrolled> courseEnrolled=new ArrayList<CourseEnrolled>();
		ArrayList<Course> courseList=new ArrayList<Course>();

		try 
		{
			Query query=currentSession.createQuery("from CourseEnrolled c WHERE c.studentId= :id");
			query.setParameter("id", id);
			courseEnrolled=(ArrayList<CourseEnrolled>)query.getResultList();
			
			for(CourseEnrolled theCourseEnrolled: courseEnrolled)
			{
				int courseId=theCourseEnrolled.getCourseId();
				Course theCourse=getCourseById(String.valueOf(courseId));
				courseList.add(theCourse);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return courseList;
	}
	


}
