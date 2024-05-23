package com.projects.scheduler.inbound.dtos.responses;

import java.time.LocalDateTime;

import com.projects.scheduler.inbound.dtos.UserDTO;
import com.projects.scheduler.utils.enums.ClassTypeIndicator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StudentResponseDTO extends UserDTO {

	private StudentLevelResponseDTO studentLevel;

	private ClassTypeIndicator classType;

	private int reschedules;

	public StudentResponseDTO(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
			LocalDateTime creationDate, LocalDateTime lastUpdateDate, LocalDateTime lastLoginDate,
			StudentLevelResponseDTO studentLevel, @NotNull ClassTypeIndicator classType, int reschedules) {
		super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
		this.studentLevel = studentLevel;
		this.classType = classType;
		this.reschedules = reschedules;
	}

}
