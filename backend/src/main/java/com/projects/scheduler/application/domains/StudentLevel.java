package com.projects.scheduler.application.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentLevel {

    private Long id;

    private String name;

    private String code;

    private String description;

}
