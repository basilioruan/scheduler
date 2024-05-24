package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentInPort;
import com.projects.scheduler.inbound.dtos.requests.StudentRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentResponseDTO;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Validated
public class StudentController {

	private final StudentInPort studentInPort;

	@GetMapping("/find/{id}")
	public ResponseEntity<StudentResponseDTO> findById(@NotNull(message = "Student id must be not null") @Min(value = 1,
			message = "Student id must be positive") @PathVariable Long id) {
		try {
			return ResponseEntity.ok(this.studentInPort.findById(id));
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/find-all")
	public ResponseEntity<List<StudentResponseDTO>> findAll() {
		try {
			return ResponseEntity.ok(this.studentInPort.findAll());
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/save")
	public ResponseEntity<StudentResponseDTO> save(
			@RequestBody @NotNull(message = "Student must not be null") StudentRequestDTO studentRequestDTO) {
		try {
			return ResponseEntity.ok(this.studentInPort.save(studentRequestDTO));
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@NotNull(message = "Student id must be not null") @Min(value = 1,
			message = "Student id must be positive") @PathVariable Long id) {
		try {
			this.studentInPort.deleteById(id);
			return ResponseEntity.ok("Student deleted!");
		}
		catch (SchedularRuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
