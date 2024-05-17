package com.projects.scheduler.application.ports.inbound;

import java.util.List;

import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;

public interface StudentLevelInPort {

	StudentLevelResponseDTO findById(Long id) throws SchedularRuntimeException;

	List<StudentLevelResponseDTO> findAll() throws SchedularRuntimeException;

	StudentLevelResponseDTO save(StudentLevelRequestDTO studentLevel) throws SchedularRuntimeException;

	void deleteById(Long id) throws SchedularRuntimeException;

}
