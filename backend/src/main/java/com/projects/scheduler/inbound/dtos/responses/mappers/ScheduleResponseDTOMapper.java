package com.projects.scheduler.inbound.dtos.responses.mappers;

import java.util.Objects;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.inbound.dtos.responses.PairIdLabelDTO;
import com.projects.scheduler.inbound.dtos.responses.ScheduleResponseDTO;
import com.projects.scheduler.utils.ValueUtils;

import org.springframework.stereotype.Component;

@Component
public class ScheduleResponseDTOMapper {

	public ScheduleResponseDTO fromDomain(Schedule domain) {
		if (Objects.isNull(domain)) {
			return null;
		}

		return ScheduleResponseDTO.builder()
			.id(domain.getId())
			.dateTime(domain.getDateTime())
			.teacher(ValueUtils.buildPairIdLabel(domain.getTeacher().getId(), domain.getTeacher().getName()))
			.student(this.getStudentInfo(domain.getStudent()))
			.status(domain.getStatus())
			.creationDate(domain.getCreationDate())
			.lastUpdateDate(domain.getLastUpdateDate())
			.build();
	}

	private PairIdLabelDTO getStudentInfo(Student student) {
		if (Objects.isNull(student)) {
			return null;
		}

		return ValueUtils.buildPairIdLabel(student.getId(), student.getName());
	}

}
