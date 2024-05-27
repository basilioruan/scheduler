package com.projects.scheduler.outbound.persistence.entities;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "student", schema = "DB_SCHEDULER")
@PrimaryKeyJoinColumn(name = "application_user_id")
@SuperBuilder
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

	@ManyToOne
	@JoinColumn(name = "student_teacher_id")
	@NotNull
	private TeacherEntity teacher;

	public StudentEntity() {
		super();
	}

}
