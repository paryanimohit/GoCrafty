package com.GoCrafty.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.GoCrafty.entity.Instructor;
import com.GoCrafty.entity.Student;
import com.GoCrafty.service.Encryption;

public class AuthenticationDAOImpl implements AuthenticationDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public String studentLogin(Object theUser, String role) {
		
		Session  currentSession= sessionFactory.getCurrentSession();
		try {
			Query q = null;
			String id = null;
			String decryptedPassword=null;
			Encryption encr = new Encryption();
			if(role.equals("student"))
			{
				Student theStudent=(Student)theUser;//from login
				System.out.println("Student "+theStudent.getFirstName());
				q= currentSession.createQuery("from Student s WHERE s.email= :email");
				q.setParameter("email", theStudent.getEmail());
							Student myStudent=(Student) q.getSingleResult();
							String fetchPassword= myStudent.getPassword();
							decryptedPassword=encr.decrypt(fetchPassword);
							 String.valueOf(myStudent.getId());
							if(decryptedPassword.contentEquals(theStudent.getPassword()))
							{
								return id;
								}
							else 
								return null;
			}					

			else if(role.equals("instructor")) {
				Instructor theInstructor=(Instructor)theUser;
				q= currentSession.createQuery("from Instructor i WHERE i.email= :email");
								q.setParameter("email", theInstructor.getEmail());
								Instructor myInstructor=(Instructor) q.getSingleResult();
								String fetchPassword2= myInstructor.getPassword();
								decryptedPassword=encr.decrypt(fetchPassword2);
								 String.valueOf(myInstructor.getId());
								if(decryptedPassword.contentEquals(theInstructor.getPassword()))
									{
									return id;
									}
								else 
									return null;
									
			}			

			
			
		}
			 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
		
		
	}

}
