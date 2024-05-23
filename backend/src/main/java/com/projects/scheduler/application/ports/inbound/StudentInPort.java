package com.projects.scheduler.application.ports.inbound;

import java.util.List;

import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;

public interface StudentInPort {

	StudentResponseDTO findById(Long id);

	List<StudentResponseDTO> findAll();

	StudentResponseDTO save(StudentRequestDTO student);

	void deleteById(Long id);

}
