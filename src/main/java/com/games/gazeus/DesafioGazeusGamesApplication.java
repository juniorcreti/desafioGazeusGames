package com.games.gazeus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.games.gazeus.domain.Game;
import com.games.gazeus.services.GameServiceImpl;
import com.games.gazeus.services.MatchServiceImpl;
import com.games.gazeus.services.PlayerServiceImpl;
import com.games.gazeus.services.TopDTOService;

@EnableCaching
@SpringBootApplication
public class DesafioGazeusGamesApplication implements CommandLineRunner {

	@Autowired
	GameServiceImpl gameService;
	@Autowired
	PlayerServiceImpl playerService;
	@Autowired
	MatchServiceImpl matchService;
	@Autowired
	TopDTOService top;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioGazeusGamesApplication.class, args);
	}
	

  

	@Override
	public void run(String... args) throws Exception {
		/*Game game = new Game("Buraco","VICTORY",100);
		gameService.cadastrarGame(game); 
		game = new Game("Buraco","DEFEAT",-20);
		 gameService.cadastrarGame(game);
		 game = new Game("Buraco","DRAW",50);
		gameService.cadastrarGame(game);
		 game = new Game("Domino","VICTORY",150);
		gameService.cadastrarGame(game); 
		game = new Game("Domino","DEFEAT",-30);
		 gameService.cadastrarGame(game);
		 game = new Game("Domino","DRAW",75);
		gameService.cadastrarGame(game);
		 game = new Game("Truco","VICTORY",200);
		gameService.cadastrarGame(game); 
		game = new Game("Truco","DEFEAT",-50);
		 gameService.cadastrarGame(game);
		 game = new Game("Truco","DRAW",100);
		gameService.cadastrarGame(game);
		   
		 
	/*	Game g= gameService.findByNameAndStatus("Domino", "VICTORY");  
		Game g2= gameService.findByNameAndStatus("Domino", "DEFEAT"); 
		System.out.println("TESTE "+g.getName()+g.getStatus());
	Player p = playerService.listarPlayers(10);   
	Player p2 = playerService.listarPlayers(11); 

		 
		
		matchService.cadastra(g, p);  
		matchService.cadastra(g, p);
		matchService.cadastra(g, p);  
		matchService.cadastra(g, p);
		matchService.cadastra(g, p);  
		matchService.cadastra(g, p);
		matchService.cadastra(g2, p2);
		matchService.cadastra(g2, p2);
		matchService.cadastra(g2, p2);
		matchService.cadastra(g2, p2);
		List<TopDTO> tops=top.listarTop("Buraco",0);
		for(TopDTO itens:tops) {
			System.out.println("Id :"+itens.getId()+" Nome "+itens.getNamePlayer()+" jogo "+itens.getNameGame()+" Total: "+itens.getTotalPoints());
		}
*/		
	}

}
