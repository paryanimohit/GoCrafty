package com.GoCrafty.service;

import java.util.ArrayList;

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
		return studentDAO.getStudent(id);
		}

	@Override
	@Transactional
	public String getImage(int id) {
		return studentDAO.getImage(id);
	}

	@Override
	@Transactional
	public String createAccount(Student theStudent) {
		return studentDAO.createAccount(theStudent);
	}

	@Override
	@Transactional
	public Student editProfile(ArrayList<String> updatedStudent, String localId) {
		return studentDAO.editProfile(updatedStudent, localId);
	}

}
