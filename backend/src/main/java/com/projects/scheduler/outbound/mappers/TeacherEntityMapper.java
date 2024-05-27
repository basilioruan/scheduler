package com.projects.scheduler.outbound.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;

import org.springframework.stereotype.Component;

@Component
public class TeacherEntityMapper {

	public TeacherEntity fromDomain(Teacher domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return TeacherEntity.builder()
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

	public Teacher fromEntity(TeacherEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		return Teacher.builder()
			.id(entity.getId())
			.name(entity.getName())
			.phone(entity.getPhone())
			.email(entity.getEmail())
			.photo(entity.getPhoto())
			.creationDate(entity.getCreationDate())
			.lastUpdateDate(entity.getLastUpdateDate())
			.lastLoginDate(entity.getLastLoginDate())
			.schoolSubject(entity.getSchoolSubject())
			.build();
	}

}
