package com.projects.scheduler.application.ports.outbound;

import com.projects.scheduler.application.domains.Teacher;

public interface TeacherOutPort {

	Teacher findById(Long id);

}
