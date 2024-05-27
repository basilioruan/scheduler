package com.projects.scheduler.inbound.dtos.responses.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.utils.ValueUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentResponseDTOMapper {

	private final StudentLevelResponseDTOMapper studentLevelResponseDTOMapper;

	public StudentResponseDTO fromDomain(Student domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return StudentResponseDTO.builder()
			.id(domain.getId())
			.name(domain.getName())
			.phone(domain.getPhone())
			.email(domain.getEmail())
			.photo(domain.getPhoto())
			.creationDate(domain.getCreationDate())
			.lastUpdateDate(domain.getLastUpdateDate())
			.lastLoginDate(domain.getLastLoginDate())
			.studentLevel(this.studentLevelResponseDTOMapper.fromDomain(domain.getStudentLevel()))
			.classType(domain.getClassType())
			.reschedules(domain.getReschedules())
			.teacher(ValueUtils.buildPairIdLabel(domain.getTeacher().getId(), domain.getTeacher().getName()))
			.build();
	}

}
