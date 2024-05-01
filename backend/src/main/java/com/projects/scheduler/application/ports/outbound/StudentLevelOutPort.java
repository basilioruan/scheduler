package com.projects.scheduler.application.ports.outbound;

import com.projects.scheduler.application.domains.StudentLevel;

import java.util.List;

public interface StudentLevelOutPort {

    StudentLevel findById(Long id);

    List<StudentLevel> findAll();

    StudentLevel save(StudentLevel studentLevel);

    void deleteById(Long id);

}
