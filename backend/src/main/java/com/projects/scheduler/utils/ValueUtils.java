package com.projects.scheduler.utils;

import com.projects.scheduler.inbound.dtos.responses.PairIdLabelDTO;

public class ValueUtils {

	public static PairIdLabelDTO buildPairIdLabel(Long id, String label) {
		return PairIdLabelDTO.builder()
				.id(id)
				.label(label)
				.build();
	}

}
