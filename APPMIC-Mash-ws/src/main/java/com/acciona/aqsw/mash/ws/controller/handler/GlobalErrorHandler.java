package com.acciona.aqsw.mash.ws.controller.handler;

import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalErrorHandler {

	private static final String MESSAGE = "message";
	private static final String STATUS_CODE = "status-code";
	private static final String URI = "uri";
	private static final String METHOD = "method";

	@ExceptionHandler({PlayerExistsConflictException.class})
	public ResponseEntity<Map<String, String>> handleElementNotFoundException(final HttpServletRequest request, final RuntimeException e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of(METHOD, request.getMethod(),
						URI, request.getRequestURI(),
						MESSAGE, Objects.requireNonNull(e.getMessage()),
						STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.toString()
				));	}

	@ExceptionHandler({PlayerNotFoundException.class})
	public ResponseEntity<Map<String, String>> handleExistsConflictException(final HttpServletRequest request, final RuntimeException e) {

		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body(Map.of(METHOD, request.getMethod(),
						URI, request.getRequestURI(),
						MESSAGE, Objects.requireNonNull(e.getMessage()),
						STATUS_CODE, HttpStatus.NO_CONTENT.toString()
				));	}

}
