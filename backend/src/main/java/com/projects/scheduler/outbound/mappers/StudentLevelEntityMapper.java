package com.projects.scheduler.outbound.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;

import org.springframework.stereotype.Component;

@Component
public class StudentLevelEntityMapper {

	public StudentLevel fromEntity(StudentLevelEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		return StudentLevel.builder()
			.id(entity.getId())
			.name(entity.getName())
			.code(entity.getCode())
			.description(entity.getDescription())
			.build();
	}

	public StudentLevelEntity fromDomain(StudentLevel domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return StudentLevelEntity.builder()
			.id(domain.getId())
			.name(domain.getName())
			.code(domain.getCode())
			.description(domain.getDescription())
			.build();
	}

}
