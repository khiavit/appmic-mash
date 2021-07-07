package com.acciona.aqsw.mash.ws.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.model.domain.Player;

public class PlayerServiceHelper {

	public static Player createPlayer() {
		Player player = new Player();
		player.setId(1L);
		player.setName("Player 1");
		player.setAge(11);
		player.setNumber(1L);
		return player;
	}

	public static List<Player> createPlayers() {
		return Arrays.asList(createPlayer());
	}

	public static PlayerDTO createPlayerDTO() {
		return PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
	}

	public static List<PlayerDTO> createPlayersDTO() {
		return Arrays.asList(createPlayerDTO());
	}

	public static List<PlayerDTO> createPlayersDTOEmpty() {
		return new ArrayList<>();
	}
}
