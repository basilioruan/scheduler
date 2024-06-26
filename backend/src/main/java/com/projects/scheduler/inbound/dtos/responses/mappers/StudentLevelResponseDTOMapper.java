package com.projects.scheduler.inbound.dtos.responses.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class StudentLevelResponseDTOMapper {

	public StudentLevelResponseDTO fromDomain(StudentLevel domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return StudentLevelResponseDTO.builder()
			.id(domain.getId())
			.name(domain.getName())
			.code(domain.getCode())
			.description(domain.getDescription())
			.build();
	}

}
