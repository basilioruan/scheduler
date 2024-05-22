package com.projects.scheduler.outbound.adapters;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.ports.outbound.StudentOutPort;
import com.projects.scheduler.outbound.mappers.StudentEntityMapper;
import com.projects.scheduler.outbound.persistence.repositories.StudentRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentAdapter implements StudentOutPort {

	private final StudentRepository studentRepository;

	private final StudentEntityMapper studentEntityMapper;

	@Override
	public Student findById(Long id) {
		try {
			return this.studentEntityMapper.fromEntity(this.studentRepository.getReferenceById(id));
		}
		catch (EntityNotFoundException ex) {
			return null;
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

}
