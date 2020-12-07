package enterTheDungeon.game.level;

import java.util.ArrayList;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Gegner;
import enterTheDungeon.game.Hindernis;
import enterTheDungeon.game.Schuss;
import enterTheDungeon.game.Spieler;
import enterTheDungeon.resource.Texturen;

public class LevelCreator {

	private Game game;
	private Texturen tex;
	private ArrayList<Gegner> gegnerliste;
	private ArrayList<Hindernis> hindernisliste;
	private ArrayList<Portal> portalliste;
	private Portal portal;
	private Gegner gegner;
	private Hindernis hindernis;

	public LevelCreator(Game pGame, Texturen pTex) {

		// ArrayList initialsieren
		gegnerliste = new ArrayList<Gegner>();
		hindernisliste = new ArrayList<Hindernis>();
		

		// Variablen deklarieren
		this.game = pGame;
		this.tex = pTex;
		
//		gegner = new Gegner(400, 400, 65, 65, 3, 3, tex);
//		gegnerliste.add(gegner);
//		hindernis = new Hindernis(50, 100, 25, 25, tex);
	}

	public void createLevel() {
		createBorder();
		double x, y, width, height;
		for (int i = 0; i < game.getAnzGegner(); i++) {
			x = Math.random() * 1000 + 1;
			y = Math.random() * 900 + 1;
			width = 65;
			height = 65;
			gegner = new Gegner(x, y, width, height, 3, 3, tex, game);
			gegnerliste.add(gegner);
		}
		for(int i = 0; i < game.getAnzHindernis(); i++) {
			x = Math.random() * 1000 + 1;
			y = Math.random() * 900 + 1;
			width = Math.random() * 100 + 65;
			height = width;
			hindernis = new Hindernis(x, y, width, height, tex);
			hindernisliste.add(hindernis);
		}

	}
	
	
	
	private void createBorder() {
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		hindernisliste.add(new Hindernis(0, 0, 20, height, tex)); //links
		hindernisliste.add(new Hindernis(width - 20, 0, 20, height, tex)); //rechts
		hindernisliste.add(new Hindernis(0, 0, width, 30, tex)); //oben
		hindernisliste.add(new Hindernis(0, height - 80, width, 30, tex));
	}
	
	
	public void addPortal(Portal pPortal) {
		portalliste.add(pPortal);
	}
	
	public void removePortal(Portal pPortal) {
		portalliste.remove(pPortal);
	}
	
	public ArrayList<Portal> getPortalliste(){
		return portalliste;
	}
	
	public ArrayList<Gegner> getGegnerliste() {
		return gegnerliste;
	}
	
	public ArrayList<Hindernis> getHindernisliste(){
		return hindernisliste;
	}

	public void addGegner(Gegner pGegner) {
		gegnerliste.add(pGegner);
	}

	public void removeGegner(Gegner pGegner) {
		gegnerliste.remove(pGegner);
	}

	public void addHindernis(Hindernis pHindernis) {
		hindernisliste.add(pHindernis);
	}

	public void removeHindernis(Hindernis pHindernis) {
		hindernisliste.remove(pHindernis);
	}

}
