package com.projects.scheduler.inbound.dtos.requests.mappers;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.mocks.StudentMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentRequestDTOMapperTests {

	@InjectMocks
	private StudentRequestDTOMapper studentRequestDTOMapper;

	@Test
	void fromDTO_shouldReturnNull() {
		Student actual = this.studentRequestDTOMapper.fromDTO(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDTO_shouldReturnDomain() {
		Student expected = StudentMocks.getStudentDomainFromRequestDTO();

		Student actual = this.studentRequestDTOMapper.fromDTO(StudentMocks.getStudentRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
