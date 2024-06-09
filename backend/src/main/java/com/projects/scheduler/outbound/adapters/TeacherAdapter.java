package com.projects.scheduler.outbound.adapters;

import java.util.List;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.outbound.mappers.TeacherEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;
import com.projects.scheduler.outbound.persistence.repositories.TeacherRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeacherAdapter implements TeacherOutPort {

	private final TeacherRepository teacherRepository;

	private final TeacherEntityMapper teacherEntityMapper;

	@Override
	public Teacher findById(Long id) {
		try {
			return this.teacherEntityMapper.fromEntity(this.teacherRepository.findById(id).orElse(null));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Teacher> findAll() {
		try {
			return this.teacherRepository.findAll().stream().map(this.teacherEntityMapper::fromEntity).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public Teacher save(Teacher teacher) {
		try {
			TeacherEntity entity = this.teacherEntityMapper.fromDomain(teacher);
			return this.teacherEntityMapper.fromEntity(this.teacherRepository.save(entity));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.teacherRepository.deleteById(id);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

}
