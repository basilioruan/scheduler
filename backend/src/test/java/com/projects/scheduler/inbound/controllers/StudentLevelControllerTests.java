package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentLevelInPort;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.mocks.StudentLevelMocks;
import com.projects.scheduler.utils.DefaultValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentLevelControllerTests {

	@InjectMocks
	private StudentLevelController studentLevelController;

	@Mock
	private StudentLevelInPort studentLevelInPort;

	@Test
	void findById_shouldReturnNull() {
		StudentLevelResponseDTO actual = this.studentLevelController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNull();
	}

	@Test
	void findById_shouldReturnResponseDTO() {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelInPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelResponseDTO());

		StudentLevelResponseDTO actual = this.studentLevelController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldReturnEmptyList() {
		List<StudentLevelResponseDTO> actual = this.studentLevelController.findAll();

		assertThat(actual).isEmpty();
	}

	@Test
	void findAll_shouldReturnResponseDTOList() {
		List<StudentLevelResponseDTO> expected = List.of(StudentLevelMocks.getStudentLevelResponseDTO());

		BDDMockito.when(this.studentLevelInPort.findAll())
			.thenReturn(List.of(StudentLevelMocks.getStudentLevelResponseDTO()));

		List<StudentLevelResponseDTO> actual = this.studentLevelController.findAll();

		assertThat(actual).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldReturnNull() {
		StudentLevelResponseDTO actual = this.studentLevelController
			.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual).isNull();
	}

	@Test
	void save_shouldReturnResponseDTO() {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelInPort.save(any())).thenReturn(StudentLevelMocks.getStudentLevelResponseDTO());

		StudentLevelResponseDTO actual = this.studentLevelController
			.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void delete_shouldCallDeletePort() {
		this.studentLevelController.delete(DefaultValues.LONG_VALUE);

		verify(this.studentLevelInPort, times(1)).deleteById(anyLong());
	}

}
