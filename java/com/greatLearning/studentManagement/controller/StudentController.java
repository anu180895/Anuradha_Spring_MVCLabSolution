package com.greatLearning.studentManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.studentManagement.service.StudentService;

import com.greatLearning.studentManagement.model.Student;

@Controller
@RequestMapping(("/students"))

public class StudentController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/list")
	public String getAllStudents(Model theModel) {
		List<Student> res = studentService.getAllStudents();
		theModel.addAttribute("studentModel", res);
		return "studentlist";
	}

	@RequestMapping("/add")
	public String addStudent(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student", student);
		return "studentsave";
	}

	@RequestMapping("/update")
	public String updateStudent(@RequestParam("id") int id, Model theModel) {
		Student student = studentService.getStudentById(id);
		theModel.addAttribute("student", student);
		return "studentsave";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id) {
		Student student = studentService.deleteById(id);
		System.out.println("Deleted Book Id"+ student.getId());
		return "redirect:/students/list";
	}

	@RequestMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("course") String course, @RequestParam("country") String country){
		Student student;
		if(id != 0) {
			student=studentService.getStudentById(id);
		}
		else
		{
			student = new Student();
		}
		student.setFirstname(firstname);
		student.setLastname(lastname);
		student.setCourse(course);
		student.setCountry(country);
		return "redirect:/students/list";
	}
}
