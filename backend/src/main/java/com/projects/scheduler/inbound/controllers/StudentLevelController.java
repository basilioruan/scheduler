package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentLevelInPort;
import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-level")
@RequiredArgsConstructor
public class StudentLevelController {

	private final StudentLevelInPort studentLevelInPort;

	@GetMapping("/find/{id}")
	public ResponseEntity<StudentLevelResponseDTO> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(this.studentLevelInPort.findById(id));
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/find-all")
	public ResponseEntity<List<StudentLevelResponseDTO>> findAll() {
		try {
			return ResponseEntity.ok(this.studentLevelInPort.findAll());
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("/save")
	public ResponseEntity<StudentLevelResponseDTO> save(@RequestBody StudentLevelRequestDTO requestDTO) {
		try {
			return new ResponseEntity<>(this.studentLevelInPort.save(requestDTO), HttpStatus.CREATED);
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			this.studentLevelInPort.deleteById(id);
			return ResponseEntity.ok("Student level deleted!");
		}
		catch (SchedularRuntimeException ex) {
			return ResponseEntity.internalServerError().build();
		}
	}

}
