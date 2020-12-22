package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.resource.Texturen;


public class Raum3 extends RaumOberklasse {

	public Raum3(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(0);
		setAnzGegner(2);
		setRaum(this);
		
		setxSpawn(50);
		setySpawn(150);
	}

	public void erstelleRaum() {
		
		System.out.println();
		createBorder();
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		int offset = 0;
		for(int i=2;i<getAnzHindernis();i++){
			hindernis = new Hindernis(20+offset,20+offset , 100, 100, tex);
			hindernisliste.add(hindernis);
			offset=offset+100;
			}
		offset=0;
		for(int i=2;i<getAnzHindernis();i++){
			hindernis = new Hindernis(1420-offset,920-offset , 100, 100, tex);
			hindernisliste.add(hindernis);
			offset=offset+100;
			}
		offset=0;
		for(int i=1;i<getAnzHindernis();i++){
			hindernis = new Hindernis(985-offset,20-offset , 100, 100, tex);
			hindernisliste.add(hindernis);
			offset=offset-100;
			}
		falle = new Falle(720, 820	, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(720, 920	, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(720, 20	, 100, 100, tex);
		fallenliste.add(falle);
		falle = new Falle(720, 120	, 100, 100, tex);
		fallenliste.add(falle);

		for (int i = 150; i < width; i += width / 4) {

			gegner = new Gegner(i, 900, 65, 65, 3, 3, tex, game);
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
