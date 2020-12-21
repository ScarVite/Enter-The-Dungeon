package enterTheDungeon.game.level.raum;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.level.*;
import enterTheDungeon.resource.Texturen;

public class RaumOberklasse {

	protected int anzHindernis;
	protected int anzGegner;
	protected int anzFallen;
	protected int xSpawn;
	protected int ySpawn;

	protected ArrayList<Hindernis> hindernisliste;
	protected ArrayList<Gegner> gegnerliste;
	protected ArrayList<Falle> fallenliste;
	protected ArrayList<Portal> portalliste;
	protected ArrayList<RaumOberklasse> raumliste;
	protected RaumOberklasse raum;
	protected Portal portal;
	protected Gegner gegner;
	protected Rectangle spieler;
	protected Hindernis hindernis;
	protected Falle falle;
	protected Game game;
	protected Texturen tex;
	private int raumNr = 0;
	private int maxRaum;
	private Raum1 raum1;
	private Raum2 raum2;
	private Raum3 raum3;
	private Raum4 raum4;

	public RaumOberklasse(Game pGame, Texturen pTex) {
		this.game = pGame;
		this.tex = pTex;

		hindernisliste = new ArrayList<Hindernis>();
		gegnerliste = new ArrayList<Gegner>();
		fallenliste = new ArrayList<Falle>();
		portalliste = new ArrayList<Portal>();
		raumliste = new ArrayList<RaumOberklasse>();

	}

	protected void createBorder() {
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		hindernisliste.add(new Hindernis(0, 0, 20, height, tex)); // links
		hindernisliste.add(new Hindernis(width - 35, 0, 20, height, tex)); // rechts
		hindernisliste.add(new Hindernis(0, 0, width, 30, tex)); // oben
		hindernisliste.add(new Hindernis(0, height - 60, width, 30, tex)); // unten
	}

	public void erstelleLevel() {
		raumliste.add(raum1 = new Raum1(game, tex));
		raumliste.add(raum2 = new Raum2(game, tex));
		raumliste.add(raum3 = new Raum3(game, tex));
		raumliste.add(raum4 = new Raum4(game, tex));

		raum1.erstelleRaum();
		raum2.erstelleRaum();
		raum3.erstelleRaum();
		raum4.erstelleRaum();

		setMaxRaum(raumliste.size());

	}

	public void starteRaum(int pRaumNr) {
		setRaumNr(pRaumNr);

//		raum1.erstelleRaum();
//		raum2.erstelleRaum();
	}

	public void update() {
		tick();

	}

	protected void tick() {

		for (int i = 0; i < portalliste.size(); i++) {
			portalliste.get(i).update();
		}
		for (int i = 0; i < gegnerliste.size(); i++) {
			gegnerliste.get(i).update();
		}
		for (int i = 0; i < hindernisliste.size(); i++) {
			hindernisliste.get(i).update();
		}
		for (int i = 0; i < fallenliste.size(); i++) {
			fallenliste.get(i).update();
		}
	}

	public void render(Graphics g) {

	}

	protected void draw(Graphics g) {

		for (int i = 0; i < portalliste.size(); i++) {
			portalliste.get(i).render(g);
		}

		for (int i = 0; i < fallenliste.size(); i++) {
			fallenliste.get(i).render(g);
		}
		for (int i = 0; i < gegnerliste.size(); i++) {
			gegnerliste.get(i).render(g);
		}
		for (int i = 0; i < hindernisliste.size(); i++) {
			hindernisliste.get(i).render(g);
		}

	}

	public int getMaxRaum() {
		return maxRaum;
	}

	public void setMaxRaum(int maxRaum) {
		this.maxRaum = maxRaum;
	}

	public int getRaumNr() {
		return raumNr;
	}

	public void setRaumNr(int pRaumNr) {
		this.raumNr = pRaumNr;
	}

	public RaumOberklasse getRaum() {
		return raum;
	}

	public void setRaum(RaumOberklasse pRaum) {
		this.raum = pRaum;
	}

	public int getAnzHindernis() {
		return anzHindernis;
	}

	public void setAnzHindernis(int anzHindernis) {
		this.anzHindernis = anzHindernis;
	}

	public int getAnzGegner() {
		return anzGegner;
	}

	public void setAnzGegner(int anzGegner) {
		this.anzGegner = anzGegner;
	}

	public int getAnzFallen() {
		return anzFallen;
	}

	public void setAnzFallen(int anzFallen) {
		this.anzFallen = anzFallen;
	}

	public ArrayList<Hindernis> getHindernisliste() {
		return hindernisliste;
	}

	public void setHindernisliste(ArrayList<Hindernis> hindernisliste) {
		this.hindernisliste = hindernisliste;
	}

	public ArrayList<Gegner> getGegnerliste() {
		return gegnerliste;
	}

	public void setGegnerliste(ArrayList<Gegner> gegnerliste) {
		this.gegnerliste = gegnerliste;
	}

	public ArrayList<Falle> getFallenliste() {
		return fallenliste;
	}

	public void setFallenliste(ArrayList<Falle> fallenliste) {
		this.fallenliste = fallenliste;
	}

	public ArrayList<Portal> getPortalliste() {
		return portalliste;
	}

	public void setPortalliste(ArrayList<Portal> portalliste) {
		this.portalliste = portalliste;
	}

	public ArrayList<RaumOberklasse> getRaumliste() {
		return raumliste;
	}

	public void setRaumliste(ArrayList<RaumOberklasse> raumliste) {
		this.raumliste = raumliste;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	public Gegner getGegner() {
		return gegner;
	}

	public void setGegner(Gegner gegner) {
		this.gegner = gegner;
	}

	public Rectangle getSpieler() {
		return spieler;
	}

	public void setSpieler(Rectangle spieler) {
		this.spieler = spieler;
	}

	public Hindernis getHindernis() {
		return hindernis;
	}

	public void setHindernis(Hindernis hindernis) {
		this.hindernis = hindernis;
	}

	public Falle getFalle() {
		return falle;
	}

	public void setFalle(Falle falle) {
		this.falle = falle;
	}

	public void addPortal(Portal portal) {
		portalliste.add(portal);
	}

	public void addGegner(Gegner gegner) {
		gegnerliste.add(gegner);
	}

	public void addHindernis(Hindernis hindernis) {
		hindernisliste.add(hindernis);
	}

	public void addFalle(Falle falle) {
		fallenliste.remove(falle);
	}

	public void removePortal(Portal portal) {
		portalliste.remove(portal);
	}

	public void removeGegner(Gegner gegner) {
		gegnerliste.remove(gegner);
	}

	public void removeHindernis(Hindernis hindernis) {
		hindernisliste.remove(hindernis);
	}

	public void removeFalle(Falle falle) {
		fallenliste.remove(falle);
	}

	public void clearHindernisliste() {
		hindernisliste.clear();
	}
	
	public int getxSpawn() {
		return xSpawn;
	}

	public void setxSpawn(int xSpawn) {
		this.xSpawn = xSpawn;
	}

	public int getySpawn() {
		return ySpawn;
	}

	public void setySpawn(int ySpawn) {
		this.ySpawn = ySpawn;
	}

}
