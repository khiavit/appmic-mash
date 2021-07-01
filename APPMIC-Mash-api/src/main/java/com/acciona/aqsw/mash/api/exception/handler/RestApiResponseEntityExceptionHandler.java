package com.acciona.aqsw.mash.api.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.acciona.aqsw.mash.api.exception.EntityExistsConflictException;
import com.acciona.aqsw.mash.api.exception.EntityNotFoundException;

import lombok.NoArgsConstructor;

@ControllerAdvice
@NoArgsConstructor
public class RestApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleElementNotFoundException(final EntityNotFoundException ex,
			final WebRequest request) {

		return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(EntityExistsConflictException.class)
	public ResponseEntity<?> handleExistsConflictException(final EntityExistsConflictException ex,
			final WebRequest request) {

		return super.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}
