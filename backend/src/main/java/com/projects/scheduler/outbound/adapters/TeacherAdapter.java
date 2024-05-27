package com.projects.scheduler.outbound.adapters;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.outbound.mappers.TeacherEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;
import com.projects.scheduler.outbound.persistence.repositories.TeacherRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherAdapter implements TeacherOutPort {

	public static final String TEACHER_NOT_FOUND = "Teacher was not found for parameters {id=%s}";

	private final TeacherRepository teacherRepository;

	private final TeacherEntityMapper teacherEntityMapper;

	@Override
	public Teacher findById(Long id) {
		try {
			TeacherEntity entity = this.teacherRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(TEACHER_NOT_FOUND, id)));
			return this.teacherEntityMapper.fromEntity(entity);
		}
		catch (EntityNotFoundException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}

	}

}
