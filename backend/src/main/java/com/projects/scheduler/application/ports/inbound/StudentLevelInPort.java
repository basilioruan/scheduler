package com.projects.scheduler.application.ports.inbound;

import java.util.List;

import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;

public interface StudentLevelInPort {

	StudentLevelResponseDTO findById(Long id);

	List<StudentLevelResponseDTO> findAll();

	StudentLevelResponseDTO save(StudentLevelRequestDTO studentLevel);

	void deleteById(Long id);

}
