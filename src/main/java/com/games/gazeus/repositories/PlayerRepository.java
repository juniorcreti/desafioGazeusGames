package com.games.gazeus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.gazeus.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
