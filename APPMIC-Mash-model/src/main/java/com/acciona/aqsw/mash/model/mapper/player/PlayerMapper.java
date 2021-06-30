package com.acciona.aqsw.mash.model.mapper.player;

import java.util.List;

import org.mapstruct.Mapper;

import com.acciona.aqsw.mash.api.dto.PlayerDTO;
import com.acciona.aqsw.mash.model.domain.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

	PlayerDTO playerToPlayerDto(Player player);

	List<PlayerDTO> playerToPlayerDto(List<Player> player);

	Player playerDtoToPlayer(PlayerDTO player);

	List<Player> playerDtoToPlayer(List<PlayerDTO> player);

}
