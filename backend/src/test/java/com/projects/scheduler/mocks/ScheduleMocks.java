package com.projects.scheduler.mocks;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;
import com.projects.scheduler.utils.enums.ScheduleStatusIndicator;

public final class ScheduleMocks {

	private ScheduleMocks() {
		throw new UnsupportedOperationException("This is an utility class and cannot be instantiated");
	}

	public static Schedule getScheduleDomain() {
		return Schedule.builder()
			.id(DefaultValues.LONG_VALUE)
			.dateTime(DefaultValues.LOCAL_DATE_TIME)
			.teacher(TeacherMocks.getTeacherDomain())
			.student(StudentMocks.getStudentDomain())
			.status(ScheduleStatusIndicator.OPEN)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.build();
	}

	public static ScheduleEntity getScheduleEntity() {
		return ScheduleEntity.builder()
			.id(DefaultValues.LONG_VALUE)
			.dateTime(DefaultValues.LOCAL_DATE_TIME)
			.teacher(TeacherMocks.getTeacherEntity())
			.student(StudentMocks.getStudentEntity())
			.status(ScheduleStatusIndicator.OPEN)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.build();
	}

}
