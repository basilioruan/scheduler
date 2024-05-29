package com.projects.scheduler.inbound.dtos.requests;

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
public class StudentRequestDTO extends UserRequestDTO {

	@NotNull(message = "Student level id must be not null")
	private Long studentLevelId;

	@NotNull(message = "Class type must be not null")
	private ClassTypeIndicator classType;

	private int reschedules;

	@NotNull(message = "Teacher must be not null")
	private Long teacherId;

	public StudentRequestDTO(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
			Long studentLevelId, @NotNull ClassTypeIndicator classType, int reschedules, @NotNull Long teacherId) {
		super(id, name, phone, email, photo);
		this.studentLevelId = studentLevelId;
		this.classType = classType;
		this.reschedules = reschedules;
		this.teacherId = teacherId;
	}

}
