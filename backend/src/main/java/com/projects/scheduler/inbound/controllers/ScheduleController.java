package com.projects.scheduler.inbound.controllers;

import java.util.List;

import com.projects.scheduler.application.ports.inbound.ScheduleInPort;
import com.projects.scheduler.inbound.dtos.requests.ScheduleRequestDTO;
import com.projects.scheduler.inbound.dtos.responses.ScheduleResponseDTO;
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
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleInPort scheduleInPort;

	@GetMapping("/find/{id}")
	public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable Long id) {
		 return ResponseEntity.ok(this.scheduleInPort.findById(id));
 	}

	@GetMapping("/find-all")
	public ResponseEntity<List<ScheduleResponseDTO>> findAll() {
		return ResponseEntity.ok(this.scheduleInPort.findAll());
	}

	@PostMapping("/save")
	public ResponseEntity<ScheduleResponseDTO> save(@RequestBody ScheduleRequestDTO dto) {
		return ResponseEntity.ok(this.scheduleInPort.save(dto));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		this.scheduleInPort.deleteById(id);
		return ResponseEntity.ok("Schedule deleted!");
	}

}
