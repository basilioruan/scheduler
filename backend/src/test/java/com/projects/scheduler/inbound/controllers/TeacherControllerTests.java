package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.TeacherInPort;
import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import com.projects.scheduler.mocks.TeacherMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTests {

	@InjectMocks
	private TeacherController teacherController;

	@Mock
	private TeacherInPort teacherInPort;

	@Test
	void findById_shouldReturnResponseDTO() {
		BDDMockito.when(this.teacherInPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherResponseDTO());

		ResponseEntity<TeacherResponseDTO> actual = this.teacherController.findById(DefaultValues.LONG_VALUE);

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actual.hasBody()).isTrue();
		assertThat(actual.getBody()).usingRecursiveComparison().isEqualTo(TeacherMocks.getTeacherResponseDTO());
	}

	@Test
	void findAll_shouldReturnListOfResponseDTO() {
		BDDMockito.when(this.teacherInPort.findAll()).thenReturn(List.of(TeacherMocks.getTeacherResponseDTO()));

		ResponseEntity<List<TeacherResponseDTO>> actual = this.teacherController.findAll();

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actual.hasBody()).isTrue();
		assertThat(actual.getBody()).usingRecursiveComparison()
			.isEqualTo(List.of(TeacherMocks.getTeacherResponseDTO()));
	}

	@Test
	void save_shouldReturnSavedResponseDTO() {
		BDDMockito.when(this.teacherInPort.save(any(TeacherRequestDTO.class)))
			.thenReturn(TeacherMocks.getTeacherResponseDTO());

		ResponseEntity<TeacherResponseDTO> actual = this.teacherController.save(TeacherMocks.getTeacherRequestDTO());

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actual.hasBody()).isTrue();
		assertThat(actual.getBody()).usingRecursiveComparison().isEqualTo(TeacherMocks.getTeacherResponseDTO());
	}

	@Test
	void delete_shouldDeleteTeacher() {
		String expectedMessage = "Teacher deleted!";
		ResponseEntity<String> actual = this.teacherController.delete(DefaultValues.LONG_VALUE);

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(actual.hasBody()).isTrue();
		assertThat(actual.getBody()).isEqualTo(expectedMessage);
	}

}
