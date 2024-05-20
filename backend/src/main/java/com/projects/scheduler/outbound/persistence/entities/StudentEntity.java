package com.projects.scheduler.outbound.persistence.entities;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.ClassTypeIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "student", schema = "DB_SCHEDULER")
@PrimaryKeyJoinColumn(name = "application_user_id")
public class StudentEntity extends UserEntity {

	@ManyToOne
	@JoinColumn(name = "student_level_id")
	private StudentLevelEntity studentLevel;

	@Column(name = "student_class_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private ClassTypeIndicator classType;

	@Column(name = "student_reschedules")
	private int reschedules;

	@Builder
	public StudentEntity(Long id, @NotNull String name, @NotNull String phone, @NotNull String email, String photo,
						 @NotNull LocalDateTime creationDate, @NotNull LocalDateTime lastUpdateDate, LocalDateTime lastLoginDate,
						 StudentLevelEntity studentLevel, @NotNull ClassTypeIndicator classType, int reschedules) {
		super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
		this.studentLevel = studentLevel;
		this.classType = classType;
		this.reschedules = reschedules;
	}

}
