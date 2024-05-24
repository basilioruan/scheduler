package com.projects.scheduler.inbound.dtos.requests;

import java.time.LocalDateTime;

import com.projects.scheduler.inbound.dtos.UserDTO;
import com.projects.scheduler.utils.enums.ClassTypeIndicator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import org.springframework.validation.annotation.Validated;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Validated
public class StudentRequestDTO extends UserDTO {

	@NotNull(message = "Student level id must be not null")
	private Long studentLevelId;

	@NotNull(message = "Class type must be not null")
	private ClassTypeIndicator classType;

	private int reschedules;

	public StudentRequestDTO(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
			LocalDateTime creationDate, LocalDateTime lastUpdateDate, LocalDateTime lastLoginDate, Long studentLevelId,
			@NotNull ClassTypeIndicator classType, int reschedules) {
		super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
		this.studentLevelId = studentLevelId;
		this.classType = classType;
		this.reschedules = reschedules;
	}

}
