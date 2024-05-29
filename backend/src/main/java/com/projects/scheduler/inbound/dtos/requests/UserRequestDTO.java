package com.projects.scheduler.inbound.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@Valid
public abstract class UserRequestDTO {

	private Long id;

	@NotNull(message = "Name must be not null")
	private String name;

	@NotNull(message = "Phone must be not null")
	private String phone;

	@NotNull(message = "E-mail must be not null")
	private String email;

	private String photo;

}
