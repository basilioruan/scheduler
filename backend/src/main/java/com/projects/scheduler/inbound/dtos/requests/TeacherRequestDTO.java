package com.projects.scheduler.inbound.dtos.requests;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import org.springframework.validation.annotation.Validated;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@Validated
public class TeacherRequestDTO extends UserRequestDTO {

	@NotNull(message = "School subject must be not null")
	private SchoolSubjectIndicator schoolSubject;

	public TeacherRequestDTO(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
			SchoolSubjectIndicator schoolSubject) {
		super(id, name, phone, email, photo);
		this.schoolSubject = schoolSubject;
	}

}
