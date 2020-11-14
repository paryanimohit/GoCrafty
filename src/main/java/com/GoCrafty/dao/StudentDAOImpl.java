package com.GoCrafty.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GoCrafty.entity.Student;
import com.GoCrafty.service.Encryption;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public String studentLogin(String email, String password) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Query q= currentSession.createQuery("from Student s WHERE s.email= :email");
			q.setParameter("email", email);
			Student myStudent=(Student) q.getSingleResult();
			String fetchPassword= myStudent.getPassword();
			Encryption encr = new Encryption();
			String decryptedPassword=encr.decrypt(fetchPassword);
			String id=String.valueOf(myStudent.getId());
			if(decryptedPassword.contentEquals(password)){
				return id;
				}
			else 
				return null;
				}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		}



	@Override
	public Student getUser(int id) {
		Session  currentSession= sessionFactory.getCurrentSession();
		Student myStudent= currentSession.get(Student.class, id);
		return myStudent;
	}



	@Override
	public String getImage(int id) {
		Session currentSession= sessionFactory.getCurrentSession();
		Student thisStudent= currentSession.get(Student.class, id);
		 try {
		 byte[] buffer=thisStudent.getProfilePic();
		 ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
		
	      BufferedImage bImage2 = ImageIO.read(bis);
	       ByteArrayOutputStream os = new ByteArrayOutputStream();
	      ImageIO.write(bImage2, "jpg", os );
	     String img= Base64.getEncoder().encodeToString(os.toByteArray());
	     
	     return img;
		 }
		 catch (Exception e) {
		    
		     return "failed";
			
		}
	}



	@Override
	public String createAccount(Student theStudent) {
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Encryption encr = new Encryption();
			String encryptedPassword=encr.encrypt(theStudent.getPassword());
			System.out.println("StuDAO, apply for job: |"+theStudent.getApplyForJob()+"|");
			Student newStudent;
			if(theStudent.getApplyForJob()==null)
			{
				theStudent.setApplyForJob("0");
			}
			newStudent= new Student(theStudent.getFirstName(), theStudent.getLastName(),theStudent.getEmail(),encryptedPassword,theStudent.getBirthDate(),theStudent.getApplyForJob());

	
			currentSession.save(newStudent);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Cannot create user! Please try again";
		}
			return "Success! User created, Please login to continue";
	}

}
