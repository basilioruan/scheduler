package com.projects.scheduler.application.usecases;

import java.util.List;

import com.projects.scheduler.application.domains.StudentLevel;
import com.projects.scheduler.application.ports.inbound.StudentLevelInPort;
import com.projects.scheduler.application.ports.outbound.StudentLevelOutPort;
import com.projects.scheduler.inbound.dtos.requests.StudentLevelRequestDTO;
import com.projects.scheduler.inbound.dtos.requests.mappers.StudentLevelRequestDTOMapper;
import com.projects.scheduler.inbound.dtos.responses.StudentLevelResponseDTO;
import com.projects.scheduler.inbound.dtos.responses.mappers.StudentLevelResponseDTOMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentLevelUseCase implements StudentLevelInPort {

	private final StudentLevelOutPort studentLevelOutPort;

	private final StudentLevelRequestDTOMapper studentLevelRequestDTOMapper;

	private final StudentLevelResponseDTOMapper studentLevelResponseDTOMapper;

	@Override
	public StudentLevelResponseDTO findById(Long id) {
		StudentLevel studentLevel = this.studentLevelOutPort.findById(id);
		return this.studentLevelResponseDTOMapper.fromDomain(studentLevel);
	}

	@Override
	public List<StudentLevelResponseDTO> findAll() {
		List<StudentLevel> studentLevels = this.studentLevelOutPort.findAll();
		return studentLevels.stream().map(this.studentLevelResponseDTOMapper::fromDomain).toList();
	}

	@Override
	public StudentLevelResponseDTO save(StudentLevelRequestDTO studentLevelRequestDTO) {
		StudentLevel studentLevelDomain = this.studentLevelRequestDTOMapper.fromDTO(studentLevelRequestDTO);
		StudentLevel studentLevelSaved = this.studentLevelOutPort.save(studentLevelDomain);
		return this.studentLevelResponseDTOMapper.fromDomain(studentLevelSaved);
	}

	@Override
	public void deleteById(Long id) {
		this.studentLevelOutPort.deleteById(id);
	}

}
