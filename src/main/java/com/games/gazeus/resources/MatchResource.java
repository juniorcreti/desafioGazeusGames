package com.games.gazeus.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import com.games.gazeus.domain.Game;
import com.games.gazeus.domain.Match;
import com.games.gazeus.domain.Player;
import com.games.gazeus.domain.dto.TopDTO;
import com.games.gazeus.response.Response;
import com.games.gazeus.services.GameServiceImpl;
import com.games.gazeus.services.MatchServiceImpl;
import com.games.gazeus.services.PlayerServiceImpl;
import com.games.gazeus.services.TopDTOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST GAMES")
public class MatchResource {
	@Autowired
	MatchServiceImpl matchServiceImpl;
	@Autowired
	GameServiceImpl gameServiceImpl;
	@Autowired
	PlayerServiceImpl playerServiceImpl;
	@Autowired
	TopDTOService topDTOService;

	@PostMapping(value = "/{game}/match/player/{playerId}/")
	@ApiOperation(value = "Cadastra Partidas do game Informado")
	public ResponseEntity<Response<Match>> cadastraMatch(@PathVariable(name = "game") String gameName,
			@PathVariable(name = "playerId") long playerId, @RequestParam("status") String status) {
		String msg;
		List<String> erros =new ArrayList<String>();
		Optional<Game> gameopt = gameServiceImpl.findByNameAndStatus(gameName, status);
		Game g = null;
		Player p = null;
		
		if (gameopt.isPresent()) {
			g = gameopt.get();
			Optional<Player> playeropt = playerServiceImpl.buscarPlayers(playerId);
			if (playeropt.isPresent()) {
				p = playeropt.get();
				erros.add("Partida do " + gameName + " foi castrado com sucesso");
				
				return ResponseEntity.created(null).body(new Response<Match>(this.matchServiceImpl.cadastra(g, p)));
			} else {
				erros.add("A partida do " + gameName + " não pode adicionado sem player um cadastrado");
				
			}
		} else {
			erros.add("A partida não pode ser adicionada sem um Game cadastrado");
			
		}

		return ResponseEntity.badRequest().body(new Response<Match>(erros));

	}

	@GetMapping(value = "/{game}/top")
	@ApiOperation(value = "Lista o Top players do game informado")
	public ResponseEntity<Response<List<TopDTO>>> listarTOPS(@PathVariable(name = "game") String gameName,
			@RequestParam("n") String n) {
		
				List<String> erros =new ArrayList<String>();
		
		 if(n==null){
			erros.add("O nome do Game é obrigatório");
			return ResponseEntity.status(400).body(new Response<List<TopDTO>>(erros));
		}
       int number;
	try {
		number = Integer.parseInt(n);
		return ResponseEntity.status(200).body(new Response<List<TopDTO>>(this.topDTOService.listarTop(gameName, number)));
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		erros.add("O nome do Game é obrigatório");
		erros.add(e.getMessage());
		return ResponseEntity.status(400).body(new Response<List<TopDTO>>(erros));
	}
		

	}

	@GetMapping(value = "/{game}/player/{id}")
	@ApiOperation(value = "Retorna a posição players no TOP do game informado")
	public ResponseEntity<Response<Integer>> retornaPosicaoTOPS(@PathVariable(name = "game") String gameName,
			@PathVariable(name = "id") long id) {
		List<String> erros =new ArrayList<String>();
		int posicao=playerServiceImpl.posicaoPlayer(gameName, id);
		if(posicao==0) {
			erros.add("A posição no Top do "+gameName+" não foi localizada");
			return ResponseEntity.status(400).body(new Response<Integer>(erros));
		}

		return ResponseEntity.status(200).body(new Response<Integer>(this.playerServiceImpl.posicaoPlayer(gameName, id)));

	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
	}
}
