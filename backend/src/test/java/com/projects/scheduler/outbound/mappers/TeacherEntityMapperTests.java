package com.projects.scheduler.outbound.mappers;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.mocks.TeacherMocks;
import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TeacherEntityMapperTests {

	@InjectMocks
	private TeacherEntityMapper teacherEntityMapper;

	@Test
	void fromDomain_shouldReturnNull_whenEntityIsNull() {
		TeacherEntity actual = this.teacherEntityMapper.fromDomain(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDomain_shouldReturnEntity() {
		TeacherEntity expected = TeacherMocks.getTeacherEntity();

		TeacherEntity actual = this.teacherEntityMapper.fromDomain(TeacherMocks.getTeacherDomain());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void fromEntity_shouldReturnNull_whenDomainIsNull() {
		Teacher actual = this.teacherEntityMapper.fromEntity(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromEntity_shouldReturnDomain() {
		Teacher expected = TeacherMocks.getTeacherDomain();

		Teacher actual = this.teacherEntityMapper.fromEntity(TeacherMocks.getTeacherEntity());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
