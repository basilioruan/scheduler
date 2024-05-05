package com.projects.scheduler.inbound.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentLevelResponseDTO {

	private Long id;

	private String name;

	private String code;

	private String description;

}
