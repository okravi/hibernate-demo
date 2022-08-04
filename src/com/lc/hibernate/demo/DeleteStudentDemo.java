package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			int studentId = 1;
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve based on a key
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the student id=1
			session.delete(myStudent);
			
			//delete student id=2
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			
		} finally {
			factory.close();
		}
	}

}
