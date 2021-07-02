package com.acciona.aqsw.mash.api.service;

import java.util.List;
import java.util.Optional;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;

public interface IPlayerService {

	PlayerDTO getPlayerById(final long id) throws PlayerNotFoundException;

	Optional<PlayerDTO> getPlayerWithNumber(final long number);

	List<PlayerDTO> getPlayers();

	PlayerDTO insert(final PlayerDTO player) throws PlayerExistsConflictException;

	PlayerDTO update(final Long id, final PlayerDTO player) throws PlayerNotFoundException;

	void delete(final long id) throws PlayerNotFoundException;

	boolean isUserExist(final long id);

}
