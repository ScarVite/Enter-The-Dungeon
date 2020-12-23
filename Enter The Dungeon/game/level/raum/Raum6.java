package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.resource.Texturen;


public class Raum6 extends RaumOberklasse {

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
		//Links Oben
		for(int i=8;i<getAnzFallen();i++){
			falle = new Falle(910-offset, 20+offset, 100, 100, tex);
			fallenliste.add(falle);
			offset=offset+100;
			}	
		offset=0;
		//Links Unten
		for(int i=8;i<getAnzFallen();i++){
			falle = new Falle(510+offset, 520+offset, 100, 100, tex);
			fallenliste.add(falle);
			offset=offset+100;
			}	
		offset=0;
		//Rechts oben
		for(int i=8;i<getAnzFallen();i++){
			falle = new Falle(910+offset, 120+offset, 100, 100, tex);
			fallenliste.add(falle);
			offset=offset+100;
			}	
		offset=0;
		//Rechts unten
		for(int i=8;i<getAnzFallen();i++){
			falle = new Falle(1210-offset, 520+offset, 100, 100, tex);
			fallenliste.add(falle);
			offset=offset+100;
			}	
			
		for (int i = 150; i < width; i += width / 2) {
			
			gegner = new Gegner(i, 400, 65, 65, 3, 3, tex, game);
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
