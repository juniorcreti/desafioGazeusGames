package com.games.gazeus.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.gazeus.domain.Game;
import com.games.gazeus.domain.Match;
import com.games.gazeus.domain.Player;
import com.games.gazeus.repositories.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchRepository matchRepository;
	@PersistenceContext
	private EntityManager em;

	@Override
	public Match cadastra(Game game, Player player) {

		return matchRepository.save(new Match(game, player));
	}

	@Override
	public List<Match> listarMatch() {

		return matchRepository.findAll();
	}

}
