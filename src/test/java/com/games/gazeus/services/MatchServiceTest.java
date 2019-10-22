package com.games.gazeus.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.games.gazeus.DesafioGazeusGamesApplication;
import com.games.gazeus.domain.Game;
import com.games.gazeus.domain.Match;
import com.games.gazeus.domain.Player;
import com.games.gazeus.domain.dto.TopDTO;
import com.games.gazeus.repositories.MatchRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class MatchServiceTest {

	@MockBean
	private MatchRepository repository;

	

	@Autowired
	private TopDTOService serviceTOP;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Match match;
	private Game game;
	private Player player;

	private static final int N = 10;
	private static final String NOME = "Truco";
	private static final String STATUS = "VICTORY";
	private static final long PLAYERID = 1;
	private static final long GAMEID = 2;

	@Before
	public void setUp() throws Exception {
		game = new Game();
		game.setId(GAMEID);
		game.setName(NOME);
		game.setStatus(STATUS);

		player = new Player();
		player.setId(PLAYERID);

		match = new Match();
		match.setGame(game);
		match.setPlayer(player);
		match.setId(90);
		match.setTotal(0);

		when(repository.findById(GAMEID)).thenReturn(Optional.empty());

	}

	@Test
	public void salvarUmMatch() {

		when(repository.save(match)).thenReturn(match);
		Match matchTest = repository.save(match);
		verify(repository).save(match);
		assertThat(matchTest.getId()).isNotNull();
		assertThat(matchTest.getGame().getName()).isEqualTo(NOME);
		assertThat(matchTest.getPlayer().getId()).isEqualTo(PLAYERID);

	}

	@Test
	public void listaTOP() {
		List<TopDTO> topPlayers = serviceTOP.listarTop(NOME, N);
		assertThat(topPlayers.get(0).getNameGame()).isEqualTo(NOME);
		assertThat(topPlayers.get(0).getTotalPoints()).isNotNegative();
		MatcherAssert.assertThat(topPlayers, not(IsEmptyCollection.empty()));
	}

	@Test
	public void listaDeTOPsVazia() {
		when(repository.findAll()).thenReturn(Collections.emptyList());
		List<TopDTO> topPlayers = serviceTOP.listarTop("Buraco", 0);
		MatcherAssert.assertThat(topPlayers, (IsEmptyCollection.empty()));
	}

}
