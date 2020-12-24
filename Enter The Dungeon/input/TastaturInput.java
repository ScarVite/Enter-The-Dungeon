package enterTheDungeon.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Pausemenu;


public class TastaturInput extends KeyAdapter{
	
	private Game game;
	private Pausemenu pausemenu;
	
	
	public TastaturInput(Game pGame,Pausemenu pPausemenu) {
		this.pausemenu = pPausemenu;
		this.game = pGame;
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
		//soll erst gemacht werden wenn Pausemenü erzeugt wird
		if(pausemenu!=null) {
		pausemenu.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
	
	
}