package com.greatLearning.studentManagement.service;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatLearning.studentManagement.model.Student;

@Repository 
public class StudentServiceImpl implements StudentService{

	SessionFactory sessionFactory;
	Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			this.session = sessionFactory.getCurrentSession();
		}catch(HibernateException he) {
			this.session = sessionFactory.openSession();
		}
	}
	
	@Override
	@Transactional
	public List<Student> getAllStudents() {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>)session.createQuery("from Student").list();
		return list;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return session.get(Student.class, id);
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
		
	}

	@Override
	public Student deleteById(int id) {
		// TODO Auto-generated method stub
		Student student = session.get(Student.class, id);
		session.delete(student);
		return student;	
	}
	
}
