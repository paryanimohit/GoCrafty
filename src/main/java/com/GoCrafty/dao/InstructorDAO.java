package com.GoCrafty.dao;

import java.util.ArrayList;

import com.GoCrafty.entity.Instructor;

public interface InstructorDAO {
	public String instructorLogin(String email,String password);

	public Instructor getUser(int id);

	public String getImage(int id);

	public String createAccount(Instructor theInstructor);

	public Instructor setCurrentLogin(int id);

	public Instructor editProfile(ArrayList<String> updatedInstructor, String localId);

}
