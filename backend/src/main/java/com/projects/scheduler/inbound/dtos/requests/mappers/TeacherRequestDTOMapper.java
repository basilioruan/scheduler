package com.projects.scheduler.inbound.dtos.requests.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherRequestDTOMapper {

	public Teacher fromDTO(TeacherRequestDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}

		return Teacher.builder()
			.id(dto.getId())
			.name(dto.getName())
			.phone(dto.getPhone())
			.email(dto.getEmail())
			.photo(dto.getPhoto())
			.schoolSubject(dto.getSchoolSubject())
			.build();
	}

}
