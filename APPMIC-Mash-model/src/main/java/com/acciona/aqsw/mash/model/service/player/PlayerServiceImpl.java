package com.acciona.aqsw.mash.model.service.player;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
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
	public PlayerDTO getPlayerById(final long id) {
		return playerMapper.playerToPlayerDto(playerRepository.findById(id).get());
	}

	@Override
	public PlayerDTO getPlayerWithNumber(final long number) {
		if(log.isInfoEnabled()){
			log.info("Recuperando el usuario con number {} desde el servicio", number);
		}
        return playerMapper.playerToPlayerDto(playerRepository.findByNumber(number));
	}

	@Override
	public List<PlayerDTO> getPlayers() {
		if(log.isInfoEnabled()){
			log.info("Recuperando la lista de usuarios desde el servicio");
		}
		return playerMapper.playerToPlayerDto(playerRepository.findAll());
	}

	@Override
	public void insert(PlayerDTO player) throws PlayerExistsConflictException {
		if (getPlayerWithNumber(player.getNumber()) != null ) {
			final String txtError = "Imposible la creacion. El usuario con el nombre  %s, ya existe.";
			if (log.isWarnEnabled()) {
				log.error(txtError, player.getName());
			}
			throw new PlayerExistsConflictException(String.format(txtError, player.getName()));
		}
		playerRepository.saveAndFlush(playerMapper.playerDtoToPlayer(player));
	}

	@Override
	public void update(PlayerDTO player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUserExist(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
