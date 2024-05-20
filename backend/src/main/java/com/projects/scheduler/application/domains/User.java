package com.projects.scheduler.application.domains;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class User {

	private Long id;

	private String name;

	private String phone;

	private String email;

	private String photo;

	private LocalDateTime creationDate;

	private LocalDateTime lastUpdateDate;

	private LocalDateTime lastLoginDate;

}
