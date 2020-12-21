package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.resource.Texturen;

public class Raum1 extends RaumOberklasse {

	public Raum1(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(0);
		setAnzGegner(2);
		setRaum(this);
	}

	public void erstelleRaum() {
		
		System.out.println();
		createBorder();
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		for (int i = 150; i < width; i += width / 5) {

			for (int j = 150; j < height; j += height / 2) {
				hindernis = new Hindernis(i, j, 100, 100, tex);
				hindernisliste.add(hindernis);
			}

		}

		for (int i = 150; i < width; i += width / 2) {

			gegner = new Gegner(i, 400, 65, 65, 3, 3, tex, game);
			gegnerliste.add(gegner);
		}

		setHindernisliste(hindernisliste);
		setGegnerliste(gegnerliste);

	}

	public void update() {
		tick();
	}

	public void render(Graphics g) {
		draw(g);
	}

}
