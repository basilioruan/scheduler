package com.projects.scheduler.inbound.dtos.requests.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.inbound.dtos.requests.ScheduleRequestDTO;

import org.springframework.stereotype.Component;

@Component
public class ScheduleRequestDTOMapper {

	public Schedule fromDTO(ScheduleRequestDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}

		return Schedule.builder().id(dto.getId()).dateTime(dto.getDateTime()).status(dto.getStatus()).build();
	}

}
