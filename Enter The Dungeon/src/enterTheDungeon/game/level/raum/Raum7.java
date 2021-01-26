package enterTheDungeon.game.level.raum;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.Falle;
import enterTheDungeon.game.level.Hindernis;
import enterTheDungeon.game.level.Gegner;
import enterTheDungeon.resource.Texturen;


public class Raum7 extends RaumOberklasse {

	public Raum7(Game pGame, Texturen pTex) {
		super(pGame, pTex);
		setAnzHindernis(10);
		setAnzFallen(10);
		setAnzGegner(5);
		setRaum(this);
		setxSpawn(100);
		setySpawn(100);
	}

	public void erstelleRaum() {
		
		System.out.println();
		createBorder();
		int width = 1920;
		int height = 1080;
		int offset = 1900;
				for(int i=5;i<getAnzHindernis();i++){
					hindernis = new Hindernis(width-offset,220 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}	
				offset = 1700;
				for(int i=5;i<getAnzHindernis();i++){
					hindernis = new Hindernis(width-offset,520 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}
				offset= 1100;
				for(int i=7;i<getAnzHindernis();i++){
					hindernis = new Hindernis(width-offset,520 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}
				offset=900;
				for(int i=3;i<getAnzHindernis();i++){
					hindernis = new Hindernis(width-offset,220 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}
				offset= 500;
				for(int i=5;i<getAnzHindernis();i++){
					hindernis = new Hindernis(width-offset,20 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}
				offset= 1060;
				for(int i=3;i<getAnzHindernis();i++){
					hindernis = new Hindernis(720,height-offset , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset-100;
					}
				offset = 160;
				for(int i=1;i<getAnzHindernis();i++){
					hindernis = new Hindernis(1820,height-offset , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset+100;
					}
				offset=100;
				for(int i=7;i<getAnzHindernis();i++){
					hindernis = new Hindernis(1420+offset,520 , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset+100;
				}
		

				hindernis = new Hindernis(1320,320 , 100, 100, tex);
				hindernisliste.add(hindernis);

				hindernis = new Hindernis(1320,20 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(1320,220 , 100, 100, tex);
				hindernisliste.add(hindernis);
				hindernis = new Hindernis(720,920 , 100, 100, tex);
				hindernisliste.add(hindernis);
				offset= 160;
				for(int i=4;i<getAnzHindernis();i++){
					hindernis = new Hindernis(1320,height-offset , 100, 100, tex);
					hindernisliste.add(hindernis);
					offset=offset+100;
					}	
					//Gegner Raum 1
					gegner = new Gegner(220, 400, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(120, 400, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 2
					gegner = new Gegner(20, 750, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(20, 850, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(20, 950, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 3
					gegner = new Gegner(820, 750, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(820, 850, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
//					//Gegner Raum 4
					gegner = new Gegner(820, 400, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 5
					gegner = new Gegner(820, 140, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);	
					//Gegner Raum 6
					gegner = new Gegner(1620, 400, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					//Gegner Raum 7
					gegner = new Gegner(1620, 700, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);
					gegner = new Gegner(1620, 900, 65, 65, 3, 3, 80, tex, game);
					gegnerliste.add(gegner);

			// Fallen 
				//Ersten beiden Fallen
				falle = new Falle(520, 220	, 100, 100, tex);
				fallenliste.add(falle);
				falle = new Falle(620, 220	, 100, 100, tex);
				fallenliste.add(falle);
				// 2ten Fallen
				falle = new Falle(20, 520	, 100, 100, tex);
				fallenliste.add(falle);
				falle = new Falle(120, 520	, 100, 100, tex);
				fallenliste.add(falle);
				//3ten Fallen
				falle = new Falle(720, 720	, 100, 100, tex);
				fallenliste.add(falle);
				falle = new Falle(720, 820	, 100, 100, tex);
				fallenliste.add(falle);
				//4ten Fallen
				falle = new Falle(1120, 520	, 100, 100, tex);
				fallenliste.add(falle);
				falle = new Falle(1220, 520	, 100, 100, tex);
				fallenliste.add(falle);
				//5ten Fallen
				falle = new Falle(820, 220	, 100, 100, tex);
				fallenliste.add(falle);
				falle = new Falle(920, 220	, 100, 100, tex);
				fallenliste.add(falle);
				//Death Valley of Fallen 
				offset=100;
				for(int i=5;i<getAnzFallen();i++){
					falle = new Falle(1220+offset, 120, 100, 100, tex);
					fallenliste.add(falle);
					offset=offset+100;
					}
				falle = new Falle(1720, 220	, 100, 100, tex);
				fallenliste.add(falle);
				//7ten Fallen
				falle = new Falle(1420, 520	, 100, 100, tex);
				fallenliste.add(falle);
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
