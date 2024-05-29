package com.projects.scheduler.utils;

import com.projects.scheduler.inbound.dtos.responses.PairIdLabelDTO;

public final class ValueUtils {

	private ValueUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static PairIdLabelDTO buildPairIdLabel(Long id, String label) {
		return PairIdLabelDTO.builder().id(id).label(label).build();
	}

}
