package com.acciona.aqsw.mash.model.service.player;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;
import com.acciona.aqsw.mash.api.service.IPlayerService;
import com.acciona.aqsw.mash.model.domain.Player;
import com.acciona.aqsw.mash.model.mapper.player.PlayerMapper;
import com.acciona.aqsw.mash.model.repository.player.IPlayerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class PlayerServiceImpl implements IPlayerService {

	private final PlayerMapper playerMapper;

	private final IPlayerRepository playerRepository;

	@Override
	public PlayerDTO getPlayerById(final long id) throws PlayerNotFoundException {
		return playerMapper.playerToPlayerDto(playerRepository.findById(id)
				.orElseThrow(() -> new PlayerNotFoundException(String.format("Usuario con id %d no encontrado.", id))));
	}

	@Override
	public Optional<PlayerDTO> getPlayerWithNumber(final long number) {
		return playerRepository.findByNumber(number);
	}

	@Override
	public List<PlayerDTO> getPlayers() {
		return playerMapper.playerToPlayerDto(playerRepository.findAll());
	}

	@Override
	public PlayerDTO insert(final PlayerDTO player) throws PlayerExistsConflictException {
		if (getPlayerWithNumber(player.getNumber()).isPresent()) {
			throw new PlayerExistsConflictException(
					String.format("Imposible la creacion. El usuario con el nombre  %s, ya existe.", player.getName()));
		} else {
			final Player playerAdded = playerRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
			return playerMapper.playerToPlayerDto(playerAdded);
		}
	}

	@Override
	public PlayerDTO update(final Long id, final PlayerDTO player) throws PlayerNotFoundException {
		if (isUserExist(id)) {
			player.setId(id);
			final Player playerUpdated = playerRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
			return playerMapper.playerToPlayerDto(playerUpdated);
		} else {
			throw new PlayerNotFoundException(String.format("Usuario con id %d no encontrado.", id));
		}
	}

	@Override
	public void delete(final long id) throws PlayerNotFoundException {
		if (isUserExist(id)) {
			playerRepository.deleteById(id);
		} else {
			throw new PlayerNotFoundException(String.format("Usuario con id %d no encontrado.", id));
		}
	}

	@Override
	public boolean isUserExist(final long id) {
		return playerRepository.existsById(id);
	}

}
