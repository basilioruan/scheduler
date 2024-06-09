package com.projects.scheduler.application.usecases;

import java.util.List;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.application.ports.outbound.StudentLevelOutPort;
import com.projects.scheduler.application.ports.outbound.StudentOutPort;
import com.projects.scheduler.application.ports.outbound.TeacherOutPort;
import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.StudentRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.StudentResponseDTOMapper;
import com.projects.scheduler.mocks.StudentLevelMocks;
import com.projects.scheduler.mocks.StudentMocks;
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
class StudentUseCaseTests {

	@InjectMocks
	private StudentUseCase studentUseCase;

	@Mock
	private StudentOutPort studentOutPort;

	@Mock
	private StudentLevelOutPort studentLevelOutPort;

	@Mock
	private TeacherOutPort teacherOutPort;

	@Mock
	private StudentRequestDTOMapper studentRequestDTOMapper;

	@Mock
	private StudentResponseDTOMapper studentResponseDTOMapper;

	@Test
	void findById_shouldReturnDTO() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();

		BDDMockito.when(this.studentOutPort.findById(anyLong())).thenReturn(StudentMocks.getStudentDomain());
		BDDMockito.when(this.studentResponseDTOMapper.fromDomain(any()))
			.thenReturn(StudentMocks.getStudentResponseDTO());

		StudentResponseDTO actual = this.studentUseCase.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findALl_shouldReturnDTOList() {
		List<StudentResponseDTO> expected = List.of(StudentMocks.getStudentResponseDTO());

		BDDMockito.when(this.studentOutPort.findAll()).thenReturn(List.of(StudentMocks.getStudentDomain()));
		BDDMockito.when(this.studentResponseDTOMapper.fromDomain(any()))
			.thenReturn(StudentMocks.getStudentResponseDTO());

		List<StudentResponseDTO> actual = this.studentUseCase.findAll();

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldThrowRuntimeException() {
		String expectedMessage = String.format("Student was not found for parameters {id=%s}",
				DefaultValues.LONG_VALUE);
		StudentRequestDTO requestDTO = StudentMocks.getStudentRequestDTO();

		BDDMockito.when(this.studentRequestDTOMapper.fromDTO(any()))
			.thenReturn(StudentMocks.getStudentDomainFromRequestDTO());
		BDDMockito.when(this.studentLevelOutPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelDomain());
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());
		BDDMockito.when(this.studentOutPort.findById(anyLong())).thenReturn(null);

		assertThatThrownBy(() -> this.studentUseCase.save(requestDTO)).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(expectedMessage);
	}

	@Test
	void save_shouldReturnResponseDTO_whenCreating() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();
		Student studentFromRequest = StudentMocks.getStudentDomainFromRequestDTO();
		studentFromRequest.setId(null);

		BDDMockito.when(this.studentRequestDTOMapper.fromDTO(any())).thenReturn(studentFromRequest);
		BDDMockito.when(this.studentLevelOutPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelDomain());
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());
		BDDMockito.when(this.studentOutPort.save(any())).thenReturn(StudentMocks.getStudentDomain());
		BDDMockito.when(this.studentResponseDTOMapper.fromDomain(any()))
			.thenReturn(StudentMocks.getStudentResponseDTO());

		StudentResponseDTO actual = this.studentUseCase.save(StudentMocks.getStudentRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
		verify(this.studentOutPort, times(1)).save(any());
	}

	@Test
	void save_shouldReturnResponseDTO_whenUpdating() {
		StudentResponseDTO expected = StudentMocks.getStudentResponseDTO();

		BDDMockito.when(this.studentRequestDTOMapper.fromDTO(any()))
			.thenReturn(StudentMocks.getStudentDomainFromRequestDTO());
		BDDMockito.when(this.studentLevelOutPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelDomain());
		BDDMockito.when(this.teacherOutPort.findById(anyLong())).thenReturn(TeacherMocks.getTeacherDomain());
		BDDMockito.when(this.studentOutPort.save(any())).thenReturn(StudentMocks.getStudentDomain());
		BDDMockito.when(this.studentResponseDTOMapper.fromDomain(any()))
			.thenReturn(StudentMocks.getStudentResponseDTO());
		BDDMockito.when(this.studentOutPort.findById(anyLong())).thenReturn(StudentMocks.getStudentDomain());

		StudentResponseDTO actual = this.studentUseCase.save(StudentMocks.getStudentRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
		verify(this.studentOutPort, times(1)).save(any());
	}

	@Test
	void delete_shouldCallDeletePort() {
		BDDMockito.when(this.studentOutPort.findById(anyLong())).thenReturn(StudentMocks.getStudentDomain());

		assertThatCode(() -> this.studentUseCase.deleteById(DefaultValues.LONG_VALUE)).doesNotThrowAnyException();

		verify(this.studentOutPort, times(1)).deleteById(anyLong());
	}

	@Test
	void deleteById_shouldThrowAnException_whenTryToDelete() {
		Long id = DefaultValues.LONG_VALUE;
		String errorMessage = String.format("Student was not found for parameters {id=%s}", id);

		BDDMockito.when(this.studentOutPort.findById(anyLong())).thenReturn(null);

		assertThatThrownBy(() -> this.studentUseCase.deleteById(id)).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

}
