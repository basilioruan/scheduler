package com.projects.scheduler.outbound.mappers;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.mocks.ScheduleMocks;
import com.projects.scheduler.mocks.StudentMocks;
import com.projects.scheduler.mocks.TeacherMocks;
import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;
import com.projects.scheduler.outbound.persistence.entities.StudentEntity;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ScheduleEntityMapperTests {

	@InjectMocks
	private ScheduleEntityMapper scheduleEntityMapper;

	@Mock
	private StudentEntityMapper studentEntityMapper;

	@Mock
	private TeacherEntityMapper teacherEntityMapper;

	@Test
	void fromEntity_shouldBeNull() {
		Schedule actual = this.scheduleEntityMapper.fromEntity(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromEntity_shouldReturnDomain() {
		ScheduleEntity entity = ScheduleMocks.getScheduleEntity();
		Schedule expected = ScheduleMocks.getScheduleDomain();

		BDDMockito.when(this.studentEntityMapper.fromEntity(any(StudentEntity.class)))
			.thenReturn(StudentMocks.getStudentDomain());
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any(TeacherEntity.class)))
			.thenReturn(TeacherMocks.getTeacherDomain());

		Schedule actual = this.scheduleEntityMapper.fromEntity(entity);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void fromDomain_shouldBeNull() {
		ScheduleEntity actual = this.scheduleEntityMapper.fromDomain(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDomain_shouldReturnEntity() {
		Schedule domain = ScheduleMocks.getScheduleDomain();
		ScheduleEntity expected = ScheduleMocks.getScheduleEntity();

		BDDMockito.when(this.studentEntityMapper.fromDomain(any(Student.class)))
			.thenReturn(StudentMocks.getStudentEntity());
		BDDMockito.when(this.teacherEntityMapper.fromDomain(any(Teacher.class)))
			.thenReturn(TeacherMocks.getTeacherEntity());

		ScheduleEntity actual = this.scheduleEntityMapper.fromDomain(domain);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
