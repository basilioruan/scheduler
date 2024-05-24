package com.projects.scheduler.outbound.adapters;

import java.util.List;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.application.ports.outbound.StudentLevelOutPort;
import com.projects.scheduler.outbound.mappers.StudentLevelEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;
import com.projects.scheduler.outbound.persistence.repositories.StudentLevelRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentLevelAdapter implements StudentLevelOutPort {

	public static final String STUDENT_LEVEL_NOT_FOUND = "Student level was not found for parameters {id=%s}";

	private final StudentLevelRepository studentLevelRepository;

	private final StudentLevelEntityMapper studentLevelEntityMapper;

	@Override
	public StudentLevel findById(Long id) {
		try {
			StudentLevelEntity response = this.studentLevelRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(STUDENT_LEVEL_NOT_FOUND, id)));
			return this.studentLevelEntityMapper.fromEntity(response);
		}
		catch (EntityNotFoundException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}

	}

	@Override
	public List<StudentLevel> findAll() {
		try {
			List<StudentLevelEntity> studentLevelEntities = this.studentLevelRepository.findAll();
			return studentLevelEntities.stream().map(this.studentLevelEntityMapper::fromEntity).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public StudentLevel save(StudentLevel studentLevel) {
		try {
			StudentLevelEntity studentLevelToSave = this.studentLevelEntityMapper.fromDomain(studentLevel);
			StudentLevelEntity entitySaved = this.studentLevelRepository.save(studentLevelToSave);

			return this.studentLevelEntityMapper.fromEntity(entitySaved);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			StudentLevelEntity studentLevelFromDB = this.studentLevelRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.format(STUDENT_LEVEL_NOT_FOUND, id)));
			this.studentLevelRepository.deleteById(studentLevelFromDB.getId());
		}
		catch (EntityNotFoundException ex) {
			throw ex;
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

}
