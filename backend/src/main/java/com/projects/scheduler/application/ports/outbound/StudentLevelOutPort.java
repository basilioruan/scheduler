package com.projects.scheduler.application.ports.outbound;

import java.util.List;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;

public interface StudentLevelOutPort {

	StudentLevel findById(Long id) throws SchedularRuntimeException;

	List<StudentLevel> findAll() throws SchedularRuntimeException;

	StudentLevel save(StudentLevel studentLevel) throws SchedularRuntimeException;

	void deleteById(Long id) throws SchedularRuntimeException;

}
