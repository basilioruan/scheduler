package com.projects.scheduler.outbound.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleEntityMapper {

	private final StudentEntityMapper studentEntityMapper;

	private final TeacherEntityMapper teacherEntityMapper;

	public Schedule fromEntity(ScheduleEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		return Schedule.builder()
			.id(entity.getId())
			.dateTime(entity.getDateTime())
			.teacher(this.teacherEntityMapper.fromEntity(entity.getTeacher()))
			.student(this.studentEntityMapper.fromEntity(entity.getStudent()))
			.status(entity.getStatus())
			.creationDate(entity.getCreationDate())
			.lastUpdateDate(entity.getLastUpdateDate())
			.build();
	}

	public ScheduleEntity fromDomain(Schedule domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return ScheduleEntity.builder()
			.id(domain.getId())
			.dateTime(domain.getDateTime())
			.teacher(this.teacherEntityMapper.fromDomain(domain.getTeacher()))
			.student(this.studentEntityMapper.fromDomain(domain.getStudent()))
			.status(domain.getStatus())
			.creationDate(domain.getCreationDate())
			.lastUpdateDate(domain.getLastUpdateDate())
			.build();
	}

}
