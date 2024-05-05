package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.StudentLevelInPort;
import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import lombok.RequiredArgsConstructor;

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
	public StudentLevelResponseDTO findById(@PathVariable Long id) {
		return this.studentLevelInPort.findById(id);
	}

	@GetMapping("/find-all")
	public List<StudentLevelResponseDTO> findAll() {
		return this.studentLevelInPort.findAll();
	}

	@PostMapping("/save")
	public StudentLevelResponseDTO save(@RequestBody StudentLevelRequestDTO requestDTO) {
		return this.studentLevelInPort.save(requestDTO);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		this.studentLevelInPort.deleteById(id);
	}

}