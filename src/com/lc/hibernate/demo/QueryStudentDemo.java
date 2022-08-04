package com.lc.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display students
			displayStudents(theStudents);
			
			//query students: lastName="Johnson"
			theStudents = session.createQuery("from Student s where s.lastName='Johnson'").getResultList();
			
			//display students
			System.out.println("Students with last name of Johnson:");
			displayStudents(theStudents);
			
			//query students: lastName="Johnson" or firstNAme="Anna"
			theStudents = session.createQuery("from Student s where s.lastName='Johnson' OR s.firstName='Anna'").getResultList();
			
			//display students
			System.out.println("Students with last name of Johnson or fist name of Anna:");
			displayStudents(theStudents);
			
			//query students: email ends with "2.com"
			theStudents = session.createQuery("from Student s where s.email LIKE '%2.com'").getResultList();
			
			//display students
			System.out.println("Students with email ending with '2.com'");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
