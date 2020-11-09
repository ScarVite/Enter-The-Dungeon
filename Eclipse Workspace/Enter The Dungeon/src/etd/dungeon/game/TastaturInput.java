package etd.dungeon.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
