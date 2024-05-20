package com.projects.scheduler.outbound.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.outbound.persistence.entities.StudentEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentEntityMapper {

	private final StudentLevelEntityMapper studentLevelEntityMapper;

	public Student fromEntity(StudentEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		return Student.builder()
			.id(entity.getId())
			.name(entity.getName())
			.phone(entity.getPhone())
			.email(entity.getEmail())
			.photo(entity.getPhoto())
			.creationDate(entity.getCreationDate())
			.lastUpdateDate(entity.getLastUpdateDate())
			.lastLoginDate(entity.getLastLoginDate())
			.studentLevel(this.studentLevelEntityMapper.fromEntity(entity.getStudentLevel()))
			.classType(entity.getClassType())
			.reschedules(entity.getReschedules())
			.build();
	}

	public StudentEntity fromDomain(Student domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return StudentEntity.builder()
			.id(domain.getId())
			.name(domain.getName())
			.phone(domain.getPhone())
			.email(domain.getEmail())
			.photo(domain.getPhoto())
			.creationDate(domain.getCreationDate())
			.lastUpdateDate(domain.getLastUpdateDate())
			.lastLoginDate(domain.getLastLoginDate())
			.studentLevel(this.studentLevelEntityMapper.fromDomain(domain.getStudentLevel()))
			.classType(domain.getClassType())
			.reschedules(domain.getReschedules())
			.build();
	}

}
