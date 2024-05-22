package com.projects.scheduler.application.ports.outbound;

import com.projects.scheduler.application.domains.Student;

public interface StudentOutPort {

	Student findById(Long id);

}
