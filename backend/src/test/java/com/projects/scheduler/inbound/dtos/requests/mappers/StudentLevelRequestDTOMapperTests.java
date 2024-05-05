package com.projects.scheduler.inbound.dtos.requests.mappers;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.mocks.StudentLevelMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentLevelRequestDTOMapperTests {

	@InjectMocks
	private StudentLevelRequestDTOMapper studentLevelRequestDTOMapper;

	@Test
	void fromDTO_shouldReturnNull() {
		StudentLevel actual = this.studentLevelRequestDTOMapper.fromDTO(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDTO_shouldReturnDomain() {
		StudentLevel expected = StudentLevelMocks.getStudentLevelDomain();

		StudentLevel actual = this.studentLevelRequestDTOMapper.fromDTO(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
