package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentLevelInPort;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.mocks.StudentLevelMocks;
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
class StudentLevelControllerTests {

	@InjectMocks
	private StudentLevelController studentLevelController;

	@Mock
	private StudentLevelInPort studentLevelInPort;

	@Test
	void findById_shouldReturnNull() {
		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getBody()).isNull();
	}

	@Test
	void findById_shouldReturnResponseDTO() throws SchedularRuntimeException {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelInPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelResponseDTO());

		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findById_shouldReturnInternalServerError() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelInPort.findById(anyLong())).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void findAll_shouldReturnEmptyList() {
		ResponseEntity<List<StudentLevelResponseDTO>> actual = this.studentLevelController.findAll();

		assertThat(actual.getBody()).isEmpty();
	}

	@Test
	void findAll_shouldReturnResponseDTOList() throws SchedularRuntimeException {
		List<StudentLevelResponseDTO> expected = List.of(StudentLevelMocks.getStudentLevelResponseDTO());

		BDDMockito.when(this.studentLevelInPort.findAll())
			.thenReturn(List.of(StudentLevelMocks.getStudentLevelResponseDTO()));

		ResponseEntity<List<StudentLevelResponseDTO>> actual = this.studentLevelController.findAll();

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldReturnInternalServerError() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelInPort.findAll()).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<List<StudentLevelResponseDTO>> actual = this.studentLevelController.findAll();

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void save_shouldReturnNull() {
		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController
			.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual.getBody()).isNull();
	}

	@Test
	void save_shouldReturnResponseDTO() throws SchedularRuntimeException {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelInPort.save(any())).thenReturn(StudentLevelMocks.getStudentLevelResponseDTO());

		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController
			.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual.getBody()).isNotNull().usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldReturnInternalServerError() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelInPort.save(any())).thenThrow(new SchedularRuntimeException("error"));

		ResponseEntity<StudentLevelResponseDTO> actual = this.studentLevelController
			.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test
	void delete_shouldReturnSuccessfulMessage() {
		String expectedMessage = "Student level deleted!";

		ResponseEntity<String> reponse = this.studentLevelController.delete(DefaultValues.LONG_VALUE);

		assertThat(reponse.getBody()).isEqualTo(expectedMessage);
	}

	@Test
	void delete_shouldReturnInternalServerError() throws SchedularRuntimeException {
		Mockito.doThrow(new SchedularRuntimeException("error")).when(this.studentLevelInPort).deleteById(anyLong());
		ResponseEntity<String> reponse = this.studentLevelController.delete(DefaultValues.LONG_VALUE);

		assertThat(reponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
