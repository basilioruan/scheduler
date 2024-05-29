package com.projects.scheduler.mocks;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.outbound.persistence.entities.StudentEntity;
import com.projects.scheduler.utils.DefaultValues;
import com.projects.scheduler.utils.enums.ClassTypeIndicator;

public final class StudentMocks {

	private StudentMocks() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static Student getStudentDomain() {
		return Student.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.studentLevel(StudentLevelMocks.getStudentLevelDomain())
			.classType(ClassTypeIndicator.ONLINE)
			.reschedules(DefaultValues.INT_VALUE)
			.teacher(TeacherMocks.getTeacherDomain())
			.build();
	}

	public static StudentEntity getStudentEntity() {
		return StudentEntity.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.studentLevel(StudentLevelMocks.getStudentLevelEntity())
			.classType(ClassTypeIndicator.ONLINE)
			.reschedules(DefaultValues.INT_VALUE)
			.teacher(TeacherMocks.getTeacherEntity())
			.build();
	}

	public static StudentResponseDTO getStudentResponseDTO() {
		return StudentResponseDTO.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.studentLevel(StudentLevelMocks.getStudentLevelResponseDTO())
			.classType(ClassTypeIndicator.ONLINE)
			.reschedules(DefaultValues.INT_VALUE)
			.teacher(DefaultValues.PAIR_ID_LABEL_DTO)
			.build();
	}

	public static StudentRequestDTO getStudentRequestDTO() {
		return StudentRequestDTO.builder()
			.id(DefaultValues.LONG_VALUE)
			.name(DefaultValues.STRING_VALUE)
			.phone(DefaultValues.PHONE)
			.email(DefaultValues.EMAIL)
			.phone(DefaultValues.STRING_VALUE)
			.creationDate(DefaultValues.LOCAL_DATE_TIME)
			.lastUpdateDate(DefaultValues.LOCAL_DATE_TIME)
			.lastLoginDate(DefaultValues.LOCAL_DATE_TIME)
			.studentLevelId(DefaultValues.LONG_VALUE)
			.classType(ClassTypeIndicator.ONLINE)
			.reschedules(DefaultValues.INT_VALUE)
			.teacherId(DefaultValues.LONG_VALUE)
			.build();
	}

}
