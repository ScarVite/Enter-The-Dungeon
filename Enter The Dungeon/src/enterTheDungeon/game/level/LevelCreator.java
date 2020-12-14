package enterTheDungeon.game.level;

import java.awt.Rectangle;
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
	private Rectangle spieler;
	private Hindernis hindernis;
	private double x, y, width, height; // f√ºr Hindernisse

	public LevelCreator(Game pGame, Texturen pTex) {

		// ArrayList initialsieren
		gegnerliste = new ArrayList<Gegner>();
		hindernisliste = new ArrayList<Hindernis>();
		portalliste = new ArrayList<Portal>();

		// Variablen deklarieren
		this.game = pGame;
		this.tex = pTex;

//		gegner = new Gegner(400, 400, 65, 65, 3, 3, tex);
//		gegnerliste.add(gegner);
//		hindernis = new Hindernis(50, 100, 25, 25, tex);
	}

	public void createLevel() {
		System.out.println("neue Runde");
		createBorder();
		int screeenwidth = game.getScreenwidth();
		int screenheight = game.getScreenheight();
		Rectangle spielerRect = game.spielerBounds();

		for (int i = 0; i < game.getAnzHindernis(); i++) {
			setX(Math.random() * 800 + 1);
			setY(Math.random() * 800 + 1);
			setWidth(150);
			setHeight(width);
			hindernis = new Hindernis(x, y, width, height, tex);
			hindernisliste.add(hindernis);
		}

		for (int i = 0; i < game.getAnzGegner(); i++) {
			setX(Math.random() * 1000 + 1);
			setY(Math.random() * 900 + 1);
			setWidth(100);
			setHeight(100);
			gegner = new Gegner(x, y, getWidth(), getHeight(), 3, 3, tex, game);
			gegnerliste.add(gegner);
		}

		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			for (Gegner gegner : gegnerliste) {
				Rectangle gegRect = gegner.getBounds();
				while (hindi.intersects(gegRect)) {
					System.out.println("Rein Da 1");
					System.out.println(gegner.getBounds() + "unver‰ndertd");
					gegner.setxPos(Math.random() * 1000 + 1);
					gegner.setyPos(Math.random() * 900 + 1);
					gegner.setWidth(100);
					gegner.setHeight(100);
					gegner.setBounds(gegner.getxPos(), gegner.getyPos(), gegner.getWidth(), gegner.getHeight());
					System.out.println(gegner.getBounds());
					gegner.ausgebenPos(gegner.getxPos(), gegner.getyPos());
					gegRect = gegner.getBounds();
					if (gegRect.intersects(hindi)) {
						System.out.println("getroffen");
					}
				}

			}
		}

		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();

			while (spielerRect.intersects(hindi)) {
				System.out.println("rein da spieler");
				hindernis.setxPos(Math.random() * 1000 + 1);
				hindernis.setyPos(Math.random() * 900 + 1);
				hindernis.setWidth(150);
				hindernis.setHeight(150);
				hindernis.setBounds(hindernis.getxPos(), hindernis.getyPos(), hindernis.getWidth(),
						hindernis.getHeight());
				hindi = hindernis.getBounds();
			}
		}
	}

	private void createBorder() {
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		hindernisliste.add(new Hindernis(0, 0, 20, height, tex)); // links
		hindernisliste.add(new Hindernis(width - 20, 0, 20, height, tex)); // rechts
		hindernisliste.add(new Hindernis(0, 0, width, 30, tex)); // oben
		hindernisliste.add(new Hindernis(0, height - 80, width, 30, tex));
	}

	public void setSpielerRect(Rectangle pSpieler) {
		this.spieler = pSpieler;
	}

	public void addPortal(Portal pPortal) {
		portalliste.add(pPortal);
	}

	public void removePortal(Portal pPortal) {
		portalliste.remove(pPortal);
	}

	public ArrayList<Portal> getPortalliste() {
		return portalliste;
	}

	public ArrayList<Gegner> getGegnerliste() {
		return gegnerliste;
	}

	public ArrayList<Hindernis> getHindernisliste() {
		return hindernisliste;
	}

	public void clearGegnerliste() {
		gegnerliste.clear();
	}

	public void clearHindernisliste() {
		hindernisliste.clear();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
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
