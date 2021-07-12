package com.acciona.aqsw.mash.api.service;

import java.util.List;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.api.exception.PlayerExistsConflictException;
import com.acciona.aqsw.mash.api.exception.PlayerNotFoundException;

/**
 * The Interface IPlayerService.
 */
public interface IPlayerService {

	/**
	 * Gets the player by id.
	 *
	 * @param id the id
	 * @return the player by id
	 * @throws PlayerNotFoundException the player not found exception
	 */
	PlayerDTO getPlayerById(final long id) throws PlayerNotFoundException;

	/**
	 * Gets the player with number.
	 *
	 * @param number the number
	 * @return the player with number
	 * @throws PlayerNotFoundException the player not found exception
	 */
	PlayerDTO getPlayerWithNumber(final long number) throws PlayerNotFoundException;

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	List<PlayerDTO> getPlayers();

	/**
	 * Insert.
	 *
	 * @param player the player
	 * @return the player DTO
	 * @throws PlayerExistsConflictException the player exists conflict exception
	 */
	PlayerDTO insert(final PlayerDTO player) throws PlayerExistsConflictException;

	/**
	 * Update.
	 *
	 * @param player the player
	 * @return the player DTO
	 * @throws PlayerNotFoundException the player not found exception
	 */
	PlayerDTO update(final PlayerDTO player) throws PlayerNotFoundException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the long
	 * @throws PlayerNotFoundException the player not found exception
	 */
	long delete(final long id) throws PlayerNotFoundException;

	/**
	 * Checks if is user exist by id.
	 *
	 * @param id the id
	 * @return true, if is user exist by id
	 */
	boolean isUserExistById(final long id);

	/**
	 * Checks if is user exist by number.
	 *
	 * @param number the number
	 * @return true, if is user exist by number
	 */
	boolean isUserExistByNumber(final long number);

}
