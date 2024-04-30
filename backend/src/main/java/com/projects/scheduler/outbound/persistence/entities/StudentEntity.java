package com.projects.scheduler.outbound.persistence.entities;

import com.projects.scheduler.utils.enums.ClassTypeIndicator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "application_user_id")
public class StudentEntity extends UserEntity{

    @ManyToOne
    @JoinColumn(name = "student_level_id")
    private StudentLevelEntity studentLevelEntity;

    @Column(name = "student_class_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ClassTypeIndicator classType;

    @Column(name = "student_reschedules")
    private int reschedules;

    @Builder
    public StudentEntity(Long id, @NotNull String name, @NotNull String phone, @NotNull String email,
                         String photo, @NotNull LocalDateTime creationDate, @NotNull LocalDateTime lastUpdateDate,
                         LocalDateTime lastLoginDate, StudentLevelEntity studentLevelEntity,
                         @NotNull ClassTypeIndicator classType, int reschedules) {
        super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
        this.studentLevelEntity = studentLevelEntity;
        this.classType = classType;
        this.reschedules = reschedules;
    }
}
