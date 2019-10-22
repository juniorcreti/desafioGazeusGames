package com.games.gazeus.services;

import java.util.List;

import com.games.gazeus.domain.Game;
import com.games.gazeus.domain.Match;
import com.games.gazeus.domain.Player;

public interface MatchService {

	Match cadastra(Game game, Player player);

	List<Match> listarMatch();

}
