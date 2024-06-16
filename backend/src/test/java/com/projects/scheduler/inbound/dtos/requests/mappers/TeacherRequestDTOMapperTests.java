package com.projects.scheduler.inbound.dtos.requests.mappers;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.mocks.TeacherMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TeacherRequestDTOMapperTests {

	@InjectMocks
	private TeacherRequestDTOMapper teacherRequestDTOMapper;

	@Test
	void fromDTO_shouldReturnDomain() {
		Teacher expected = TeacherMocks.getTeacherDomainFromRequestDTO();

		Teacher actual = this.teacherRequestDTOMapper.fromDTO(TeacherMocks.getTeacherRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void fromDTO_shouldReturnNull_whenDTOIsNull() {
		Teacher actual = this.teacherRequestDTOMapper.fromDTO(null);

		assertThat(actual).isNull();
	}

}
