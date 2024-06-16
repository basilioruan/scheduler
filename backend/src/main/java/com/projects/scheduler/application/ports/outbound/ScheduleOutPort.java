package com.projects.scheduler.application.ports.outbound;

import java.util.List;

import com.projects.scheduler.application.domains.Schedule;

public interface ScheduleOutPort {

	Schedule findById(Long id);

	List<Schedule> findAll();

	Schedule save(Schedule schedule);

	void deleteById(Long id);

}
