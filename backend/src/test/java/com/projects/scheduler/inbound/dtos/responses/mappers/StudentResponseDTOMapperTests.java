package com.projects.scheduler.inbound.dtos.responses.mappers;

import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.mocks.StudentMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StudentResponseDTOMapperTests {

	@InjectMocks
	private StudentResponseDTOMapper studentResponseDTOMapper;

	@Mock
	private StudentLevelResponseDTOMapper studentLevelResponseDTOMapper;

	@Test
	void fromDTO_shouldReturnNull() {
		StudentResponseDTO actual = this.studentResponseDTOMapper.fromDomain(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDTO_shouldReturnDomain() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();

		BDDMockito.when(this.studentLevelResponseDTOMapper.fromDomain(any())).thenCallRealMethod();

		StudentResponseDTO actual = this.studentResponseDTOMapper.fromDomain(StudentMocks.getStudentDomain());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
