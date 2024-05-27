package com.projects.scheduler.application.domains;

import com.projects.scheduler.utils.enums.SchoolSubjectIndicator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class Teacher extends User {

	private SchoolSubjectIndicator schoolSubject;

}
