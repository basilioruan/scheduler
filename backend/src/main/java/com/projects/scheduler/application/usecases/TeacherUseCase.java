package com.projects.scheduler.application.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.inbound.TeacherInPort;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.TeacherRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.TeacherResponseDTOMapper;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherUseCase implements TeacherInPort {

	private final TeacherOutPort teacherOutPort;

	private final TeacherResponseDTOMapper teacherResponseDTOMapper;

	private final TeacherRequestDTOMapper teacherRequestDTOMapper;

	@Override
	public TeacherResponseDTO findById(Long id) {
		try {
			return this.teacherResponseDTOMapper.fromDomain(this.teacherOutPort.findById(id));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<TeacherResponseDTO> findAll() {
		try {
			return this.teacherOutPort.findAll().stream().map(this.teacherResponseDTOMapper::fromDomain).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public TeacherResponseDTO save(TeacherRequestDTO dto) {
		try {
			Teacher teacher = this.teacherRequestDTOMapper.fromDTO(dto);
			this.setAttributes(teacher, dto);
			return this.teacherResponseDTOMapper.fromDomain(this.teacherOutPort.save(teacher));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.validateIfTeacherExist(id);
			this.teacherOutPort.deleteById(id);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	private void setAttributes(Teacher teacher, TeacherRequestDTO dto) {
		if (Objects.isNull(dto.getId())) {
			teacher.setCreationDate(LocalDateTime.now());
		}
		else {
			Teacher teacherFromDb = this.validateIfTeacherExist(dto.getId());
			teacher.setCreationDate(teacherFromDb.getCreationDate());
		}
		teacher.setLastUpdateDate(LocalDateTime.now());
	}

	private Teacher validateIfTeacherExist(Long id) {
		Teacher teacherFromDB = this.teacherOutPort.findById(id);
		if (Objects.isNull(teacherFromDB)) {
			throw new SchedularRuntimeException(String.format("Teacher was not found for parameters {id=%s}", id));
		}
		return teacherFromDB;
	}

}
