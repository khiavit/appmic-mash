package com.acciona.aqsw.mash.ws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.service.IPlayerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * The Class PlayerController.
 */
@RestController
@RequestMapping(path = "/api/v1")
@Api(value = "/api/v1", tags = "Operaciones con usuarios")
@RequiredArgsConstructor
@Log4j2
public class PlayerController {

	private final IPlayerService playerService;

	/**
	 * List all users.
	 *
	 * @return the response entity
	 */
	@ApiOperation(value = "Acceso a todos los usuarios.", notes = "Se pide el listado de todos los usuarios dados de alta en la BBDD.")
	@ApiResponses({ @ApiResponse(code = 204, message = "No se encontro el resultado") })
	@GetMapping(value = "/players", headers = "Accept=application/json")
	public ResponseEntity<List<PlayerDTO>> listAllUsers() {

		ResponseEntity<List<PlayerDTO>> response;
		final List<PlayerDTO> players = playerService.getPlayers();
		if (players.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<List<PlayerDTO>>(players, HttpStatus.OK);
		}
		return response;
	}

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@ApiOperation(value = "Busqueda de un usuario.", notes = "Busqueda de un usuario dado de alta en memoria.")
	@ApiResponses({ @ApiResponse(code = 404, message = "No se encontro el resultado") })
	@GetMapping(value = "/players/{id}", headers = "Accept=application/json")
	public ResponseEntity<PlayerDTO> getUser(
			@ApiParam(name = "id", type = "Long", value = "Player's Id", example = "1", required = true) @PathVariable("id") long id) {

		final PlayerDTO player = playerService.getPlayerById(id);
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

	/**
	 * Creates the user.
	 *
	 * @param player the player
	 * @return the response entity
	 */
	@ApiOperation(value = "Alta de un usuario a partir de un id.", notes = "Insercion de un usuario en memoria.")
	@ApiResponses({ @ApiResponse(code = 409, message = "El usuario ya existe") })
	@PostMapping(value = "/players")
	public ResponseEntity<PlayerDTO> createUser(
			@ApiParam(name = "player", type = "PlayerDTO", value = "Player's entity", required = true) @RequestBody PlayerDTO player) {

		final PlayerDTO playerAdded = playerService.insert(player);
		return new ResponseEntity<>(playerAdded, HttpStatus.CREATED);
	}

	/**
	 * Update user.
	 *
	 * @param player the player
	 * @return the response entity
	 */
	@ApiOperation(value = "Modificacion de un usuario a partir de una peticion Put.", notes = "Modificacion de un usuario en memoria.")
	@ApiResponses({ @ApiResponse(code = 404, message = "No se encontro el resultado") })
	@PutMapping(value = "/players")
	public ResponseEntity<PlayerDTO> updateUser(
			@ApiParam(name = "player", type = "PlayerDTO", value = "Player's entity", required = true) @RequestBody PlayerDTO player) {

		final PlayerDTO playerUpdated = playerService.update(player);
		return new ResponseEntity<>(playerUpdated, HttpStatus.OK);
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@ApiOperation(value = "Eliminacion de un usuario a partir de una peticion Put.", notes = "Eliminacion de un usuario en memoria")
	@ApiResponses({ @ApiResponse(code = 404, message = "Usuario no encontrado") })
	@DeleteMapping(value = "/players/{id}")
	public ResponseEntity<?> deleteUser(
			@ApiParam(name = "id", type = "Long", value = "Player's Id", example = "1", required = true) @PathVariable("id") long id) {

		final long idDeleted = playerService.delete(id);
		return new ResponseEntity<>(String.format("Eliminado el usuario con id %d", idDeleted), HttpStatus.OK);
	}

}
