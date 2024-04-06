package com.RESTAPI.StudentRESTAPI;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	SessionFactory sf;

	//Create Student
	@PostMapping("/Students/create")
	public String create(@RequestBody Student student){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(student);
		t.commit();
		s.close();
		return "New Student created Successfully "+student;
	}	
	
	//Get all Students.
	@GetMapping("showAllStudent")
	public String getAllStudent(){
		Session s = sf.openSession();
		Query query = s.createQuery("from Student");
		List<Student> list = query.list();
		s.close();
		return "List of all Student "+list;
	}
	
	//Get Student by ID.
	@GetMapping("getStudent/{id}")
	public String getStudent(@PathVariable int id){
		Session s = sf.openSession();
		Student stud =s.get(Student.class, id);
		s.close();
		return "Specific Student by ID "+stud;
	}
	
	//Update existing Student
	@PutMapping("Student")
	public String update(@RequestBody Student student){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.update(student);
		t.commit();
		s.close();
		return "Record Updated "+student;
	}
	
	//Delete a Student
	@DeleteMapping("Student/{id}")
	public String delete(@PathVariable int id){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		Student stud =s.get(Student.class, id);
		s.delete(stud);
		t.commit();
		s.close();
		return "Record Delete Successfully "+id;
	}
}
