package com.projects.scheduler.outbound.persistence.entities;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.ScheduleStatusIndicator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "schedule", schema = "DB_SCHEDULER")
public class ScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private Long id;

	@Column(name = "schedule_date")
	@NotNull
	private LocalDateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "schedule_teacher_id")
	@NotNull
	private TeacherEntity teacher;

	@ManyToOne
	@JoinColumn(name = "schedule_student_id")
	private StudentEntity student;

	@Column(name = "schedule_status")
	@NotNull
	private ScheduleStatusIndicator status;

	@Column(name = "schedule_creation_date")
	@NotNull
	private LocalDateTime creationDate;

	@Column(name = "schedule_last_update_date")
	@NotNull
	private LocalDateTime lastUpdateDate;

}
