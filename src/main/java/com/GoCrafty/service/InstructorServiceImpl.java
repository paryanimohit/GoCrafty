package com.GoCrafty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.InstructorDAO;
import com.GoCrafty.entity.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorDAO instructorDAO;
	
	@Override
	@Transactional
	public String instructorLogin(String email, String password) {
	
		return instructorDAO.instructorLogin(email, password);
		}

	@Override
	@Transactional
	public Instructor getInstructor(int id) {
		return instructorDAO.getUser(id);
		}

	@Override
	@Transactional
	public String getImage(int id) {
		return instructorDAO.getImage(id);
	}

}
