package com.GoCrafty.service;

import java.util.List;

import com.GoCrafty.entity.Admin;
import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;


public interface AdminService {

	Admin adminLogin(int id, String password);

	List<Student> getStudent();

	List<Instructor> getInstructor();

	String deleteInstructor(int instructorId);
//
//	String deleteUser(int userId);
//
//	List<BarberShops> getShops();
//
//	String deletebarberShop(int shopId);
//
//	String addBarberShop(BarberShops theShop, String finalDays, String hour, double[] lati_longi);
//
	String updatePassword(int adminId, String previousPassword, String confirmPassword);

	

}
