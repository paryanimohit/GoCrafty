package com.GoCrafty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.StudentDAO;

public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	@Transactional
	public String studentLogin(String email, String password) {
		// TODO Auto-generated method stub
		return studentDAO.studentLogin(email, password);	}

}
