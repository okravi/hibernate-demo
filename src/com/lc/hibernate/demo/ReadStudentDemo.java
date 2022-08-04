package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			//create student object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Memo", "Livvie", "memo@j2.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving");
			System.out.println(tempStudent);
			session.save(tempStudent);
			//commit transaction
			session.getTransaction().commit();;
			
			//get the primary key
			System.out.println("Saved id is: " + tempStudent.getId());
			System.out.println("Done");
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve based on a key
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
	}

}
