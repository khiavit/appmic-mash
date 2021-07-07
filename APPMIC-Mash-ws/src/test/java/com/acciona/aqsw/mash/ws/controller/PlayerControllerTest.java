package com.acciona.aqsw.mash.ws.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.acciona.aqsw.mash.api.service.IPlayerService;
import com.acciona.aqsw.mash.ws.helper.PlayerServiceHelper;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(value = PlayerController.class)
@SpringBootTest
class PlayerControllerTest {

	@MockBean
	private IPlayerService playerService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testListAllUsersOK() throws Exception {
		Mockito.when(playerService.getPlayers()).thenReturn(PlayerServiceHelper.createPlayersDTO());
		mockMvc.perform(get("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testListAllUsersNoContent() throws Exception {
		Mockito.when(playerService.getPlayers()).thenReturn(PlayerServiceHelper.createPlayersDTOEmpty());
		mockMvc.perform(get("/api/v1/players").with(csrf()).header("Authorization", "Bearer ")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isNoContent());
	}

	@Test
	void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateUser() {
		fail("Not yet implemented");
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
