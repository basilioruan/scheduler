package com.projects.scheduler.outbound.persistence.repositories;

import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLevelRepository extends JpaRepository<StudentLevelEntity, Long> {
}
