package com.projects.scheduler.outbound.adapters;

import java.util.List;
import java.util.Optional;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.mocks.ScheduleMocks;
import com.projects.scheduler.mocks.utils.DefaultValues;
import com.projects.scheduler.outbound.mappers.ScheduleEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;
import com.projects.scheduler.outbound.persistence.repositories.ScheduleRepository;
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
class ScheduleAdapterTests {

	@InjectMocks
	private ScheduleAdapter scheduleAdapter;

	@Mock
	private ScheduleEntityMapper scheduleEntityMapper;

	@Mock
	private ScheduleRepository scheduleRepository;

	@Test
	void findById_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.when(this.scheduleRepository.findById(anyLong())).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.scheduleAdapter.findById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findById_shouldReturnDomain() {
		Schedule expected = ScheduleMocks.getScheduleDomain();

		BDDMockito.when(this.scheduleRepository.findById(anyLong()))
			.thenReturn(Optional.of(ScheduleMocks.getScheduleEntity()));
		BDDMockito.when(this.scheduleEntityMapper.fromEntity(any(ScheduleEntity.class)))
			.thenReturn(ScheduleMocks.getScheduleDomain());

		Schedule actual = this.scheduleAdapter.findById(DefaultValues.LONG_VALUE);

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void findAll_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.when(this.scheduleRepository.findAll()).thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.scheduleAdapter.findAll()).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void findAll_shouldReturnDomainList() {
		List<Schedule> expected = List.of(ScheduleMocks.getScheduleDomain());

		BDDMockito.when(this.scheduleRepository.findAll()).thenReturn(List.of(ScheduleMocks.getScheduleEntity()));
		BDDMockito.when(this.scheduleEntityMapper.fromEntity(any(ScheduleEntity.class)))
			.thenReturn(ScheduleMocks.getScheduleDomain());

		List<Schedule> actual = this.scheduleAdapter.findAll();

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void save_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";
		Schedule schedule = ScheduleMocks.getScheduleDomain();

		BDDMockito.when(this.scheduleEntityMapper.fromDomain(any(Schedule.class)))
			.thenReturn(ScheduleMocks.getScheduleEntity());
		BDDMockito.when(this.scheduleRepository.save(any(ScheduleEntity.class)))
			.thenThrow(new RuntimeException(errorMessage));

		assertThatThrownBy(() -> this.scheduleAdapter.save(schedule)).isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void save_shouldReturnDomain() {
		Schedule expected = ScheduleMocks.getScheduleDomain();

		BDDMockito.when(this.scheduleEntityMapper.fromDomain(any(Schedule.class)))
			.thenReturn(ScheduleMocks.getScheduleEntity());
		BDDMockito.when(this.scheduleRepository.save(any(ScheduleEntity.class)))
			.thenReturn(ScheduleMocks.getScheduleEntity());
		BDDMockito.when(this.scheduleEntityMapper.fromEntity(any(ScheduleEntity.class)))
			.thenReturn(ScheduleMocks.getScheduleDomain());

		Schedule actual = this.scheduleAdapter.save(ScheduleMocks.getScheduleDomain());

		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	void deleteById_shouldThrowSchedularRuntimeException() {
		String errorMessage = "error";

		BDDMockito.doThrow(new RuntimeException(errorMessage)).when(this.scheduleRepository).deleteById(anyLong());

		assertThatThrownBy(() -> this.scheduleAdapter.deleteById(DefaultValues.LONG_VALUE))
			.isInstanceOf(SchedularRuntimeException.class)
			.hasMessage(errorMessage);
	}

	@Test
	void deleteById_shouldDeleteDomain() {
		assertThatCode(() -> this.scheduleAdapter.deleteById(DefaultValues.LONG_VALUE)).doesNotThrowAnyException();
		verify(this.scheduleRepository, times(1)).deleteById(anyLong());
	}

}
