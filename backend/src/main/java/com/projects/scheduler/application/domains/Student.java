package com.projects.scheduler.application.domains;

import com.projects.scheduler.utils.enums.ClassTypeIndicator;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Student extends User {

	private StudentLevel studentLevel;

	private ClassTypeIndicator classType;

	private int reschedules;

}
