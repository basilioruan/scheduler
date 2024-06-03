package com.projects.scheduler.mocks.utils;

import java.time.LocalDateTime;

import com.projects.scheduler.inbound.dtos.responses.PairIdLabelDTO;

public final class DefaultValues {

	public static Long LONG_VALUE = 1L;

	public static int INT_VALUE = 1;

	public static String STRING_VALUE = "TESTE";

	public static String PHONE = "77988888888";

	public static String EMAIL = "teste@test.com";

	public static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2024, 1, 1, 0, 0, 0);

	public static PairIdLabelDTO PAIR_ID_LABEL_DTO = PairIdLabelDTO.builder()
		.id(LONG_VALUE)
		.label(STRING_VALUE)
		.build();

	private DefaultValues() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

}
