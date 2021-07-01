package com.acciona.aqsw.mash.model.service.player;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;
import com.acciona.aqsw.mash.api.service.IPlayerService;
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
		if (log.isInfoEnabled()) {
			log.info("Buscando usuario con id {}", id);
		}
		try {
			return playerMapper.playerToPlayerDto(playerRepository.findById(id).get());
		} catch (Exception e) {
			final String txtError = "Usuario con id %d no encontrado.";
			if (log.isWarnEnabled()) {
				log.error(txtError, id);
			}
			throw new PlayerNotFoundException(String.format(txtError, id));
		}
	}

	@Override
	public PlayerDTO getPlayerWithNumber(final long number) {
		if (log.isInfoEnabled()) {
			log.info("Recuperando el usuario con number {} desde el servicio", number);
		}
		return playerMapper.playerToPlayerDto(playerRepository.findByNumber(number));
	}

	@Override
	public List<PlayerDTO> getPlayers() {
		if (log.isInfoEnabled()) {
			log.info("Recuperando la lista de usuarios desde el servicio");
		}
		return playerMapper.playerToPlayerDto(playerRepository.findAll());
	}

	@Override
	public void insert(final PlayerDTO player) throws PlayerExistsConflictException {
		if (log.isInfoEnabled()) {
			log.info("Creando usuario : {}", player);
		}
		if (getPlayerWithNumber(player.getNumber()) != null) {
			final String txtError = "Imposible la creacion. El usuario con el nombre  %s, ya existe.";
			if (log.isWarnEnabled()) {
				log.error(txtError, player.getName());
			}
			throw new PlayerExistsConflictException(String.format(txtError, player.getName()));
		}
		playerRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
	}

	@Override
	public PlayerDTO update(final Long id, final PlayerDTO player) {
		PlayerDTO currentPlayer = null;
		if (log.isInfoEnabled()) {
			log.info("Usuario actualizado con id {}", id);
		}
		try {
			currentPlayer = getPlayerById(id);
			currentPlayer.setName(player.getName());
			currentPlayer.setAge(player.getAge());
			currentPlayer.setNumber(player.getNumber());
			playerRepository.saveAndFlush(playerMapper.playerDtoToPlayer(currentPlayer));
		} catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.error("Imposible actualizar. Usuario con id {} no se encontro.", id);
			}
		}
		return currentPlayer;
	}

	@Override
	public PlayerDTO delete(final long id) throws PlayerNotFoundException {
		PlayerDTO currentPlayer = null;
		if (log.isInfoEnabled()) {
			log.info("Buscando & eliminando usuario con id {}", id);
		}
		try {
//			currentPlayer = getPlayerById(id);
			playerRepository.deleteById(id);
		} catch (Exception e) {
			final String txtError = "No es posible eliminarlo. Usuario con id %d no encontrado.";
			if (log.isWarnEnabled()) {
				log.error(txtError, id);
			}
		}
		return currentPlayer;
	}

	@Override
	public boolean isUserExist(final long id) {
		return playerRepository.existsById(id);
	}

}
