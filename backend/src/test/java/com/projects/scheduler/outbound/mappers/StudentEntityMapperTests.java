package com.projects.scheduler.outbound.mappers;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.mocks.StudentMocks;
import com.projects.scheduler.outbound.persistence.entities.StudentEntity;
import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;
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
class StudentEntityMapperTests {

	@InjectMocks
	private StudentEntityMapper studentEntityMapper;

	@Mock
	private StudentLevelEntityMapper studentLevelEntityMapper;

	@Mock
	private TeacherEntityMapper teacherEntityMapper;

	@Test
	void fromEntity_shouldBeNull() {
		Student actual = this.studentEntityMapper.fromEntity(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromEntity_shouldReturnDomain() {
		StudentEntity entity = StudentMocks.getStudentEntity();
		Student expected = StudentMocks.getStudentDomain();

		BDDMockito.when(this.studentLevelEntityMapper.fromEntity(any(StudentLevelEntity.class))).thenCallRealMethod();
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any(TeacherEntity.class))).thenCallRealMethod();

		Student actual = this.studentEntityMapper.fromEntity(entity);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void fromDomain_shouldBeNull() {
		StudentEntity actual = this.studentEntityMapper.fromDomain(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDomain_shouldReturnEntity() {
		Student domain = StudentMocks.getStudentDomain();
		StudentEntity expected = StudentMocks.getStudentEntity();

		BDDMockito.when(this.studentLevelEntityMapper.fromDomain(any(StudentLevel.class))).thenCallRealMethod();
		BDDMockito.when(this.teacherEntityMapper.fromDomain(any(Teacher.class))).thenCallRealMethod();

		StudentEntity actual = this.studentEntityMapper.fromDomain(domain);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
