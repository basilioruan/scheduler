package com.projects.scheduler.inbound.dtos.responses;

import java.time.LocalDateTime;

import com.projects.scheduler.utils.enums.ScheduleStatusIndicator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduleResponseDTO {

	private Long id;

	private LocalDateTime dateTime;

	private PairIdLabelDTO teacher;

	private PairIdLabelDTO student;

	private ScheduleStatusIndicator status;

	private LocalDateTime creationDate;

	private LocalDateTime lastUpdateDate;

}
