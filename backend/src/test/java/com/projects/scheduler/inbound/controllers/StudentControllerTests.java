package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentInPort;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.mocks.StudentMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class StudentControllerTests {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentInPort studentInPort;

	@Test
	void findById_shouldReturnResponseDTO() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();

		BDDMockito.when(this.studentInPort.findById(anyLong())).thenReturn(StudentMocks.getStudentResponseDTO());

		ResponseEntity<StudentResponseDTO> actual = this.studentController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldReturnEmptyList() {
		ResponseEntity<List<StudentResponseDTO>> actual = this.studentController.findAll();

		assertThat(actual.getBody()).isEmpty();
	}

	@Test
	void findAll_shouldReturnResponseDTOList() {
		List<StudentResponseDTO> expected = List.of(StudentMocks.getStudentResponseDTO());

		BDDMockito.when(this.studentInPort.findAll()).thenReturn(List.of(StudentMocks.getStudentResponseDTO()));

		ResponseEntity<List<StudentResponseDTO>> actual = this.studentController.findAll();

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldReturnNull() {
		ResponseEntity<StudentResponseDTO> actual = this.studentController.save(StudentMocks.getStudentRequestDTO());

		assertThat(actual.getBody()).isNull();
	}

	@Test
	void save_shouldReturnResponseDTO() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();

		BDDMockito.when(this.studentInPort.save(any())).thenReturn(StudentMocks.getStudentResponseDTO());

		ResponseEntity<StudentResponseDTO> actual = this.studentController.save(StudentMocks.getStudentRequestDTO());

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void delete_shouldReturnSuccessfulMessage() {
		String expectedMessage = "Student deleted!";

		ResponseEntity<String> reponse = this.studentController.delete(DefaultValues.LONG_VALUE);

		assertThat(reponse.getBody()).isEqualTo(expectedMessage);
	}

}
