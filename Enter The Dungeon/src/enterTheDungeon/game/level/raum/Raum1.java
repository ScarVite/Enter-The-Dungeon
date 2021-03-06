package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.resource.Texturen;


public class Raum1 extends RaumOberklasse {

	public Raum1(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(0);
		setAnzGegner(2);
		setRaum(this);
		setPortalX(1400);
		setPortalY(240);
		
	}

	public void erstelleRaum() {
		
		createBorder();
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		for (int i = 150; i < width; i += width / 4) {

			for (int j = 150; j < height; j += height / 3) {
				hindernis = new Hindernis(i, j, 100, 100, tex);
				hindernisliste.add(hindernis);
			}

		}
//		for (int i = 550; i < width; i += width / 1) {

			gegner = new Gegner(950, 800, 65, 65, 3, 2, 80,tex, game);
			gegnerliste.add(gegner);
			gegner = new Gegner(850, 600, 65, 65, 3, 2, 80,tex, game);
			gegnerliste.add(gegner);
//		}
		
//		for (int i = 150; i < width; i += width / 1) {
//
//			gegner = new Gegner(i, 700, 65, 65, 3, 2, tex, game);
//			gegnerliste.add(gegner);
//		}
		setHindernisliste(hindernisliste);
		setGegnerliste(gegnerliste);
		erstelleSubHindernisOben();
		erstelleSubHindernisUnten();
		erstelleSubHindernisRechts();
		erstelleSubHindernisLinks();

	}

	public void update() {
		tick();
	}

	public void render(Graphics g) {
		draw(g);
	}

}
