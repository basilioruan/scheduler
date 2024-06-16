package com.projects.scheduler.inbound.dtos.responses.mappers;

import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import com.projects.scheduler.mocks.TeacherMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TeacherResponseDTOMapperTests {

	@InjectMocks
	private TeacherResponseDTOMapper teacherResponseDTOMapper;

	@Test
	void fromDomain_shouldReturnNull_whenDomainIsNull() {
		TeacherResponseDTO actual = this.teacherResponseDTOMapper.fromDomain(null);

		assertThat(actual).isNull();
	}

	@Test
	void fromDomain_shouldReturnDTO() {
		TeacherResponseDTO expected = TeacherMocks.getTeacherResponseDTO();

		TeacherResponseDTO actual = this.teacherResponseDTOMapper.fromDomain(TeacherMocks.getTeacherDomain());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

}
