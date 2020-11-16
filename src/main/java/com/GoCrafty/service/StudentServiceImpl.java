package com.GoCrafty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.StudentDAO;
import com.GoCrafty.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDAO studentDAO;
	
//	@Override
//	@Transactional
//	public String studentLogin(String email, String password) {
//	
//		return studentDAO.studentLogin(email, password);
//		}

	@Override
	@Transactional
	public Student getStudent(int id) {
		return studentDAO.getUser(id);
		}

	@Override
	@Transactional
	public String getImage(int id) {
		return studentDAO.getImage(id);
	}

	@Override
	@Transactional
	public String createAccount(Student theStudent) {
		// TODO Auto-generated method stub
		return studentDAO.createAccount(theStudent);
	}

}
