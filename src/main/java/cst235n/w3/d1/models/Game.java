package cst235n.w3.d1.models;

import org.springframework.stereotype.Component;

@Component 
	public class Game {
		private String gameId;
		public Game() {
			super();
		}
		
		public String getGameId() {
			return gameId;
		}
		public Game(String gameId) {
			super();
			this.gameId = gameId;
		}

		@Override
		public String toString() {
			return "Game [gameId=" + gameId + "]";
		}
	}

