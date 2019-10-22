package com.games.gazeus.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.games.gazeus.response.Response;
import com.games.gazeus.domain.Player;
import com.games.gazeus.services.PlayerServiceImpl;

@RestController
@RequestMapping(value = "/api/player")
public class PlayerResource {
	@Autowired
	PlayerServiceImpl playerService;

	@PostMapping("/")
	public ResponseEntity<Response<Player>> cadastraPlayer(@Valid @RequestBody Player player, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros =new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Player>(erros));
		}else {
		return ResponseEntity.status(201).body(new Response<Player>(this.playerService.cadastra(player)));
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Player>> listarPlayer() {
		return ResponseEntity.status(200).body(playerService.listarAllPlayers());
	}

}
