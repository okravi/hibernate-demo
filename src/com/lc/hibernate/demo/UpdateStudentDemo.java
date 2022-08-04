package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			System.out.println("Updating student");
			
			//change the name
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//bulk email update
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Updating emil for all students");
			session.createQuery("update Student set email='changed@gmail.com'").executeUpdate();
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
