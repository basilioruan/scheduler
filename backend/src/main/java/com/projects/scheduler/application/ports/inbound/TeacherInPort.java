package com.projects.scheduler.application.ports.inbound;

import java.util.List;

import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;

public interface TeacherInPort {

	TeacherResponseDTO findById(Long id);

	List<TeacherResponseDTO> findAll();

	TeacherResponseDTO save(TeacherRequestDTO dto);

	void deleteById(Long id);

}
