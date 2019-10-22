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

import com.games.gazeus.domain.Player;

import com.games.gazeus.repositories.PlayerRepository;

import com.games.gazeus.services.PlayerServiceImpl;

import io.restassured.http.ContentType;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class BPlayerResourceTest {


	private Player player;
	
	int porta =8050;
	@Autowired
	private PlayerRepository repository;
	
	@Autowired
	private PlayerServiceImpl service;
	@Before
    public void setUp() {
    	repository.deleteAll();
    	
    	Player Paula    = new Player("Paula Marcolino");
    	Player Joao    = new Player("Joao Felipe Ghizi Assad");
    	Player Rodrigo    = new Player("Rodrigo Mello");
    	Player paulan    = new Player("Rodrigo Neves");
    	Player Carlos    = new Player("Carlos Estigarribia");
    	Player Jose    = new Player("Jose Costa Flor Junior");
    
    	
		
		List<Player> players = Arrays.asList(Paula, Joao,Rodrigo , paulan,Carlos,Jose);
		
		
		players.forEach(dto -> { 			
			
			player= service.cadastra(dto);
			System.out.println("teste id" +player.getId());
		});
		
    }

	@Test
	public void listaPlayersStatusCode200() {		
		given()
			.port(porta)
		.get("/api/player/")
		.then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(OK.value());
			
	}
	
	@Test
	public void criaPlayerStatusCode201() {
		
		Player func1    = new Player("Funcionario Gazeus Games 01");
		
		given()
			.port(porta)
			.request()
			.header("Accept", ContentType.JSON)
            .header("Content-type", ContentType.JSON)
            .body(func1)
        .when()
        .post("/api/player/")
        .then()
	        .log().headers().and()
	        .log().body().and()
	        .assertThat()
            .statusCode(CREATED.value());
	}
	@Test
	public void PlayerSemNomeStatusCode400() {
		Player func2    = new Player();
		
		given()
			.port(porta)
			.request()
			.header("Accept", ContentType.JSON)
            .header("Content-type", ContentType.JSON)
            .body(func2)
        .when()
        .post("/api/player/")
        .then()
	        .log().headers().and()
	        .log().body().and()
			.assertThat()
			.statusCode(BAD_REQUEST.value());
			
	}

}
