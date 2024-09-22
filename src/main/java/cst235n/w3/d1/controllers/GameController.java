package cst235n.w3.d1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cst235n.w3.d1.models.Game;


import cst235n.w3.d1.services.Turn;
import cst235n.w3.d1.services.UnoGameAPI;

@RestController
@CrossOrigin(maxAge = 3600)
public class GameController {
	@PostMapping("/games1")
	Turn postGame(@RequestBody Game game) {
		UnoGameAPI gameAPI = UnoGameAPI.getGame(game.getGameId());
		System.out.println(game.getGameId());
		return gameAPI.getTurn();
	}

	// Use the UnoGameAPI to
	// 1. Return the game name of an existing name
	// 2. Or create a new game and return its name
	

	@GetMapping("/games1")
	List<Game> getGames() {
		List<String> names = UnoGameAPI.getGameNames();
		List<Game> games = new ArrayList<Game>();
		for (int i = 0; i < names.size(); i++) {
			Game game = new Game(names.get(i));
			games.add(game);
		
		 }
		return games;
	}
		// Use the UnoGameAPI to
		// 1. Get all existing game names
		// 2. This is a new method for the UnoGameAPI
	@GetMapping("/games1/{name}")
	Turn getGames(@PathVariable String name) {
		List<String> names = UnoGameAPI.getGameNames();
		
		if (names.contains(name)) {
			UnoGameAPI game = UnoGameAPI.getGame(name);
		return game.getTurn();
	}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	

	@PutMapping("/games1")
	Turn getTurn(@RequestBody Game game) {
		
		UnoGameAPI game1 = UnoGameAPI.getGame(game.getGameId());
		
		if (game1.nextTurn()) {
			Turn t = game1.getTurn();
			return t;
		}
		return null;
		// Use the UnoGameAPI to
		// 1. Get a game turn for the given game key (name)
		// 2. Do not create a game if the game key is unknown
		// 3. Instead, when game key is not known:
		// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find
		// resource");
	}
	
	@DeleteMapping("/games1")
	Game deleteGame(@RequestBody Game game) {
	System.out.println("deleting " + game.getGameId());
	UnoGameAPI gameAPI = UnoGameAPI.deleteGame(game.getGameId());
	
	if (gameAPI != null) {
		System.out.println("success");
		return new Game(game.getGameId());
	}
	System.out.println("failure");
	
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
	}

}

















