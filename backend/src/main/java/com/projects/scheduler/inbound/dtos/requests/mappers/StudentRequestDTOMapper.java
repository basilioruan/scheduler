package com.projects.scheduler.inbound.dtos.requests.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentRequestDTOMapper {

	public Student fromDTO(StudentRequestDTO dto) {
		if (Objects.isNull(dto)) {
			return null;
		}

		return Student.builder()
			.id(dto.getId())
			.name(dto.getName())
			.phone(dto.getPhone())
			.email(dto.getEmail())
			.photo(dto.getPhoto())
			.creationDate(dto.getCreationDate())
			.lastUpdateDate(dto.getLastUpdateDate())
			.lastLoginDate(dto.getLastLoginDate())
			.classType(dto.getClassType())
			.reschedules(dto.getReschedules())
			.build();
	}

}
