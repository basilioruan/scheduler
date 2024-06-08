package com.projects.scheduler.outbound.adapters;

import java.util.List;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.ports.outbound.StudentOutPort;
import com.projects.scheduler.outbound.mappers.StudentEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.StudentEntity;
import com.projects.scheduler.outbound.persistence.repositories.StudentRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
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
			return this.studentEntityMapper.fromEntity(this.studentRepository.findById(id).orElse(null));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Student> findAll() {
		try {
			return this.studentRepository.findAll().stream().map(this.studentEntityMapper::fromEntity).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public Student save(Student student) {
		try {
			StudentEntity entityToSave = this.studentEntityMapper.fromDomain(student);
			StudentEntity entitySaved = this.studentRepository.save(entityToSave);

			return this.studentEntityMapper.fromEntity(entitySaved);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.studentRepository.deleteById(id);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

}
