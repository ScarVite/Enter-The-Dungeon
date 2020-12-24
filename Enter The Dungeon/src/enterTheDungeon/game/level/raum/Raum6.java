package enterTheDungeon.game.level.raum;

import java.awt.Graphics;
import java.awt.Rectangle;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.resource.Texturen;

public class Raum6 extends RaumOberklasse {

	private Waffe waffe;
	private boolean spawnGegner = false;

	public Raum6(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(12);
		setAnzGegner(5);
		setRaum(this);
		setxSpawn(50);
		setySpawn(200);
	}

	public void erstelleRaum() {

		System.out.println();
		createBorder();
		int width = 1920;
		int height = 1080;
		int offset = 100;
		// Links Oben
		for (int i = 8; i < getAnzFallen(); i++) {
			falle = new Falle(910 - offset, 20 + offset, 100, 100, tex);
			fallenliste.add(falle);
			offset = offset + 100;
		}
		offset = 0;
		// Links Unten
		for (int i = 8; i < getAnzFallen(); i++) {
			falle = new Falle(510 + offset, 520 + offset, 100, 100, tex);
			fallenliste.add(falle);
			offset = offset + 100;
		}
		offset = 0;
		// Rechts oben
		for (int i = 8; i < getAnzFallen(); i++) {
			falle = new Falle(910 + offset, 120 + offset, 100, 100, tex);
			fallenliste.add(falle);
			offset = offset + 100;
		}
		offset = 0;
		// Rechts unten
		for (int i = 8; i < getAnzFallen(); i++) {
			falle = new Falle(1210 - offset, 520 + offset, 100, 100, tex);
			fallenliste.add(falle);
			offset = offset + 100;
		}

		// Boss
		gegner = new Gegner(1000, 500, 100, 100, 50, 0.5, 10, tex, game);
		gegner.setVision(new Rectangle(0, 0, 1920, 1080));
		waffe = gegner.getWaffe();
		waffe.setReichweite(1000);
		gegnerliste.add(gegner);

		for (int i = 150; i < width; i += width / 2) {

			gegner = new Gegner(i, 400, 65, 65, 3, 3, 80, tex, game);
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
		if (!gegnerliste.isEmpty()) {
			int zahl = gegnerliste.get(0).getLeben();
			if (zahl == 40 && !isSpawnGegner()) {
				for (int i = 0; i < 5; i++) {
					gegner = new Gegner(1000, 500, 65, 65, 3, 1.5, 80, tex, game);
					gegnerliste.add(gegner);
				}
				setSpawnGegner(true);
			}
			if (zahl == 39) {
				setSpawnGegner(false);
			}

			if (zahl == 30 && !isSpawnGegner()) {
				for (int i = 0; i < 10; i++) {
					gegner = new Gegner(1000, 500, 65, 65, 3, 1.5, 80, tex, game);
					gegnerliste.add(gegner);
				}
				setSpawnGegner(true);
			}
			if (zahl == 29) {
				setSpawnGegner(false);
			}

			if (zahl == 20 && !isSpawnGegner()) {
				for (int i = 0; i < 15; i++) {
					gegner = new Gegner(1000, 500, 65, 65, 3, 1.5, 80, tex, game);
					gegnerliste.add(gegner);
				}
				setSpawnGegner(true);
			}
			if(zahl == 19) {
				setSpawnGegner(false);
			}
			if (zahl == 10 && !isSpawnGegner()) {
				for (int i = 0; i < 20; i++) {
					gegner = new Gegner(1000, 500, 65, 65, 3, 1.5, 80, tex, game);
					gegnerliste.add(gegner);
				}
				setSpawnGegner(true);

			}
			if(zahl == 9) {
				setSpawnGegner(false);
			}
			if (zahl == 2 && !isSpawnGegner()) {
				for (int i = 0; i < 25; i++) {
					gegner = new Gegner(1000, 500, 65, 65, 3, 1.5, 80, tex, game);
					gegnerliste.add(gegner);
				}
				setSpawnGegner(true);
			}
			if(zahl == 1) {
				setSpawnGegner(false);
			}
			setGegnerliste(gegnerliste);
		}

		tick();

	}

	public void render(Graphics g) {
		draw(g);
	}

	public boolean isSpawnGegner() {
		return spawnGegner;
	}

	public void setSpawnGegner(boolean spawnGegner) {
		this.spawnGegner = spawnGegner;
	}
}