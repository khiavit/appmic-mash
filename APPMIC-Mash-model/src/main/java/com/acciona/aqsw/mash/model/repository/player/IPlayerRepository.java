package com.acciona.aqsw.mash.model.repository.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acciona.aqsw.mash.model.domain.Player;

public interface IPlayerRepository extends JpaRepository<Player, Long> {

	Optional<Player> findByNumber(final long number);

}
