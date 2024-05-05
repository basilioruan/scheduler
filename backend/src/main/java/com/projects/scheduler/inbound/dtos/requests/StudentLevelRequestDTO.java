package com.projects.scheduler.inbound.dtos.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentLevelRequestDTO {

	private Long id;

	private String name;

	private String code;

	private String description;

}
