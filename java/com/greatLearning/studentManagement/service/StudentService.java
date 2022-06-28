package com.greatLearning.studentManagement.service;

import java.util.List;
import com.greatLearning.studentManagement.model.Student;


public interface StudentService{
	
	public List<Student> getAllStudents();
	
	public void save(Student student);
	
	public Student deleteById(int id);
	
	public Student getStudentById(int id);
	
}
