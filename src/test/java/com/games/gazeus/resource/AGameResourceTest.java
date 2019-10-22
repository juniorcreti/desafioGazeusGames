package com.games.gazeus.resource;

import static io.restassured.RestAssured.given;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.games.gazeus.DesafioGazeusGamesApplication;
import com.games.gazeus.domain.Game;
import com.games.gazeus.repositories.GameRepository;
import com.games.gazeus.services.GameServiceImpl;


import io.restassured.http.ContentType;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class AGameResourceTest {


	private Game game;
	
	int porta =8050;
	@Autowired
	private GameRepository repository;
	
	@Autowired
	private GameServiceImpl service;
	@Before
    public void setUp() {
    	repository.deleteAll();
    	
    	Game  trucov  = new Game("Truco","VICTORY",200);
    	Game  trucode  = new Game("Truco","DEFEAT",-50);
    	Game  trucodr  = new Game("Truco","DRAW",100);
    	Game  buracov  = new Game("Buraco","VICTORY",100);
    	Game  buracode  = new Game("Buraco","DEFEAT",-20);
    	Game  buracodr  = new Game("Buraco","DRAW",50);
    	Game  dominov  = new Game("Domino","VICTORY",150);
    	Game  dominode  = new Game("Domino","DEFEAT",-30);
    	Game  dominodr  = new Game("Domino","DRAW",75);
    	
		
		List<Game> games = Arrays.asList(trucov, trucode,trucodr , buracov,buracode,buracodr ,dominov , dominode,dominodr);
		
		
		games.forEach(dto -> { 			
			
			game= service.cadastrarGame(dto);
			System.out.println("teste id" +game.getId());
		});
		
    }

	@Test
	public void listaGamesStatusCode200() {		
		given()
			.port(porta)
		.get("/api/games/")
		.then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(OK.value());
			
	}


}