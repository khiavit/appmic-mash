package com.acciona.aqsw.mash.ws.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;
import com.acciona.aqsw.mash.api.service.IPlayerService;
import com.acciona.aqsw.mash.ws.controller.handler.GlobalErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class PlayerControllerTest.
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { IPlayerService.class })
class PlayerControllerTest {

	/** The player service. */
	@MockBean
	private IPlayerService playerService;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The om. */
	protected static ObjectMapper om = new ObjectMapper();

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new PlayerController(playerService))
				.setControllerAdvice(new GlobalErrorHandler()).build();
	}

	/**
	 * Test list all users OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testListAllUsersOK() throws Exception {

		Mockito.when(playerService.getPlayers())
				.thenReturn(Arrays.asList(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build()));

		mockMvc.perform(get("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	/**
	 * Test list all users no content.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testListAllUsersNoContent() throws Exception {

		Mockito.when(playerService.getPlayers()).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isNoContent());
	}

	/**
	 * Test get user OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetUserOK() throws Exception {

		Mockito.when(playerService.getPlayerById(1L))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		mockMvc.perform(get("/api/v1/players/{id}", 1L).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Player 1")));
	}

	/**
	 * Test get user no csrf OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetUserNoCsrfOK() throws Exception {

		// las peticiones GET por defecto no necesitan csrf
		Mockito.when(playerService.getPlayerById(1))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		mockMvc.perform(get("/api/v1/players/{id}", 1L).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Player 1")));
	}

	/**
	 * Test get user KO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetUserKO() throws Exception {

		Mockito.doThrow(new PlayerNotFoundException("Usuario con id 1 no encontrado.")).when(playerService)
				.getPlayerById(1L);

		mockMvc.perform(get("/api/v1/players/{id}", 1L).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isNoContent())
				.andExpect(content().string(containsString("Usuario con id 1 no encontrado.")));
	}

	/**
	 * Test create user OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testCreateUserOK() throws Exception {

		final PlayerDTO player = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerService.insert(player)).thenReturn(player);

		mockMvc.perform(post("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.content(om.writeValueAsString(player)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isCreated()).andExpect(content().string(containsString("Player 1")));
	}

	/**
	 * Test create user KO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testCreateUserKO() throws Exception {

		final PlayerDTO player = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.doThrow(new PlayerExistsConflictException(
				"Imposible la creacion. El usuario con el nombre Player 1, ya existe.")).when(playerService)
				.insert(player);

		mockMvc.perform(post("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.content(om.writeValueAsString(player)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isInternalServerError()).andExpect(content().string(
						containsString("Imposible la creacion. El usuario con el nombre Player 1, ya existe.")));
	}

	/**
	 * Test update user OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateUserOK() throws Exception {

		final PlayerDTO player = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerService.update(player)).thenReturn(player);

		mockMvc.perform(put("/api/v1/players", 1).with(csrf()).header("Authorization", "Bearer ")
				.content(om.writeValueAsString(player)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("Player 1")));
	}

	/**
	 * Test update user KO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdateUserKO() throws Exception {

		final PlayerDTO player = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.doThrow(new PlayerNotFoundException("Usuario con id 1 no encontrado.")).when(playerService)
				.update(player);

		mockMvc.perform(put("/api/v1/players", 1).with(csrf()).header("Authorization", "Bearer ")
				.content(om.writeValueAsString(player)).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isNoContent())
				.andExpect(content().string(containsString("Usuario con id 1 no encontrado.")));
	}

	/**
	 * Test delete user OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteUserOK() throws Exception {

		Mockito.when(playerService.delete(1)).thenReturn(1L);

		mockMvc.perform(delete("/api/v1/players/{id}", 1).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("Eliminado el usuario con id 1")));
	}

	/**
	 * Test delete user KO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDeleteUserKO() throws Exception {

		Mockito.doThrow(new PlayerNotFoundException("Usuario con id 1 no encontrado.")).when(playerService).delete(1L);

		mockMvc.perform(delete("/api/v1/players/{id}", 1).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isNoContent())
				.andExpect(content().string(containsString("Usuario con id 1 no encontrado.")));
	}

}
