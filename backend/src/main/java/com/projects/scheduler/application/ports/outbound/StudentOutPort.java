package com.projects.scheduler.application.ports.outbound;

import java.util.List;

import com.projects.scheduler.application.domains.Student;

public interface StudentOutPort {

	Student findById(Long id);

	List<Student> findAll();

	Student save(Student student);

	void deleteById(Long id);

}
