package com.games.gazeus.services;



import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.not;
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
import com.games.gazeus.domain.Player;
import com.games.gazeus.repositories.PlayerRepository;
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class PlayerServiceTest {
		@MockBean
	    private PlayerRepository repository;

		@Autowired
	    private PlayerServiceImpl service;

	    @Rule
	    public ExpectedException expectedException = ExpectedException.none();

	    private Player player;

	    private static final long ID = 4034;
	    private static final String NOME = "Paula Marcolino";
	    @Before
	    public void setUp() throws Exception {
	    	
	        player = new Player();
	      player.setId(ID);
	        player.setName(NOME);
	    
	     System.out.println(player.getName() +"TESTE");
	       when(repository.findById(ID)).thenReturn(Optional.empty());
	       
	    }
	    
	  
	    @Test
	    public void salvarUmPlayer() {
	    	
	        when(repository.save(player)).thenReturn(player);
	        Player playerTest = service.cadastra(player);
	        System.out.println(playerTest.getId()+" TESTE2 "+playerTest.getName());
	        verify(repository).save(player);
	        assertThat(playerTest.getId()).isNotNull();
	        assertThat(playerTest.getName()).isEqualTo(NOME);
	      
	    }

	    @Test
	    public void removerUmPlayer() {
	        doNothing().when(repository).deleteById(ID);
	        when(repository.findById(ID)).thenReturn(Optional.empty());
	    }
	    @Test
	    public void buscarPlayerId() {
	        when(repository.findById(ID)).thenReturn(Optional.of(player));
	        Optional<Player> playerTest  = service.buscarPlayers(ID);
	        verify(repository).findById(ID);
	        Player p= playerTest.get();
	        assertThat(p.getId()).isEqualTo(ID);
	        assertThat(p.getName()).isEqualTo(NOME);
	       
	    }
	  

	    @Test
	    public void listaDePlanetasVazia() {
	        when(repository.findAll()).thenReturn(Collections.emptyList());
	        List<Player> planetasTest = service.listarAllPlayers();
	        MatcherAssert.assertThat(planetasTest, IsEmptyCollection.empty());
	    }
}
