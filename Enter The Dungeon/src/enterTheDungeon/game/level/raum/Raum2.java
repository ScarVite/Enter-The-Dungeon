package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.resource.Texturen;

public class Raum2 extends RaumOberklasse {

	public Raum2(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(6);
		setAnzFallen(4);
		setAnzGegner(3);
		setRaum(this);
		
		setxSpawn(100);
		setySpawn(500);
	}

	public void erstelleRaum() {
		createBorder();
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		for (int i = 150; i < width; i += width / 3) {

			for (int j = 150; j < height; j += height / 2) {
				hindernis = new Hindernis(i, j, 100, 100, tex);
				hindernisliste.add(hindernis);
				
				for (int b = 300; b < width; b += width / anzFallen) {

					falle = new Falle(j + 100, b + 70, 100, 100, tex);
					fallenliste.add(falle);
				}
			}

		}

		for (int i = 150; i < width; i += width / anzGegner) {

			gegner = new Gegner(i, 400, 65, 65, 3, 2, 80, tex, game);
			gegnerliste.add(gegner);
		}
		
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
