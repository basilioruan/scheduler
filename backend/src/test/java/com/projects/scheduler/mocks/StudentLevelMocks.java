package com.projects.scheduler.mocks;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;

public final class StudentLevelMocks {

	private StudentLevelMocks() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static StudentLevel getStudentLevelDomain() {
		return StudentLevel.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.description(DefaultValues.STRING_VALUE)
			.code(DefaultValues.STRING_VALUE)
			.build();
	}

	public static StudentLevelEntity getStudentLevelEntity() {
		return StudentLevelEntity.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.description(DefaultValues.STRING_VALUE)
			.code(DefaultValues.STRING_VALUE)
			.build();
	}

	public static StudentLevelResponseDTO getStudentLevelResponseDTO() {
		return StudentLevelResponseDTO.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.description(DefaultValues.STRING_VALUE)
			.code(DefaultValues.STRING_VALUE)
			.build();
	}

	public static StudentLevelRequestDTO getStudentLevelRequestDTO() {
		return StudentLevelRequestDTO.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.description(DefaultValues.STRING_VALUE)
			.code(DefaultValues.STRING_VALUE)
			.build();
	}

}
