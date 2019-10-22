package com.games.gazeus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import com.games.gazeus.domain.Game;

public interface GameService {
	Game cadastrarGame(Game game);
	public Game buscaGame(String name, String status);
	List<Game> listarAllGame();

	@Transactional(readOnly = true)
	Optional<Game> findByNameAndStatus(String name, String status);
}
