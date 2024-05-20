package com.projects.scheduler.outbound.persistence.repositories;

import com.projects.scheduler.outbound.persistence.entities.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
