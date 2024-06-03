package com.projects.scheduler.utils.errorhandler;

import com.projects.scheduler.utils.exceptions.SchedularRuntimeException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTests {

	@InjectMocks
	private RestExceptionHandler restExceptionHandler;

	@Test
	void handleEntityNotFound_shouldReturnHttpStatusNotFound() {
		String errorMessage = "Not found";
		EntityNotFoundException exception = new EntityNotFoundException(errorMessage);

		ResponseEntity<Object> actual = this.restExceptionHandler.handleEntityNotFound(exception);
		ApiError body = (ApiError) actual.getBody();

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(body).isNotNull();
		assertThat(body.getMessage()).isEqualTo(errorMessage);
	}

	@Test
	void handleSchedularRuntime_shouldReturnHttpStatusInternalServerError() {
		String errorMessage = "Error";
		SchedularRuntimeException exception = new SchedularRuntimeException(errorMessage);

		ResponseEntity<Object> actual = this.restExceptionHandler.handleSchedularRuntime(exception);
		ApiError body = (ApiError) actual.getBody();

		assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(body).isNotNull();
		assertThat(body.getMessage()).isEqualTo(errorMessage);
	}

}
