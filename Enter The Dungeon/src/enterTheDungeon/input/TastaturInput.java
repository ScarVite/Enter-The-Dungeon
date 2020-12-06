package enterTheDungeon.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import enterTheDungeon.game.Game;


public class TastaturInput extends KeyAdapter{
	
	private Game game;
	
	
	public TastaturInput(Game pGame) {
		this.game = pGame;
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}

