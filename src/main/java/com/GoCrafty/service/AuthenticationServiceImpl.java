package com.GoCrafty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GoCrafty.dao.AuthenticationDAO;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private AuthenticationDAO authenticationDAO;

	@Override
	@Transactional
	public String userLogin(String email, String password, String role) {
		
		return authenticationDAO.studentLogin(email, password,role);	
		}
	

}
