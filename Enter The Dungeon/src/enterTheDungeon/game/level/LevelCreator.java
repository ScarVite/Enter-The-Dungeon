package enterTheDungeon.game.level;

import java.awt.Rectangle;
import java.util.ArrayList;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Spieler;
import enterTheDungeon.game.waffen.Schuss;
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
	private double x, y, width, height; // f�r Hindernisse

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
		createBorder();
		int screeenwidth = game.getScreenwidth();
		int screenheight = game.getScreenheight();
		Rectangle spielerRect = game.spielerBounds();

		for (int i = 0; i < game.getAnzHindernis(); i++) {
			setX(Math.random() * (game.getScreenwidth() - 180) + 30);
			setY(Math.random() * (game.getScreenheight() - 210) + 30);
			setWidth(150);
			setHeight(width);
			hindernis = new Hindernis(x, y, width, height, tex);
			hindernisliste.add(hindernis);
		}

		for (int i = 0; i < game.getAnzGegner(); i++) {
			setX(Math.random() * (game.getScreenwidth() - 30 - 65) + 30);
			setY(Math.random() * (game.getScreenheight() - 80 - 65) + 30);
			setWidth(100);
			setHeight(100);
			gegner = new Gegner(x, y, getWidth(), getHeight(), 3, 3, 80,tex, game);
			gegnerliste.add(gegner);
		}

		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();

			while (spielerRect.intersects(hindi)) {
				hindernis.setxPos(Math.random() * 1000 + 1);
				hindernis.setyPos(Math.random() * 900 + 1);
				hindernis.setWidth(150);
				hindernis.setHeight(150);
				hindernis.setBounds((int) hindernis.getxPos(), (int) hindernis.getyPos(), (int) hindernis.getWidth(), (int) hindernis.getHeight());
				hindi = hindernis.getBounds();
			}

			for (Gegner gegner : gegnerliste) {
				Rectangle gegC = gegner.getBounds();
				while (hindi.intersects(gegC)) {
					System.out.println("Rein da");
					gegner.setxPos(Math.random() * (game.getScreenwidth() - 30 - gegner.getWidth()) + 30);
					gegner.setyPos(Math.random() * (game.getScreenheight() - 80- gegner.getHeight()) + 30);
					gegner.setWidth(100);
					gegner.setHeight(100);
					gegner.setBounds((int) gegner.getxPos(), (int) gegner.getyPos(), (int) gegner.getWidth(),
							(int) gegner.getHeight());
					gegC = gegner.getBounds();
				}

			}
		}

	}

	private void createBorder() {
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		hindernisliste.add(new Hindernis(0, 0, 20, height, tex)); // links
		hindernisliste.add(new Hindernis(width - 20, 0, 20, height, tex)); // rechts
		hindernisliste.add(new Hindernis(0, 0, width, 30, tex)); // oben
		hindernisliste.add(new Hindernis(0, height - 80, width, 30, tex)); //unten
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