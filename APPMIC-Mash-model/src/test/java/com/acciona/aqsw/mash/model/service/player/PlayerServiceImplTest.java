package com.acciona.aqsw.mash.model.service.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;
import com.acciona.aqsw.mash.model.domain.Player;
import com.acciona.aqsw.mash.model.mapper.player.PlayerMapper;
import com.acciona.aqsw.mash.model.repository.player.IPlayerRepository;

/**
 * The Class PlayerServiceImplTest.
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { PlayerMapper.class, IPlayerRepository.class })
class PlayerServiceImplTest {

	@MockBean
	private PlayerMapper playerMapper;

	@MockBean
	private IPlayerRepository playerRepository;

	private PlayerServiceImpl playerService;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		playerService = new PlayerServiceImpl(playerMapper, playerRepository);
	}

	/**
	 * Test get player by id.
	 */
	@Test
	void testGetPlayerById() {
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
		Mockito.when(playerMapper.playerToPlayerDto(player))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		final PlayerDTO playerDTO = playerService.getPlayerById(1L);

		verify(playerRepository, times(1)).findById(1L);
		assertNotNull(player);
		assertEquals(player.getId(), playerDTO.getId());
		assertEquals(player.getName(), playerDTO.getName());
		assertEquals(player.getNumber(), playerDTO.getNumber());
		assertEquals(player.getAge(), playerDTO.getAge());
	}

	/**
	 * Test get player by id exception.
	 */
	@Test
	void testGetPlayerByIdException() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			playerService.getPlayerById(1L);
		});

		assertTrue(exception.getMessage().contains("Usuario con id 1 no encontrado."));
	}

	/**
	 * Test get player with number.
	 */
	@Test
	void testGetPlayerWithNumber() {
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerRepository.findByNumber(1)).thenReturn(Optional.of(player));
		Mockito.when(playerMapper.playerToPlayerDto(player))
				.thenReturn(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build());

		final PlayerDTO playerDTO = playerService.getPlayerWithNumber(1L);

		verify(playerRepository, times(1)).findByNumber(1L);
		assertNotNull(playerDTO);
		assertEquals(player.getId(), playerDTO.getId());
		assertEquals(player.getName(), playerDTO.getName());
		assertEquals(player.getNumber(), playerDTO.getNumber());
		assertEquals(player.getAge(), playerDTO.getAge());
	}

	/**
	 * Test get player with number exception.
	 */
	@Test
	void testGetPlayerWithNumberException() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			playerService.getPlayerWithNumber(1L);
		});

		assertTrue(exception.getMessage().contains("Usuario con number 1 no encontrado."));
	}

	/**
	 * Test get players.
	 */
	@Test
	void testGetPlayers() {
		final List<Player> players = Arrays.asList(Player.builder().id(1L).name("Player 1").age(11).number(1L).build());
		Mockito.when(playerRepository.findAll()).thenReturn(players);
		Mockito.when(playerMapper.playerToPlayerDto(players))
				.thenReturn(Arrays.asList(PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build()));

		final List<PlayerDTO> playerList = playerService.getPlayers();

		verify(playerRepository, times(1)).findAll();
		assertNotNull(playerList);
		assertEquals(1, playerList.size());
	}

	/**
	 * Test insert.
	 */
	@Test
	void testInsert() {
		final PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerMapper.playerDtoToPlayer(playerDTO)).thenReturn(player);
		Mockito.when(playerRepository.existsById(1L)).thenReturn(false);

		playerService.insert(playerDTO);

		verify(playerRepository, times(1)).saveAndFlush(player);
	}

	/**
	 * Test insert exception.
	 */
	@Test
	public void testInsertException() {
		final PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerMapper.playerToPlayerDto(player)).thenReturn(playerDTO);
		Mockito.when(playerRepository.existsByNumber(1L)).thenReturn(true);

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
		final PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerMapper.playerDtoToPlayer(playerDTO)).thenReturn(player);
		Mockito.when(playerRepository.existsById(1L)).thenReturn(true);

		playerService.update(playerDTO);

		verify(playerRepository, times(1)).saveAndFlush(player);
	}

	/**
	 * Test update exception.
	 */
	@Test
	public void testUpdateException() {
		final PlayerDTO playerDTO = PlayerDTO.builder().id(1L).name("Player 1").age(11).number(1L).build();
		final Player player = Player.builder().id(1L).name("Player 1").age(11).number(1L).build();
		Mockito.when(playerMapper.playerDtoToPlayer(playerDTO)).thenReturn(player);
		Mockito.when(playerRepository.existsById(1L)).thenReturn(false);

		Exception exception = assertThrows(RuntimeException.class, () -> {
			playerService.update(playerDTO);
		});

		assertTrue(exception.getMessage().contains("Usuario con id 1 no encontrado."));
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
	 * Test delete exception.
	 */
	@Test
	void testDeleteException() {
		Mockito.doThrow(new PlayerNotFoundException("Usuario con id 1 no encontrado.")).when(playerRepository)
				.deleteById(1L);

		Exception exception = assertThrows(RuntimeException.class, () -> {
			playerService.delete(1L);
		});

		assertTrue(exception.getMessage().contains("Usuario con id 1 no encontrado."));

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
