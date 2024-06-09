package com.projects.scheduler.inbound.dtos.responses.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherResponseDTOMapper {

	public TeacherResponseDTO fromDomain(Teacher domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return TeacherResponseDTO.builder()
			.id(domain.getId())
			.name(domain.getName())
			.phone(domain.getPhone())
			.email(domain.getEmail())
			.photo(domain.getPhoto())
			.creationDate(domain.getCreationDate())
			.lastUpdateDate(domain.getLastUpdateDate())
			.lastLoginDate(domain.getLastLoginDate())
			.schoolSubject(domain.getSchoolSubject())
			.build();
	}

}
