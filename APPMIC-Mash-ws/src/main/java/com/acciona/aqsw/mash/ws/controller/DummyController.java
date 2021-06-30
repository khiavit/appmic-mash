package com.acciona.aqsw.mash.ws.controller;


import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.service.IPlayerService;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "/api/v1", tags = "Operaciones con usuarios")
@Log4j2
@RequiredArgsConstructor
public class 	DummyController {


	//en vez de poner autowired se inyectan @RequiredArgsConstructor
	private final IPlayerService service;

	@GetMapping(value = "/dummy/findAll", headers = "Accept=application/json")
	@ApiOperation(value = "Acceso a  todos los usuario.", notes = "Se pide el listado de todos los usuarios dados de alta en la BBDD.")
	@ApiResponse(code = 401, message = "No se encontro el resultado")
	public ResponseEntity<String> getConfiguration() {
		service.findAll();
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
