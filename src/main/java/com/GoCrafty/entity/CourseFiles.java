package com.GoCrafty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_details")
public class CourseFiles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "video_link")
	private String videoLink;
	
	@Column(name = "quiz_link")
	private String quizLink;
	
	public CourseFiles() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public String getQuizLink() {
		return quizLink;
	}

	public void setQuizLink(String quizLink) {
		this.quizLink = quizLink;
	}

	public CourseFiles(int courseId, String videoLink, String quizLink) {
		
		this.courseId = courseId;
		this.videoLink = videoLink;
		this.quizLink = quizLink;
	}
	
	
	
	
}
