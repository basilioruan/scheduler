package com.projects.scheduler.outbound.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "student_level", schema = "DB_SCHEDULER")
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
