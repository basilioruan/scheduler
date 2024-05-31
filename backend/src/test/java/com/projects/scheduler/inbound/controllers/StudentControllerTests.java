package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentInPort;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.mocks.StudentMocks;
import com.projects.scheduler.utils.DefaultValues;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
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
	void findById_shouldReturnInternalServerError() {
		BDDMockito.when(this.studentInPort.findById(anyLong())).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<StudentResponseDTO> actual = this.studentController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
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
	void findAll_shouldReturnInternalServerError() {
		BDDMockito.when(this.studentInPort.findAll()).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<List<StudentResponseDTO>> actual = this.studentController.findAll();

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
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
	void save_shouldReturnInternalServerError() {
		BDDMockito.when(this.studentInPort.save(any())).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<StudentResponseDTO> actual = this.studentController.save(StudentMocks.getStudentRequestDTO());

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void delete_shouldReturnSuccessfulMessage() {
		String expectedMessage = "Student deleted!";

		ResponseEntity<String> reponse = this.studentController.delete(DefaultValues.LONG_VALUE);

		assertThat(reponse.getBody()).isEqualTo(expectedMessage);
	}

	@Test
	void delete_shouldReturnInternalServerError() {
		Mockito.doThrow(new SchedularRuntimeException("error")).when(this.studentInPort).deleteById(anyLong());
		ResponseEntity<String> reponse = this.studentController.delete(DefaultValues.LONG_VALUE);

		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
