package com.games.gazeus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.games.gazeus.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Optional<Game> findByNameAndStatus(String name, String status);
}
