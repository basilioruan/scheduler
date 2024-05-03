package com.projects.scheduler.outbound.mappers;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.mocks.StudentLevelMocks;
import com.projects.scheduler.outbound.persistence.entities.StudentLevelEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentLevelEntityMapperTests {

    @InjectMocks
    private StudentLevelEntityMapper studentLevelEntityMapper;

    @Test
    void fromEntity_shouldBeNull() {
        StudentLevel actual = this.studentLevelEntityMapper.fromEntity(null);

        assertThat(actual).isNull();
    }

    @Test
    void fromEntity_shouldReturnDomain() {
        StudentLevelEntity entity = StudentLevelMocks.getStudentLevelEntity();
        StudentLevel expected = StudentLevelMocks.getStudentLevelDomain();

        StudentLevel actual = this.studentLevelEntityMapper.fromEntity(entity);

        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void fromDomain_shouldBeNull() {
        StudentLevelEntity actual = this.studentLevelEntityMapper.fromDomain(null);

        assertThat(actual).isNull();
    }

    @Test
    void fromDomain_shouldReturnEntity() {
        StudentLevel domain = StudentLevelMocks.getStudentLevelDomain();
        StudentLevelEntity expected = StudentLevelMocks.getStudentLevelEntity();

        StudentLevelEntity actual = this.studentLevelEntityMapper.fromDomain(domain);

        assertThat(actual).isNotNull();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
