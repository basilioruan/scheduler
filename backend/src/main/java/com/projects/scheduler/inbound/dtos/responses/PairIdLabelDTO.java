package com.projects.scheduler.inbound.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PairIdLabelDTO {

	private Long id;

	private String label;

}
