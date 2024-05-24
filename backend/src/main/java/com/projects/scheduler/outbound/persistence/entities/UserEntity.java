package com.projects.scheduler.outbound.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@Table(name = "application_user", schema = "DB_SCHEDULER")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_user_id")
	private Long id;

	@Column(name = "application_user_name")
	@NotNull
	private String name;

	@Column(name = "application_user_phone")
	@NotNull
	private String phone;

	@Column(name = "application_user_email")
	@NotNull
	private String email;

	@Column(name = "application_user_photo")
	private String photo;

	@Column(name = "application_user_creation_date")
	@NotNull
	private LocalDateTime creationDate;

	@Column(name = "application_user_last_update_date")
	@NotNull
	private LocalDateTime lastUpdateDate;

	@Column(name = "application_user_last_login_date")
	private LocalDateTime lastLoginDate;

	public UserEntity() {
	}

}
