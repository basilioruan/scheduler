package com.projects.scheduler.outbound.adapters;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.projects.scheduler.application.domains.Student;
import com.projects.scheduler.mocks.StudentMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.outbound.mappers.StudentEntityMapper;
import com.projects.scheduler.outbound.persistence.repositories.StudentRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.persistence.EntityNotFoundException;
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
class StudentAdapterTests {

	@InjectMocks
	private StudentAdapter studentAdapter;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentEntityMapper studentEntityMapper;

	@Test
	void findById_shouldReturnDomain() {
		Student expected = StudentMocks.getStudentDomain();

		BDDMockito.when(this.studentRepository.findById(anyLong()))
			.thenReturn(Optional.of(StudentMocks.getStudentEntity()));
		BDDMockito.when(this.studentEntityMapper.fromEntity(any())).thenReturn(expected);

		Student actual = this.studentAdapter.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findById_shouldReturnNull_whenNotFindObject() {
		BDDMockito.when(this.studentRepository.findById(anyLong())).thenReturn(Optional.empty());

		Student actual = this.studentAdapter.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).isNull();
	}

	@Test
	void findById_shouldThrowAnException() {
		String errorMessage = "error";

		BDDMockito.when(this.studentRepository.findById(anyLong())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.studentAdapter.findById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findAll_shouldReturnEmptyList() {
		BDDMockito.when(this.studentRepository.findAll()).thenReturn(Collections.emptyList());

		List<Student> actual = this.studentAdapter.findAll();

		assertThat(actual).isEmpty();
	}

	@Test
	void findAll_shouldReturnDomainList() {
		List<Student> expected = List.of(StudentMocks.getStudentDomain());

		BDDMockito.when(this.studentRepository.findAll()).thenReturn(List.of(StudentMocks.getStudentEntity()));
		BDDMockito.when(this.studentEntityMapper.fromEntity(any())).thenReturn(StudentMocks.getStudentDomain());

		List<Student> actual = this.studentAdapter.findAll();

		assertThat(actual).isNotEmpty();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldThrowAnException() {
		String errorMessage = "error";

		BDDMockito.when(this.studentRepository.findAll()).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.studentAdapter.findAll()).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void save_shouldReturnNull() {
		Student actual = this.studentAdapter.save(null);

		assertThat(actual).isNull();
	}

	@Test
	void save_shouldReturnSavedDomain() {
		Student expected = StudentMocks.getStudentDomain();

		BDDMockito.when(this.studentRepository.save(any())).thenReturn(StudentMocks.getStudentEntity());
		BDDMockito.when(this.studentEntityMapper.fromEntity(any())).thenReturn(expected);

		Student actual = this.studentAdapter.save(StudentMocks.getStudentDomain());

		assertThat(actual).isNotNull();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldThrowAnException() {
		String errorMessage = "error";
		Student student = StudentMocks.getStudentDomain();

		BDDMockito.when(this.studentRepository.save(any())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.studentAdapter.save(student)).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void deleteById_shouldCallRepositoryDelete() {
		BDDMockito.when(this.studentRepository.findById(anyLong()))
			.thenReturn(Optional.of(StudentMocks.getStudentEntity()));

		this.studentAdapter.deleteById(DefaultValues.LONG_VALUE);

		verify(this.studentRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void deleteById_shouldThrowAnException_whenNotFindStudent() {
		String errorMessage = "Student not found";

		BDDMockito.when(this.studentRepository.findById(anyLong()))
			.thenThrow(new EntityNotFoundException(errorMessage));

		assertThatThrownBy(() -> this.studentAdapter.deleteById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void deleteById_shouldThrowAnException_whenTryToDelete() {
		String errorMessage = "error";

		BDDMockito.when(this.studentRepository.findById(anyLong()))
			.thenReturn(Optional.of(StudentMocks.getStudentEntity()));
		BDDMockito.doThrow(new RuntimeException(errorMessage)).when(this.studentRepository).deleteById(anyLong());

		assertThatThrownBy(() -> this.studentAdapter.deleteById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

}
