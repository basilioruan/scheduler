package com.projects.scheduler.outbound.persistence.repositories;

import com.projects.scheduler.outbound.persistence.entities.TeacherEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

}
