package com.acciona.aqsw.mash.api.service;

import java.util.List;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;

public interface IPlayerService {

	PlayerDTO getPlayerById(final long id) throws PlayerNotFoundException;

	PlayerDTO getPlayerWithNumber(final long number) throws PlayerNotFoundException;

	List<PlayerDTO> getPlayers();

	PlayerDTO insert(final PlayerDTO player) throws PlayerExistsConflictException;

	PlayerDTO update(final PlayerDTO player) throws PlayerNotFoundException;

	long delete(final long id) throws PlayerNotFoundException;

	boolean isUserExistById(final long id);

	boolean isUserExistByNumber(final long number);

}
