package com.GoCrafty.dao;

import com.GoCrafty.entity.Instructor;

public interface InstructorDAO {
	public String instructorLogin(String email,String password);

	public Instructor getUser(int id);

	public String getImage(int id);

}
