package com.projects.scheduler.mocks;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;
import com.projects.scheduler.utils.DefaultValues;
import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;

public final class TeacherMocks {

	private TeacherMocks() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static Teacher getTeacherDomain() {
		return Teacher.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.schoolSubject(SchoolSubjectIndicator.ENGLISH)
			.build();
	}

	public static TeacherEntity getTeacherEntity() {
		return TeacherEntity.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.schoolSubject(SchoolSubjectIndicator.ENGLISH)
			.build();
	}

}
