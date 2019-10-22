package com.games.gazeus.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.games.gazeus.domain.Game;
import com.games.gazeus.services.GameServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/games")	
@Api(value = "API REST GAMES")
public class GameResource {
	@Autowired
	GameServiceImpl gameService; 
	
	
	@GetMapping("/")
	@ApiOperation(value = "Retorna uma lista de Games")
	public ResponseEntity<List<Game>> listarGames() {
		return ResponseEntity.status(200).body(gameService.listarAllGame());
		}

}
