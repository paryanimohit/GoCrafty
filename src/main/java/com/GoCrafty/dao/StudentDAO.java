package com.GoCrafty.dao;

import com.GoCrafty.entity.Student;

public interface StudentDAO {
	public String studentLogin(String email,String password);

	public Student getUser(int id);

	public String getImage(int id);

	public String createAccount(Student theStudent);

}
