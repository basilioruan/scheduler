package com.projects.scheduler.outbound.persistence.entities;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher", schema = "DB_SCHEDULER")
@PrimaryKeyJoinColumn(name = "application_user_id")
@EqualsAndHashCode(callSuper = true)
public class TeacherEntity extends UserEntity {

	@Column(name = "teacher_school_subject")
	@NotNull
	private SchoolSubjectIndicator schoolSubject;

	@Builder
	public TeacherEntity(Long id, String name, String phone, String email, String photo, LocalDateTime creationDate,
			LocalDateTime lastUpdateDate, LocalDateTime lastLoginDate, SchoolSubjectIndicator schoolSubject) {
		super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
		this.schoolSubject = schoolSubject;
	}

}
