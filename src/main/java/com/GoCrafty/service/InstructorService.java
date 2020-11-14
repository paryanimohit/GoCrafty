package com.GoCrafty.service;

import com.GoCrafty.entity.Instructor;

public interface InstructorService {
	public String instructorLogin(String email,String password);
	public Instructor getInstructor(int id);
	public String getImage(int id);
	public String createAccount(Instructor theInstructor);
}
