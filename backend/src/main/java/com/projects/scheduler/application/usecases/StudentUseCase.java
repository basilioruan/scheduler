package com.projects.scheduler.application.usecases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.inbound.StudentInPort;
import com.projects.scheduler.application.ports.outbound.StudentLevelOutPort;
import com.projects.scheduler.application.ports.outbound.StudentOutPort;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.StudentRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.StudentResponseDTOMapper;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentUseCase implements StudentInPort {

	private final StudentOutPort studentOutPort;

	private final StudentLevelOutPort studentLevelOutPort;

	private final TeacherOutPort teacherOutPort;

	private final StudentRequestDTOMapper studentRequestDTOMapper;

	private final StudentResponseDTOMapper studentResponseDTOMapper;

	@Override
	public StudentResponseDTO findById(Long id) {
		return this.studentResponseDTOMapper.fromDomain(this.studentOutPort.findById(id));
	}

	@Override
	public List<StudentResponseDTO> findAll() {
		return this.studentOutPort.findAll().stream().map(this.studentResponseDTOMapper::fromDomain).toList();
	}

	@Override
	public StudentResponseDTO save(StudentRequestDTO studentRequestDTO) {
		Student student = this.studentRequestDTOMapper.fromDTO(studentRequestDTO);
		this.setStudentAttributes(student, studentRequestDTO);
		return this.studentResponseDTOMapper.fromDomain(this.studentOutPort.save(student));
	}

	@Override
	public void deleteById(Long id) {
		this.studentOutPort.deleteById(id);
	}

	private StudentLevel getStudentLevel(Long studentLevelId) {
		return this.studentLevelOutPort.findById(studentLevelId);
	}

	private Teacher getTeacher(Long teacherId) {
		return this.teacherOutPort.findById(teacherId);
	}

	private void setStudentAttributes(Student student, StudentRequestDTO studentRequestDTO) {
		student.setStudentLevel(this.getStudentLevel(studentRequestDTO.getStudentLevelId()));
		student.setLastUpdateDate(LocalDateTime.now());
		student.setTeacher(this.getTeacher(studentRequestDTO.getTeacherId()));
		if (Objects.isNull(student.getId())) {
			student.setCreationDate(LocalDateTime.now());
		}
		else {
			Student studentFromDB = this.studentOutPort.findById(student.getId());
			if (Objects.isNull(studentFromDB)) {
				throw new SchedularRuntimeException("Student not found");
			}
			student.setCreationDate(studentFromDB.getCreationDate());
		}
	}

}
