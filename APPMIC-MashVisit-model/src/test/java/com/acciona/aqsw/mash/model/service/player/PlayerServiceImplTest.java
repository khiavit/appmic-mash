package com.acciona.aqsw.mash.model.service.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.model.domain.Player;
import com.acciona.aqsw.mash.model.mapper.player.PlayerMapper;
import com.acciona.aqsw.mash.model.repository.player.IPlayerRepository;

/**
 * The Class PlayerServiceImplTest.
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { PlayerMapper.class, IPlayerRepository.class })
class PlayerServiceImplTest {

	/** The player mapper. */
	@MockBean
	private PlayerMapper playerMapper;

	/** The player repository. */
	@MockBean
	private IPlayerRepository playerRepository;

	/** The player service. */
	private PlayerServiceImpl playerService;

	/**
	 * Setup.
	 */
	@BeforeEach
	public void setUp() {
		playerService = new PlayerServiceImpl(playerMapper, playerRepository);
	}

	/**
	 * Test get player by id.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPlayerById() throws Exception {
		Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		when(playerRepository.findById(1L))
				.thenReturn(Optional.of(Player.builder().id(1L).name("Player 1").age(11).number(1L).build()));
		when(playerMapper.playerToPlayerDto(player))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		PlayerDTO playerDTO = playerService.getPlayerById(1L);

		verify(playerRepository, times(1)).findById(1L);
		assertNotNull(player);
		assertEquals(player.getId(), playerDTO.getId());
		assertEquals(player.getName(), playerDTO.getName());
		assertEquals(player.getNumber(), playerDTO.getNumber());
		assertEquals(player.getAge(), playerDTO.getAge());
	}

	/**
	 * Test get player with number.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testGetPlayerWithNumber() throws Exception {
		Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		when(playerRepository.findByNumber(1))
				.thenReturn(Optional.of(Player.builder().id(1L).name("Player 1").age(11).number(1L).build()));
		when(playerMapper.playerToPlayerDto(player))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		PlayerDTO playerDTO = playerService.getPlayerWithNumber(1L);

		verify(playerRepository, times(1)).findByNumber(1L);
		assertNotNull(playerDTO);
		assertEquals(player.getId(), playerDTO.getId());
		assertEquals(player.getName(), playerDTO.getName());
		assertEquals(player.getNumber(), playerDTO.getNumber());
		assertEquals(player.getAge(), playerDTO.getAge());
	}

	/**
	 * Test get players.
	 */
	@Test
	void testGetPlayers() {
		List<Player> players = Arrays.asList(Player.builder().id(1L).name("Player 1").age(11).number(1L).build());
		when(playerRepository.findAll())
				.thenReturn(Arrays.asList(Player.builder().id(1L).name("Player 1").age(11).number(1L).build()));
		when(playerMapper.playerToPlayerDto(players))
				.thenReturn(Arrays.asList(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build()));

		List<PlayerDTO> playerList = playerService.getPlayers();

		verify(playerRepository, times(1)).findAll();
		assertNotNull(playerList);
		assertEquals(1, playerList.size());
	}

	/**
	 * Test insert.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testInsert() throws Exception {
		PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		when(playerMapper.playerDtoToPlayer(playerDTO)).thenReturn(player);
		when(playerRepository.existsById(1L)).thenReturn(false);

		playerService.insert(playerDTO);

		verify(playerRepository, times(1)).saveAndFlush(player);
	}

	/**
	 * Test insert exception.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testInsertException() throws Exception {
		PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		when(playerMapper.playerToPlayerDto(player)).thenReturn(playerDTO);
		when(playerRepository.existsByNumber(1L)).thenReturn(true);

		Exception exception = assertThrows(RuntimeException.class, () -> {
			playerService.insert(playerDTO);
		});
		assertTrue(exception.getMessage().contains(
				String.format("Imposible la creacion. El usuario con el nombre  %s, ya existe.", player.getName())));
	}

	/**
	 * Test update.
	 */
	@Test
	void testUpdate() {
		PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		when(playerMapper.playerDtoToPlayer(playerDTO)).thenReturn(player);
		when(playerRepository.existsById(1L)).thenReturn(true);

		playerService.update(playerDTO);

		verify(playerRepository, times(1)).saveAndFlush(player);
	}

	/**
	 * Test delete.
	 */
	@Test
	void testDelete() {
		playerService.delete(1L);

		verify(playerRepository, times(1)).deleteById(1L);
	}

	/**
	 * Test is user exist by id.
	 */
	@Test
	void testIsUserExistById() {
		playerService.isUserExistById(1L);

		verify(playerRepository, times(1)).existsById(1L);
	}

	/**
	 * Test is user exist by number.
	 */
	@Test
	void testIsUserExistByNumber() {
		playerService.isUserExistByNumber(1L);

		verify(playerRepository, times(1)).existsByNumber(1L);
	}

}
