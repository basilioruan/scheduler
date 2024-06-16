package com.projects.scheduler.outbound.persistence.repositories;

import com.projects.scheduler.outbound.persistence.entities.ScheduleEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

}
