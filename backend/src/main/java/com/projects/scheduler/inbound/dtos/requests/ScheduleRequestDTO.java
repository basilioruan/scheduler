package com.projects.scheduler.inbound.dtos.requests;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.ScheduleStatusIndicator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleRequestDTO {

	private Long id;

	private LocalDateTime dateTime;

	private Long teacherId;

	private Long studentId;

	private ScheduleStatusIndicator status;

}
