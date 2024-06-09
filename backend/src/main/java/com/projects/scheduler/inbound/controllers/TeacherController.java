package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.TeacherInPort;
import com.projects.scheduler.inbound.dtos.requests.TeacherRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.TeacherResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherInPort teacherInPort;

	@GetMapping("/find/{id}")
	public ResponseEntity<TeacherResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(this.teacherInPort.findById(id));
	}

	@GetMapping("/find-all")
	public ResponseEntity<List<TeacherResponseDTO>> findAll() {
		return ResponseEntity.ok(this.teacherInPort.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<TeacherResponseDTO> save(@RequestBody TeacherRequestDTO dto) {
		return ResponseEntity.ok(this.teacherInPort.save(dto));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		this.teacherInPort.deleteById(id);
		return ResponseEntity.ok("Teacher deleted!");
	}

}
