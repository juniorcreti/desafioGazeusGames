package com.games.gazeus.resource;



import static io.restassured.RestAssured.given;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import com.games.gazeus.repositories.MatchRepository;
import com.games.gazeus.services.GameServiceImpl;

import io.restassured.http.ContentType;

import com.games.gazeus.DesafioGazeusGamesApplication;
import com.games.gazeus.domain.Game;
import com.games.gazeus.domain.Match;
import com.games.gazeus.domain.Player;
import com.games.gazeus.repositories.MatchRepository;
import com.games.gazeus.repositories.PlayerRepository;
import com.games.gazeus.services.GameServiceImpl;
import com.games.gazeus.services.MatchServiceImpl;
import com.games.gazeus.services.PlayerServiceImpl;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DesafioGazeusGamesApplication.class })
public class CMatchResourceTest {


	private Player player;
	private Game game;
	private Match match;
	int porta =8050;
	@Autowired
	private MatchRepository repository;
	
	@Autowired
	private MatchServiceImpl service;
	@Autowired
	private PlayerServiceImpl servicep;
	@Autowired
	private GameServiceImpl serviceg;
	


	private static final String NOME = "Truco";
	private static final String STATUS = "VICTORY";
	private static final long PLAYERID = 14;
	private static final long GAMEID = 20;
	@Before
    public void setUp() {
    	repository.deleteAll();
    	
    	Optional<Player> PaulaOpt    = servicep.buscarPlayers(14);
    	Player Paula=PaulaOpt.get();
    	Optional<Player> JoaoOpt    = servicep.buscarPlayers(15);
    	Player Joao=JoaoOpt.get();
    	Optional<Player> CarlosOpt    = servicep.buscarPlayers(18);
    	Player Carlos=CarlosOpt.get();
    	Optional<Player> JoseOpt    = servicep.buscarPlayers(19);
    	Player Jose=JoseOpt.get();
    	Optional<Player> RodrigoOpt    = servicep.buscarPlayers(17);
    	Player Rodrigo=RodrigoOpt.get();
    	Optional<Player> RodrigoMOpt    = servicep.buscarPlayers(16);
    	Player RodrigoM=RodrigoMOpt.get();
    	
        Game TrucoV= serviceg.buscaGame("Truco", "VICTORY");
        Game TrucoDe= serviceg.buscaGame("Truco", "DEFEAT");
        Game TrucoDr= serviceg.buscaGame("Truco", "DRAW");
        Match match= service.cadastra(TrucoV, Paula);
        match= service.cadastra(TrucoV, Paula);
        match= service.cadastra(TrucoV, Paula);
        match= service.cadastra(TrucoDe, Joao);
        match= service.cadastra(TrucoDr, Joao);
        match= service.cadastra(TrucoDr, Joao);
        
        match= service.cadastra(TrucoDe, Carlos);
        match= service.cadastra(TrucoDe, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        match= service.cadastra(TrucoV, Carlos);
        
    	
        match= service.cadastra(TrucoV, Jose);
        match= service.cadastra(TrucoV, Jose);
        match= service.cadastra(TrucoV, Jose);
        match= service.cadastra(TrucoV, Jose);
        match= service.cadastra(TrucoV, Jose);
        
        
        match= service.cadastra(TrucoV, Rodrigo);
        match= service.cadastra(TrucoV, Rodrigo);
        match= service.cadastra(TrucoV, Rodrigo);
        match= service.cadastra(TrucoDe, Rodrigo);
        match= service.cadastra(TrucoDe, Rodrigo);
        match= service.cadastra(TrucoDr, Rodrigo);
        match= service.cadastra(TrucoV, Rodrigo);
        match= service.cadastra(TrucoV, Rodrigo);
        match= service.cadastra(TrucoDr, Rodrigo);
        match= service.cadastra(TrucoDr, Rodrigo);
        match= service.cadastra(TrucoV, Rodrigo);
        
        
        
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoV, RodrigoM);
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoDr, RodrigoM);
        match= service.cadastra(TrucoV, RodrigoM);
		
    }

	
	@Test
	public void criaMatchStatusCode201() {
		
		Player func1    = new Player("Funcionario Gazeus Games 01");
		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.pathParam("playerId", PLAYERID)
			.queryParam("status", STATUS)
			.request()
			.header("Accept", ContentType.JSON)
            .header("Content-type", ContentType.JSON)
            .body(func1)
            .when()
            .post("/api/{game}/match/player/{playerId}/")
            .then()
	        .log().headers().and()
	        .log().body().and()
	        .assertThat()
            .statusCode(CREATED.value());
	}
	@Test
	public void criaMatchSemNomeStatusCode400() {
		
		
		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.pathParam("playerId", 1)
			.queryParam("status", STATUS)
			.request()
			.header("Accept", ContentType.JSON)
            .header("Content-type", ContentType.JSON)
            .when()
            .post("/api/{game}/match/player/{playerId}/")
            .then()
	        .log().headers().and()
	        .log().body().and()
	        .assertThat()
            .statusCode(BAD_REQUEST.value());
	}
	
	@Test
	public void listaPlayerTOPStatusCode200() {		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.queryParam("n", 10)
	    	.get("/api/{game}/top")
	   	    .then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(OK.value());
			
	}
	@Test
	public void listaPlayerTOPStatusCode400() {		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.get("/api/{game}/top")
		    .then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(BAD_REQUEST.value());
			
	}
	
	@Test
	public void retornaPosicaoTOPSCode200() {		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.pathParam("id", PLAYERID)
			.get("/api/{game}/player/{id}")
	   	    .then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(OK.value());
			
	}
	@Test
	public void retornaPosicaoTOPSCode400() {		
		given()
			.port(porta)
			.pathParam("game", NOME)
			.pathParam("id", 1)
			.get("/api/{game}/player/{id}")
		    .then()
			.log().headers().and()
			.log().body().and()
			.assertThat()
			.statusCode(BAD_REQUEST.value());
			
	}
	

}
