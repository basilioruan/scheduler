package com.projects.scheduler.outbound.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "student_level")
public class StudentLevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_level_id")
    private Long id;

    @Column(name = "student_level_name")
    @NotNull
    private String name;

    @Column(name = "student_level_code")
    private String code;

    @Column(name = "student_level_description")
    private String description;

}
