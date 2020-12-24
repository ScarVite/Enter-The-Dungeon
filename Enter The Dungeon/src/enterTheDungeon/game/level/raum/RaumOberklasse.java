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
	protected int anzPowerup;


	protected int xSpawn;
	protected int ySpawn;

	protected ArrayList<Hindernis> hindernisliste;
	protected ArrayList<Gegner> gegnerliste;
	protected ArrayList<Falle> fallenliste;
	protected ArrayList<Powerup> powerupliste;
	protected ArrayList<Portal> portalliste;
	protected ArrayList<RaumOberklasse> raumliste;
	private ArrayList<Hindernis> hindernisOben, hindernisUnten, hindernisRechts, hindernisLinks;

	protected RaumOberklasse raum;
	protected Portal portal;
	protected Gegner gegner;
	protected Rectangle spieler;
	protected Hindernis hindernis;
	protected Powerup powerup;
	protected Falle falle;
	protected Game game;
	protected Texturen tex;
	private int raumNr = 0;
	private int maxRaum;
	private Raum1 raum1;
	private Raum2 raum2;
	private Raum3 raum3;
	private Raum4 raum4;
	private Raum5 raum5;
	private Raum6 raum6;
	private Raum7 raum7;

	public RaumOberklasse(Game pGame, Texturen pTex) {
		this.game = pGame;
		this.tex = pTex;

		gegnerliste = new ArrayList<Gegner>();
		fallenliste = new ArrayList<Falle>();
		portalliste = new ArrayList<Portal>();
		raumliste = new ArrayList<RaumOberklasse>();
		powerupliste = new ArrayList<Powerup>();

		hindernisliste = new ArrayList<Hindernis>();
		// SubHindnernis fuer Gegner KI
		hindernisOben = new ArrayList<Hindernis>();
		hindernisUnten = new ArrayList<Hindernis>();
		hindernisRechts = new ArrayList<Hindernis>();
		hindernisLinks = new ArrayList<Hindernis>();

	}

	protected void createBorder() {
		int width = game.getScreenwidth();
		int height = game.getScreenheight();
		hindernisliste.add(new Hindernis(0, 0, 20, height, tex)); // links
		hindernisliste.add(new Hindernis(width - 35, 0, 20, height, tex)); // rechts
		hindernisliste.add(new Hindernis(0, 0, width, 30, tex)); // oben
		hindernisliste.add(new Hindernis(0, height - 60, width, 30, tex)); // unten
		setHindernisliste(hindernisliste);
	}

	public void erstelleLevel() {
		raumliste.add(raum1 = new Raum1(game, tex));
		raumliste.add(raum2 = new Raum2(game, tex));
		raumliste.add(raum3 = new Raum3(game, tex));
		raumliste.add(raum4 = new Raum4(game, tex));
		raumliste.add(raum5 = new Raum5(game, tex));
		raumliste.add(raum6 = new Raum6(game, tex));
		raumliste.add(raum7 = new Raum7(game, tex));

		raum1.erstelleRaum();
		raum2.erstelleRaum();
		raum3.erstelleRaum();
		raum4.erstelleRaum();
		raum5.erstelleRaum();
		raum6.erstelleRaum();
		raum7.erstelleRaum();

		setMaxRaum(raumliste.size());

	}

	protected void erstelleSubHindernisOben() {
		for (int i = 0; i < hindernisliste.size(); i++) {
			int x = (int) hindernisliste.get(i).getxPos();
			int y = (int) hindernisliste.get(i).getyPos() - 10;
			int width = (int) hindernisliste.get(i).getWidth();
			hindernisOben.add(new Hindernis(x, y, width, 1, tex));
		}
		setHindernisOben(hindernisOben);
	}

	protected void erstelleSubHindernisUnten() {
		for (int i = 0; i < hindernisliste.size(); i++) {
			int x = (int) hindernisliste.get(i).getxPos();
			int y = (int) hindernisliste.get(i).getyPos() + (int) hindernisliste.get(i).getHeight() + 10;
			int width = (int) hindernisliste.get(i).getWidth();
			hindernisUnten.add(new Hindernis(x, y, width, 1, tex));
		}
		setHindernisUnten(hindernisUnten);
	}

	protected void erstelleSubHindernisRechts() {
		for (int i = 0; i < hindernisliste.size(); i++) {
			int x = (int) (hindernisliste.get(i).getxPos() + hindernisliste.get(i).getWidth() + 10);
			int y = (int) hindernisliste.get(i).getyPos();
			int height = (int) hindernisliste.get(i).getHeight();
			hindernisRechts.add(new Hindernis(x, y, 1, height, tex));
		}
		setHindernisRechts(hindernisRechts);
	}

	protected void erstelleSubHindernisLinks() {
		for (int i = 0; i < hindernisliste.size(); i++) {
			int x = (int) (hindernisliste.get(i).getxPos() - 10);
			int y = (int) hindernisliste.get(i).getyPos();
			int height = (int) hindernisliste.get(i).getHeight();
			hindernisLinks.add(new Hindernis(x, y, 1, height, tex));
		}
		setHindernisLinks(hindernisLinks);
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
			hindernisOben.get(i).update();
			hindernisUnten.get(i).update();
			hindernisRechts.get(i).update();
			hindernisLinks.get(i).update();
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
		for (int i = 0; i < hindernisliste.size(); i++) {
			hindernisliste.get(i).render(g);
		}
		for (int i = 0; i < gegnerliste.size(); i++) {
			gegnerliste.get(i).render(g);
		}
		for (int i = 0; i < powerupliste.size(); i++) {
			powerupliste.get(i).render(g);
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
	
	public int getAnzPowerup() {
		return anzPowerup;
	}

	public void setAnzPowerup(int anzPowerup) {
		this.anzPowerup = anzPowerup;
	}
	public ArrayList<Hindernis> getHindernisliste() {
		return hindernisliste;
	}

	public void setHindernisliste(ArrayList<Hindernis> hindernisliste) {
		this.hindernisliste = hindernisliste;
	}

	public ArrayList<Hindernis> getHindernisOben() {
		return hindernisOben;
	}

	public void setHindernisOben(ArrayList<Hindernis> hindernisOben) {
		this.hindernisOben = hindernisOben;
	}

	public ArrayList<Hindernis> getHindernisUnten() {
		return hindernisUnten;
	}

	public void setHindernisUnten(ArrayList<Hindernis> hindernisUnten) {
		this.hindernisUnten = hindernisUnten;
	}

	public ArrayList<Hindernis> getHindernisRechts() {
		return hindernisRechts;
	}

	public void setHindernisRechts(ArrayList<Hindernis> hindernisRechts) {
		this.hindernisRechts = hindernisRechts;
	}

	public ArrayList<Hindernis> getHindernisLinks() {
		return hindernisLinks;
	}

	public void setHindernisLinks(ArrayList<Hindernis> hindernisLinks) {
		this.hindernisLinks = hindernisLinks;
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
	
	public void setPowerupliste(ArrayList<Powerup> powerupliste) {
		this.powerupliste = powerupliste;
	}
	public ArrayList <Powerup> getPowerupliste() {
		return powerupliste;
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
		hindernisOben.clear();
		hindernisUnten.clear();
		hindernisRechts.clear();
		hindernisLinks.clear();
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
	
	public Powerup getPowerup() {
		return powerup;
	}

	public void setPowerup(Powerup powerup) {
		this.powerup = powerup;
	}


}
