package com.projects.scheduler.inbound.dtos.responses;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class TeacherResponseDTO extends UserResponseDTO {

	private SchoolSubjectIndicator schoolSubject;

	public TeacherResponseDTO(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
			LocalDateTime creationDate, LocalDateTime lastUpdateDate, LocalDateTime lastLoginDate,
			SchoolSubjectIndicator schoolSubject) {
		super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
		this.schoolSubject = schoolSubject;
	}

}
