package com.games.gazeus.services;

import java.util.List;
import java.util.Optional;

import com.games.gazeus.domain.Player;

public interface PlayerService {
	Player cadastra(Player player);

	Optional<Player>  buscarPlayers(long id);

	List<Player> listarAllPlayers();

	int posicaoPlayer(String gameName, long playerid);

}
