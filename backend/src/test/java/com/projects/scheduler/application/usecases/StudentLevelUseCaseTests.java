package com.projects.scheduler.application.usecases;

import java.util.Collections;
import java.util.List;

import com.projects.scheduler.application.ports.outbound.StudentLevelOutPort;
import com.projects.scheduler.inbound.dtos.requests.mappers.StudentLevelRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.StudentLevelResponseDTOMapper;
import com.projects.scheduler.mocks.StudentLevelMocks;
import com.projects.scheduler.utils.DefaultValues;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
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
class StudentLevelUseCaseTests {

	@InjectMocks
	private StudentLevelUseCase studentLevelUseCase;

	@Mock
	private StudentLevelOutPort studentLevelOutPort;

	@Mock
	private StudentLevelRequestDTOMapper studentLevelRequestDTOMapper;

	@Mock
	private StudentLevelResponseDTOMapper studentLevelResponseDTOMapper;

	@Test
	void findById_shouldReturnNull() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelOutPort.findById(anyLong())).thenReturn(null);

		StudentLevelResponseDTO actual = this.studentLevelUseCase.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNull();
	}

	@Test
	void findById_shouldReturnResponseDTO() throws SchedularRuntimeException {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelOutPort.findById(anyLong()))
			.thenReturn(StudentLevelMocks.getStudentLevelDomain());
		BDDMockito.when(this.studentLevelResponseDTOMapper.fromDomain(any())).thenCallRealMethod();

		StudentLevelResponseDTO actual = this.studentLevelUseCase.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldReturnEmptyList() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelOutPort.findAll()).thenReturn(Collections.emptyList());

		List<StudentLevelResponseDTO> actual = this.studentLevelUseCase.findAll();

		assertThat(actual).isEmpty();
	}

	@Test
	void findAll_shouldReturnResponseDTOList() throws SchedularRuntimeException {
		List<StudentLevelResponseDTO> expected = List.of(StudentLevelMocks.getStudentLevelResponseDTO());

		BDDMockito.when(this.studentLevelOutPort.findAll())
			.thenReturn(List.of(StudentLevelMocks.getStudentLevelDomain()));
		BDDMockito.when(this.studentLevelResponseDTOMapper.fromDomain(any())).thenCallRealMethod();

		List<StudentLevelResponseDTO> actual = this.studentLevelUseCase.findAll();

		assertThat(actual).isNotEmpty();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldReturnNull() throws SchedularRuntimeException {
		BDDMockito.when(this.studentLevelOutPort.save(any())).thenReturn(null);

		StudentLevelResponseDTO actual = this.studentLevelUseCase.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual).isNull();
		verify(this.studentLevelRequestDTOMapper, times(1)).fromDTO(any());
	}

	@Test
	void save_shouldReturnResponseDTO() throws SchedularRuntimeException {
		StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

		BDDMockito.when(this.studentLevelOutPort.save(any())).thenReturn(StudentLevelMocks.getStudentLevelDomain());
		BDDMockito.when(this.studentLevelResponseDTOMapper.fromDomain(any())).thenCallRealMethod();

		StudentLevelResponseDTO actual = this.studentLevelUseCase.save(StudentLevelMocks.getStudentLevelRequestDTO());

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
		verify(this.studentLevelRequestDTOMapper, times(1)).fromDTO(any());
	}

	@Test
	void deleteById_shouldDelete() throws SchedularRuntimeException {
		this.studentLevelUseCase.deleteById(DefaultValues.LONG_VALUE);

		verify(this.studentLevelOutPort, times(1)).deleteById(anyLong());
	}

}
