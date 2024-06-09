package com.projects.scheduler.application.ports.outbound;

import java.util.List;

import com.projects.scheduler.application.domains.Teacher;

public interface TeacherOutPort {

	Teacher findById(Long id);

	List<Teacher> findAll();

	Teacher save(Teacher teacher);

	void deleteById(Long id);

}
