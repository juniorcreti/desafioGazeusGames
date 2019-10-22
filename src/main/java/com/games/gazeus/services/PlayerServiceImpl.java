package com.games.gazeus.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.games.gazeus.domain.Player;
import com.games.gazeus.domain.dto.TopDTO;
import com.games.gazeus.repositories.PlayerRepository;

@Service

public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	TopDTOService topDTOService;
	private RestTemplate template;

	

	public Player cadastra(Player player) {

		return playerRepository.save(player);
	}

	@Override
	public List<Player> listarAllPlayers() {

		return playerRepository.findAll();
	}

	public Optional<Player> buscarPlayers(long id) {
		Optional<Player> obj = this.playerRepository.findById(id);
		return obj;
	}

	@Override
	public int posicaoPlayer(String gameName, long playerid) {

		List<TopDTO> topdto = topDTOService.listarTop(gameName, 0);
		int i = 1;
		for (TopDTO item : topdto) {
			if (item.getId() == playerid) {
				return i;
			}
			i++;
		}
		return 0;
	}

}
