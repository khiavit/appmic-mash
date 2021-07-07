package com.acciona.aqsw.mash.ws.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.acciona.aqsw.mash.api.service.IPlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { IPlayerService.class })
class PlayerControllerTest {

	@MockBean
	private IPlayerService playerService;

	private MockMvc mockMvc;

	protected static ObjectMapper om = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new PlayerController(playerService)).build();
	}

	@Test
	void testListAllUsersOK() throws Exception {
		Mockito.when(playerService.getPlayers())
				.thenReturn(Arrays.asList(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build()));

		mockMvc.perform(get("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testListAllUsersNoContent() throws Exception {
		Mockito.when(playerService.getPlayers()).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/api/v1/players").with(csrf()).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isNoContent());
	}

	@Test
	void testGetUserOK() throws Exception {
		Mockito.when(playerService.getPlayerById(1L))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		mockMvc.perform(get("/api/v1/players/{id}", 1L).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Player 1")));
	}

	@Test
	void testGetUserNoCsrfOK() throws Exception {
		// las peticiones GET por defecto no necesitan csrf
		Mockito.when(playerService.getPlayerById(1))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		mockMvc.perform(get("/api/v1/players/{id}", 1L).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Player 1")));
	}

	@Test
	void testGetUserKO() throws Exception {
		Mockito.when(playerService.getPlayerById(1L)).thenReturn(null);

		mockMvc.perform(get("/api/v1/players/{id}", 1L).with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("Usuario con id 1 no encontrado")));
	}

	@Test
	void testCreateUserOK() throws Exception {
		mockMvc.perform(post("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.content(om.writeValueAsString(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build()))
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().string(containsString("Player 1")));

		verify(playerService, times(1)).insert(any(PlayerDTO.class));
	}

	@Test
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	void testPlayerController() {
		fail("Not yet implemented");
	}

}
