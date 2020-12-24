package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Powerup;
import enterTheDungeon.game.level.Schadenpowerup;
import enterTheDungeon.game.level.Speedpowerup;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.game.level.Heartpowerup;
import enterTheDungeon.resource.Texturen;

public class Raum4 extends RaumOberklasse {

	public Raum4(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(4);
		setAnzGegner(5);
		setRaum(this);

		setxSpawn(400);
		setySpawn(400);
	}

	public void erstelleRaum() {

		System.out.println();
		createBorder();
		int width = 1920;
		int height = 1080;
		int offset = 1900;
		for (int i = 4; i < getAnzHindernis(); i++) {
			hindernis = new Hindernis(width - offset, 250, 100, 100, tex);
			hindernisliste.add(hindernis);
			offset = offset - 100;
		}
		hindernis = new Hindernis(520, 320, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(520, 420, 100, 100, tex);
		hindernisliste.add(hindernis);
		offset = 1700;
		for (int i = 6; i < getAnzHindernis(); i++) {
			hindernis = new Hindernis(width - offset, 520, 100, 100, tex);
			hindernisliste.add(hindernis);
			offset = offset - 100;
		}
		hindernis = new Hindernis(720, 920, 100, 100, tex);
		hindernisliste.add(hindernis);
		offset = 1060;
		for (int i = 3; i < getAnzHindernis(); i++) {
			hindernis = new Hindernis(720, height - offset, 100, 100, tex);
			hindernisliste.add(hindernis);
			offset = offset - 100;
		}

		hindernis = new Hindernis(820, 320, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1320, 320, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1220, 320, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1320, 20, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1320, 220, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1420, 520, 100, 100, tex);
		hindernisliste.add(hindernis);
		hindernis = new Hindernis(1790, 520, 100, 100, tex);
		hindernisliste.add(hindernis);
		offset = 160;
		for (int i = 4; i < getAnzHindernis(); i++) {
			hindernis = new Hindernis(1320, height - offset, 100, 100, tex);
			hindernisliste.add(hindernis);
			offset = offset + 100;
		}
		// Gegner Raum 1
		gegner = new Gegner(220, 650, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(320, 750, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(420, 850, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		// Gegner Raum 2
		gegner = new Gegner(120, 50, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(220, 150, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		// Gegner Raum 3
		gegner = new Gegner(820, 650, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(820, 550, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(820, 450, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		// Gegner Raum 4
		gegner = new Gegner(820, 250, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(820, 50, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(820, 150, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		// Gegner Raum 5
		gegner = new Gegner(1520, 550, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		gegner = new Gegner(1520, 750, 65, 65, 3, 3, 80, tex, game);
		gegnerliste.add(gegner);
		// Fallen
		falle = new Falle(120, 400, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(720, 720, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(720, 820, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(1320, 120, 100, 100, tex);
		fallenliste.add(falle);

				hindernis = new Hindernis(820,320 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1320,320 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1220,320 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1320,20 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1320,220 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1420,520 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1790,520 , 100, 100, tex);
				hindernisliste.add(hindernis);
				offset= 160;
				for(int i=4;i<getAnzHindernis();i++){
					hindernis = new Hindernis(1320,height-offset , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset+100;
					}	
					//Gegner Raum 1
					gegner = new Gegner(220, 650, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(320, 750, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(420, 850, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 2
					gegner = new Gegner(120, 50, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(220, 150, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 3
					gegner = new Gegner(820, 650, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(820, 550, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(820, 450, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 4
					gegner = new Gegner(820, 250, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(820, 50, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(820, 150, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 5
					gegner = new Gegner(1520, 550, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(1520, 750, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
			// Fallen 
						falle = new Falle(120, 400	, 100, 100, tex);
						fallenliste.add(falle);
						falle = new Falle(720, 720	, 100, 100, tex);
						fallenliste.add(falle);
						falle = new Falle(720, 820	, 100, 100, tex);
						fallenliste.add(falle);
						falle = new Falle(1320, 120	, 100, 100, tex);
						fallenliste.add(falle);
				
			//Powerups
						powerup = new Heartpowerup(50, 120	, 50, 50, tex, game);
						powerupliste.add(powerup);
		for (int i = 150; i < width; i += width / 2) {

			gegner = new Gegner(i, 400, 65, 65, 3, 3, 80,tex, game);
//			gegnerliste.add(gegner);
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
