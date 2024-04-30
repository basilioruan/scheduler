package com.projects.scheduler.outbound.persistence.entities;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "application_user_id")
@EqualsAndHashCode(callSuper = true)
public class TeacherEntity extends UserEntity {

    @Column(name = "teacher_school_subject")
    @NotNull
    private SchoolSubjectIndicator schoolSubject;

    public TeacherEntity(Long id, String name, String phone, String email,
                         String photo, LocalDateTime creationDate, LocalDateTime lastUpdateDate,
                         LocalDateTime lastLoginDate, SchoolSubjectIndicator schoolSubject) {
        super(id, name, phone, email, photo, creationDate, lastUpdateDate, lastLoginDate);
        this.schoolSubject = schoolSubject;
    }

}
