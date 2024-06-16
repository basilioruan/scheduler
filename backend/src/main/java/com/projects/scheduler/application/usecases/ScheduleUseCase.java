package com.projects.scheduler.application.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.inbound.ScheduleInPort;
import com.projects.scheduler.application.ports.outbound.ScheduleOutPort;
import com.projects.scheduler.application.ports.outbound.StudentOutPort;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.inbound.dtos.requests.ScheduleRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.ScheduleRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.ScheduleResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.ScheduleResponseDTOMapper;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleUseCase implements ScheduleInPort {

	private final ScheduleOutPort scheduleOutPort;

	private final TeacherOutPort teacherOutPort;

	private final StudentOutPort studentOutPort;

	private final ScheduleResponseDTOMapper scheduleResponseDTOMapper;

	private final ScheduleRequestDTOMapper scheduleRequestDTOMapper;

	@Override
	public ScheduleResponseDTO findById(Long id) {
		try {
			return this.scheduleResponseDTOMapper.fromDomain(this.scheduleOutPort.findById(id));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<ScheduleResponseDTO> findAll() {
		try {
			return this.scheduleOutPort.findAll().stream().map(this.scheduleResponseDTOMapper::fromDomain).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
		try {
			Schedule schedule = this.scheduleRequestDTOMapper.fromDTO(scheduleRequestDTO);
			this.setAttributes(schedule, scheduleRequestDTO);
			return this.scheduleResponseDTOMapper.fromDomain(this.scheduleOutPort.save(schedule));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.validateIfScheduleExist(id);
			this.deleteById(id);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	private void setAttributes(Schedule schedule, ScheduleRequestDTO dto) {
		if (Objects.isNull(dto.getId())) {
			schedule.setCreationDate(LocalDateTime.now());
		}
		else {
			Schedule scheduleFromDb = this.validateIfScheduleExist(dto.getId());
			schedule.setCreationDate(scheduleFromDb.getCreationDate());
		}
		schedule.setTeacher(this.getTeacher(dto.getTeacherId()));
		schedule.setStudent(this.getStudent(dto.getStudentId()));
		schedule.setLastUpdateDate(LocalDateTime.now());
	}

	private Schedule validateIfScheduleExist(Long id) {
		Schedule scheduleFromDB = this.scheduleOutPort.findById(id);
		if (Objects.isNull(scheduleFromDB)) {
			throw new SchedularRuntimeException(String.format("Schedule was not found for parameters {id=%s}", id));
		}
		return scheduleFromDB;
	}

	private Teacher getTeacher(Long teacherId) {
		Teacher teacher = this.teacherOutPort.findById(teacherId);

		if (Objects.isNull(teacher)) {
			throw new SchedularRuntimeException(
					String.format("Teacher was not found for parameters {id=%s}", teacherId));
		}

		return teacher;
	}

	private Student getStudent(Long studentId) {
		Student student = this.studentOutPort.findById(studentId);

		if (Objects.isNull(student)) {
			throw new SchedularRuntimeException(
					String.format("Student was not found for parameters {id=%s}", studentId));
		}

		return student;
	}

}
