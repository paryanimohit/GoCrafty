package com.GoCrafty.service;

import com.GoCrafty.entity.Student;

public interface StudentService {
	public String studentLogin(String email,String password);
	public Student getStudent(int id);
	public String getImage(int id);
	public String createAccount(Student theStudent);


}
