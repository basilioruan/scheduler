package com.projects.scheduler.inbound.dtos.responses.mappers;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentLevelResponseDTOMapper {

    public StudentLevel fromDTO(StudentLevelResponseDTO dto) {
        if(Objects.isNull(dto)) {
            return null;
        }

        return StudentLevel.builder()
                .id(dto.getId())
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .build();
    }

    public StudentLevelResponseDTO fromDomain(StudentLevel domain) {
        if(Objects.isNull(domain)) {
            return null;
        }

        return StudentLevelResponseDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .code(domain.getCode())
                .description(domain.getDescription())
                .build();
    }

}
