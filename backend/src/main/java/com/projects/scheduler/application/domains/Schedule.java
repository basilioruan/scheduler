package com.projects.scheduler.application.domains;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.ScheduleStatusIndicator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Schedule {

	private Long id;

	private LocalDateTime dateTime;

	private Teacher teacher;

	private Student student;

	private ScheduleStatusIndicator status;

	private LocalDateTime creationDate;

	private LocalDateTime lastUpdateDate;

}
