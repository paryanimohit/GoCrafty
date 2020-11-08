package com.GoCrafty.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public String studentLogin(String email, String password) {
		
		// TODO Auto-generated method stub
		return null;
		}

}
