package com.projects.scheduler.inbound.dtos.requests.mappers;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StudentLevelRequestDTOMapper {

    public StudentLevel fromDTO(StudentLevelRequestDTO dto) {
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

    public StudentLevelRequestDTO fromDomain(StudentLevel domain) {
        if(Objects.isNull(domain)) {
            return null;
        }

        return StudentLevelRequestDTO.builder()
                .id(domain.getId())
                .name(domain.getName())
                .code(domain.getCode())
                .description(domain.getDescription())
                .build();
    }

}
