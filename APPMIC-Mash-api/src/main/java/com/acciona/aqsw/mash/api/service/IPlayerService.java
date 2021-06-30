package com.acciona.aqsw.mash.api.service;

import java.util.List;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;

public interface IPlayerService {

	PlayerDTO getPlayerById(final long id);

	PlayerDTO getPlayerWithNumber(final long number);

	List<PlayerDTO> getPlayers();

	void insert(final PlayerDTO player) throws PlayerExistsConflictException;

	void update(final PlayerDTO player);

	void delete(final long id);

	boolean isUserExist(final long id);

}
