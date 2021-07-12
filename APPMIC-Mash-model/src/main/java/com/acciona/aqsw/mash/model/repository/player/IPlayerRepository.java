package com.acciona.aqsw.mash.model.repository.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acciona.aqsw.mash.model.domain.Player;

/**
 * The Interface IPlayerRepository.
 */
public interface IPlayerRepository extends JpaRepository<Player, Long> {

	/**
	 * Find by number.
	 *
	 * @param number the number
	 * @return the optional
	 */
	Optional<Player> findByNumber(final long number);

	/**
	 * Exists by number.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	boolean existsByNumber(long number);

}
