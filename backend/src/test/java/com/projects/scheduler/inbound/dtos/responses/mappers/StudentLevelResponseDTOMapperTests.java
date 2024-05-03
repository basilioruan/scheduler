package com.projects.scheduler.inbound.dtos.responses.mappers;

import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.mocks.StudentLevelMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StudentLevelResponseDTOMapperTests {

    @InjectMocks
    private StudentLevelResponseDTOMapper StudentLevelResponseDTOMapper;

    @Test
    void fromDTO_shouldReturnNull() {
        StudentLevelResponseDTO actual = this.StudentLevelResponseDTOMapper.fromDomain(null);

        assertThat(actual).isNull();
    }

    @Test
    void fromDTO_shouldReturnDomain() {
        StudentLevelResponseDTO expected = StudentLevelMocks.getStudentLevelResponseDTO();

        StudentLevelResponseDTO actual = this.StudentLevelResponseDTOMapper.fromDomain(StudentLevelMocks.getStudentLevelDomain());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}
