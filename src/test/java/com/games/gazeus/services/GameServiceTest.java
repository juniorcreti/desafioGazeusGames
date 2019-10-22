package com.games.gazeus.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import com.games.gazeus.domain.Player;
import com.games.gazeus.repositories.GameRepository;
import com.games.gazeus.repositories.PlayerRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class GameServiceTest {
	@MockBean
    private GameRepository repository;

	@Autowired
    private GameServiceImpl service;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Game game;

    private static final long ID =1;
    private static final String NAME = "Truco";
    private static final String STATUS = "VICTORY";
    private static final int POINT =100;
    @Before
    public void setUp() throws Exception {
    	game=new Game();
    	game.setId(ID);
    	game.setName(NAME);
    	game.setStatus(STATUS);
    	game.setPoints(POINT);
      
    
     System.out.println(game.getName() +"TESTE");
       when(repository.findById(ID)).thenReturn(Optional.empty());
       
    }
    
    @Test
    public void salvarUmPlaneta() {
        when(repository.save(game)).thenReturn(game);
        Game gameTest = service.cadastrarGame(game);
        verify(repository).save(game);
        assertEquals(gameTest.getId(),ID);
        
        assertThat(gameTest.getName()).isEqualTo(NAME);
        assertThat(gameTest.getStatus()).isEqualTo(STATUS);
        assertEquals(gameTest.getPoints(), POINT);
       
    }

   @Test
    public void removerUmGame() {
        doNothing().when(repository).deleteById(ID);
        when(repository.findById(ID)).thenReturn(Optional.empty());
    }
  
    @Test
    public void buscarNameAndStatus() {
     
        Game gameTest = service.buscaGame(NAME, STATUS);
        assertThat(gameTest.getId()).isNotNull();
        assertThat(gameTest.getName()).isEqualTo(NAME);
        assertThat(gameTest.getStatus()).isEqualTo(STATUS);
        assertThat(gameTest.getPoints()).isEqualTo(200);
    }
  
  

    @Test
    public void listaComTodosOsGames() {
        Game Purrinha = new Game("Purrinha", "VICTORY", 300);
        
        when(repository.findAll()).thenReturn(Arrays.asList(game, Purrinha));
        List<Game> planetasTest = service.listarAllGame();
        MatcherAssert.assertThat(planetasTest, not(IsEmptyCollection.empty()));
    }

    @Test
    public void listaDeGamesVazia() {
        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<Game> planetasTest = service.listarAllGame();
        MatcherAssert.assertThat(planetasTest, IsEmptyCollection.empty());
    }
    
}
