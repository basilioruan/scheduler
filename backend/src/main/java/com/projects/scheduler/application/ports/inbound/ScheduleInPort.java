package com.projects.scheduler.application.ports.inbound;

import java.util.List;

import com.projects.scheduler.inbound.dtos.requests.ScheduleRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.ScheduleResponseDTO;

public interface ScheduleInPort {

	ScheduleResponseDTO findById(Long id);

	List<ScheduleResponseDTO> findAll();

	ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO);

	void deleteById(Long id);

}
