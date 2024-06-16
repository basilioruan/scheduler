package com.projects.scheduler.outbound.adapters;

import java.util.List;

import com.projects.scheduler.application.domains.Schedule;
import com.projects.scheduler.application.ports.outbound.ScheduleOutPort;
import com.projects.scheduler.outbound.mappers.ScheduleEntityMapper;
import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;
import com.projects.scheduler.outbound.persistence.repositories.ScheduleRepository;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleAdapter implements ScheduleOutPort {

	private final ScheduleEntityMapper scheduleEntityMapper;

	private final ScheduleRepository scheduleRepository;

	@Override
	public Schedule findById(Long id) {
		try {
			return this.scheduleEntityMapper.fromEntity(this.scheduleRepository.findById(id).orElse(null));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public List<Schedule> findAll() {
		try {
			return this.scheduleRepository.findAll().stream().map(this.scheduleEntityMapper::fromEntity).toList();
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public Schedule save(Schedule schedule) {
		try {
			ScheduleEntity entity = this.scheduleEntityMapper.fromDomain(schedule);
			return this.scheduleEntityMapper.fromEntity(this.scheduleRepository.save(entity));
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

	@Override
	public void deleteById(Long id) {
		try {
			this.scheduleRepository.deleteById(id);
		}
		catch (Exception ex) {
			throw new SchedularRuntimeException(ex.getMessage());
		}
	}

}
