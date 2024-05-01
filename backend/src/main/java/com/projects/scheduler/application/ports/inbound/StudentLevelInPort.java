package com.projects.scheduler.application.ports.inbound;

import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;

import java.util.List;

public interface StudentLevelInPort {

    StudentLevelResponseDTO findById(Long id);

    List<StudentLevelResponseDTO> findAll();

    StudentLevelResponseDTO save(StudentLevelRequestDTO studentLevel);

    void deleteById(Long id);

}
