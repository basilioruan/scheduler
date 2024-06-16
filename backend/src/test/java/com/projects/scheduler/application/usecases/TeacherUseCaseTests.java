package com.projects.scheduler.application.usecases;

import java.util.List;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.TeacherRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.TeacherResponseDTOMapper;
import com.projects.scheduler.mocks.TeacherMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeacherUseCaseTests {

	@InjectMocks
	private TeacherUseCase teacherUseCase;

	@Mock
	private TeacherOutPort teacherOutPort;

	@Mock
	private TeacherResponseDTOMapper teacherResponseDTOMapper;

	@Mock
	private TeacherRequestDTOMapper teacherRequestDTOMapper;

	@Test
	void findById_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.teacherUseCase.findById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findById_shouldReturnDomain() {
		TeacherResponseDTO expected = TeacherMocks.getTeacherResponseDTO();

		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());
		BDDMockito.when(this.teacherResponseDTOMapper.fromDomain(any(Teacher.class))).thenCallRealMethod();

		TeacherResponseDTO actual = this.teacherUseCase.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";
		BDDMockito.when(this.teacherOutPort.findAll()).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.teacherUseCase.findAll()).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findAll_shouldReturnDomain() {
		List<TeacherResponseDTO> expected = List.of(TeacherMocks.getTeacherResponseDTO());

		BDDMockito.when(this.teacherOutPort.findAll()).thenReturn(List.of(TeacherMocks.getTeacherDomain()));
		BDDMockito.when(this.teacherResponseDTOMapper.fromDomain(any(Teacher.class))).thenCallRealMethod();

		List<TeacherResponseDTO> actual = this.teacherUseCase.findAll();

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";
		TeacherRequestDTO requestDTO = TeacherMocks.getTeacherRequestDTO();
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());
		BDDMockito.when(this.teacherOutPort.save(any(Teacher.class))).thenThrow(new RuntimeException(errorMessage));
		BDDMockito.when(this.teacherRequestDTOMapper.fromDTO(any(TeacherRequestDTO.class))).thenCallRealMethod();

		assertThatThrownBy(() -> this.teacherUseCase.save(requestDTO)).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void save_shouldReturnResponseDTO() {
		TeacherResponseDTO expected = TeacherMocks.getTeacherResponseDTO();
		Teacher domain = TeacherMocks.getTeacherDomain();

		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(domain);
		BDDMockito.when(this.teacherOutPort.save(any(Teacher.class))).thenReturn(domain);
		BDDMockito.when(this.teacherRequestDTOMapper.fromDTO(any(TeacherRequestDTO.class))).thenCallRealMethod();
		BDDMockito.when(this.teacherResponseDTOMapper.fromDomain(any(Teacher.class))).thenCallRealMethod();

		TeacherResponseDTO actual = this.teacherUseCase.save(TeacherMocks.getTeacherRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void deleteById_shouldThrowSchedularRuntimeException() {
		String errorMessage = String.format("Teacher was not found for parameters {id=%s}", DefaultValues.LONG_VALUE);

		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(null);

		assertThatThrownBy(() -> this.teacherUseCase.deleteById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void deleteById_shouldDeleteTeacher() {
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());

		assertThatCode(() -> this.teacherUseCase.deleteById(DefaultValues.LONG_VALUE)).doesNotThrowAnyException();
		verify(this.teacherOutPort, times(1)).deleteById(anyLong());
	}

}
