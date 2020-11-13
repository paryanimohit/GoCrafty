package com.GoCrafty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "estimated_time_to_complete")
	private String estimatedTimeToComplete;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "instructor_id")
	private int instructor_id;
	
	public Course() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEstimatedTimeToComplete() {
		return estimatedTimeToComplete;
	}

	public void setEstimatedTimeToComplete(String estimatedTimeToComplete) {
		this.estimatedTimeToComplete = estimatedTimeToComplete;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getInstructor_id() {
		return instructor_id;
	}

	public void setInstructor_id(int instructor_id) {
		this.instructor_id = instructor_id;
	}

	public Course(String name, String description, String estimatedTimeToComplete, String category,
			int instructor_id) {
		super();
		this.name = name;
		this.description = description;
		this.estimatedTimeToComplete = estimatedTimeToComplete;
		this.category = category;
		this.instructor_id = instructor_id;
	}
	
	
}