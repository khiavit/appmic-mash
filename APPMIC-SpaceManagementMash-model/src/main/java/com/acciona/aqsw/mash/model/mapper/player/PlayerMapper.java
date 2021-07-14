package com.acciona.aqsw.mash.model.mapper.player;

import java.util.List;

import org.mapstruct.Mapper;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.model.domain.Player;

/**
 * The Interface PlayerMapper.
 */
@Mapper(componentModel = "spring")
public interface PlayerMapper {

	/**
	 * Player to player dto.
	 *
	 * @param player the player
	 * @return the player DTO
	 */
	PlayerDTO playerToPlayerDto(Player player);

	/**
	 * Player to player dto.
	 *
	 * @param player the player
	 * @return the list
	 */
	List<PlayerDTO> playerToPlayerDto(List<Player> player);

	/**
	 * Player dto to player.
	 *
	 * @param player the player
	 * @return the player
	 */
	Player playerDtoToPlayer(PlayerDTO player);

	/**
	 * Player dto to player.
	 *
	 * @param player the player
	 * @return the list
	 */
	List<Player> playerDtoToPlayer(List<PlayerDTO> player);

}
