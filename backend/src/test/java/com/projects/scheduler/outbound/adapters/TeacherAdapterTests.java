package com.projects.scheduler.outbound.adapters;

import java.util.List;
import java.util.Optional;

import com.projects.scheduler.application.domains.Teacher;
import com.projects.scheduler.mocks.TeacherMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.outbound.mappers.TeacherEntityMapper;
import com.projects.scheduler.outbound.persistence.repositories.TeacherRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeacherAdapterTests {

	@InjectMocks
	private TeacherAdapter teacherAdapter;

	@Mock
	private TeacherRepository teacherRepository;

	@Mock
	private TeacherEntityMapper teacherEntityMapper;

	@Test
	void findById_shouldReturnNull_whenNotFindTeacher() {
		BDDMockito.when(this.teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any())).thenCallRealMethod();

		Teacher actual = this.teacherAdapter.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNull();
	}

	@Test
	void findById_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.when(this.teacherRepository.findById(anyLong())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.teacherAdapter.findById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findById_shouldReturnTeacherDomain() {
		Teacher expected = TeacherMocks.getTeacherDomain();

		BDDMockito.when(this.teacherRepository.findById(anyLong()))
			.thenReturn(Optional.of(TeacherMocks.getTeacherEntity()));
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any())).thenCallRealMethod();

		Teacher actual = this.teacherAdapter.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldReturnTeacherDomainList() {
		List<Teacher> expected = List.of(TeacherMocks.getTeacherDomain());

		BDDMockito.when(this.teacherRepository.findAll()).thenReturn(List.of(TeacherMocks.getTeacherEntity()));
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any())).thenCallRealMethod();

		List<Teacher> actual = this.teacherAdapter.findAll();

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.when(this.teacherRepository.findAll()).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.teacherAdapter.findAll()).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void save_shouldSaveTeacher() {
		Teacher expected = TeacherMocks.getTeacherDomain();

		BDDMockito.when(this.teacherEntityMapper.fromDomain(any())).thenCallRealMethod();
		BDDMockito.when(this.teacherRepository.save(any())).thenReturn(TeacherMocks.getTeacherEntity());
		BDDMockito.when(this.teacherEntityMapper.fromEntity(any())).thenCallRealMethod();

		Teacher actual = this.teacherAdapter.save(TeacherMocks.getTeacherDomainFromRequestDTO());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";
		Teacher teacherFromRequestDTO = TeacherMocks.getTeacherDomainFromRequestDTO();

		BDDMockito.when(this.teacherEntityMapper.fromDomain(any())).thenCallRealMethod();
		BDDMockito.when(this.teacherRepository.save(any())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.teacherAdapter.save(teacherFromRequestDTO))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void deleteById_shouldDeleteTeacher() {
		this.teacherAdapter.deleteById(DefaultValues.LONG_VALUE);

		verify(this.teacherRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void deleteById_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.doThrow(new RuntimeException(errorMessage)).when(this.teacherRepository).deleteById(anyLong());

		assertThatThrownBy(() -> this.teacherAdapter.deleteById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

}
