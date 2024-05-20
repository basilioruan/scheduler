package com.projects.scheduler.application.ports.outbound;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;

public interface StudentOutPort {

	Student findById(Long id) throws SchedularRuntimeException;

}
