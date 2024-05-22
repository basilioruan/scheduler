package com.projects.scheduler.application.ports.outbound;

import java.util.List;

import com.projects.scheduler.application.domains.StudentLevel;

public interface StudentLevelOutPort {

	StudentLevel findById(Long id);

	List<StudentLevel> findAll();

	StudentLevel save(StudentLevel studentLevel);

	void deleteById(Long id);

}
