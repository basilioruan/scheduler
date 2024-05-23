package com.projects.scheduler.outbound.persistence.entities;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "teacher", schema = "DB_SCHEDULER")
@PrimaryKeyJoinColumn(name = "application_user_id")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class TeacherEntity extends UserEntity {

	@Column(name = "teacher_school_subject")
	@NotNull
	@Enumerated(EnumType.STRING)
	private SchoolSubjectIndicator schoolSubject;

}
