package enterTheDungeon.game.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Spieler;
import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.waffen.typ.Pistole;
import enterTheDungeon.resource.Texturen;


public class Gegner extends ExtendedObjectData {

	private Magazin magazin;
	private Pistole pistole;
	private double xZiel, yZiel;
	private boolean up, down, left, right;
	private boolean nachOben, nachUnten, nachRechts, nachLinks;
	private double xMarker, yMarker; // Zielpunkte zum Hinlaufen
	private int sicht;
	private boolean zielErreicht;
	private double xMitte, yMitte;
	private Rectangle vision;
	private Rectangle collision;
	private int prio = 1;
	private boolean umgehung;
	private double xDelta, yDelta;
	private ArrayList<Hindernis> hindernisliste;
	private ArrayList<Hindernis> hindernisOben, hindernisUnten, hindernisRechts, hindernisLinks;
	private int hindernisnummer;

	private Game game;

	private int feuerrate = 0;

	public Gegner(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex,
			Game pGame) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
		this.game = pGame;
		pistole.setRenderable(false);
//		sicht = pistole.getReichweite() - 100;
		vision = new Rectangle((int) xPos - sicht, (int) yPos - sicht, (int) width + sicht * 2,
				(int) height + sicht * 2);
		collision = new Rectangle((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
		setNachOben(false);
		setNachUnten(false);
		setNachRechts(false);
		setNachLinks(false);
		erstelleMarker();
		hindernisliste = new ArrayList<Hindernis>();
		hindernisOben = new ArrayList<Hindernis>();
		hindernisUnten = new ArrayList<Hindernis>();
		hindernisRechts = new ArrayList<Hindernis>();
		hindernisLinks = new ArrayList<Hindernis>();

	}
	

	public void update() {
		hindernisliste = game.getHindernisListe();
		hindernisOben = game.getHindernisOben();
		hindernisUnten = game.getHindernisUnten();
		hindernisRechts = game.getHindernisRechts();
		hindernisLinks = game.getHindernisLinks();
		xPos = getxPos();
		yPos = getyPos();
		speed = getSpeed();
		nachOben = isNachOben();
		nachUnten = isNachUnten();
		nachRechts = isNachRechts();
		nachLinks = isNachLinks();

		laufen();

		collision.setBounds((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
		vision.setBounds((int) xPos - sicht, (int) yPos - sicht, (int) width + sicht * 2, (int) height + sicht * 2);

		setxMitte(berechneXMitte());
		setyMitte(berechneYMitte());
		pistole.update(xPos, yPos);

		xZiel = game.getxPosSpieler() + game.getWidthSpieler() / 2;
		yZiel = game.getyPosSpieler() + game.getHeightSpieler() / 2;
		feuerrate++;
//		
		if (feuerrate >= 100) {
			schiessen();
			feuerrate = 0;
		}

	}

	public void render(Graphics g) {
		if (isVisible()) {
			g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
			g.drawImage(tex.gegner, (int) xPos, (int) yPos, (int) width, (int) height, null);
			g.setColor(new Color(0).RED);
			g.drawRect((int) xPos - sicht, (int) yPos - sicht, (int) width + sicht * 2, (int) height + sicht * 2);
			g.setColor(new Color(0).PINK);
			g.drawRect((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
			g.setColor(new Color(0).YELLOW);
			g.fillRect((int) xMarker, (int) yMarker, 10, 10);
			g.drawLine((int) getxMitte(), (int) getyMitte(), (int) xMarker, (int) yMarker); // Ziellinie
			g.setColor(new Color(4). cyan);
//			for(int i = 0; i < hindernisOben.size(); i++) {
//				int x = (int) hindernisOben.get(i).getxPos();
//				int y = (int) hindernisOben.get(i).getyPos();
//				int width = (int) hindernisOben.get(i).getWidth();
//				g.fillRect(x, y, width, 1);
//			}
//			for(int i = 0; i < hindernisUnten.size(); i++) {
//				int x = (int) hindernisUnten.get(i).getxPos();
//				int y = (int) hindernisUnten.get(i).getyPos();
//				int width = (int) hindernisUnten.get(i).getWidth();
//				g.fillRect(x, y, width, 1);
//			}
//			
//			for(int i = 0; i < hindernisRechts.size(); i++) {
//				int x = (int) hindernisRechts.get(i).getxPos();
//				int y = (int) hindernisRechts.get(i).getyPos();
//				int height = (int) hindernisRechts.get(i).getHeight();
//				g.fillRect(x, y, 1, height);
//			}
//			
//			for(int i = 0; i < hindernisLinks.size(); i++) {
//				int x = (int) hindernisLinks.get(i).getxPos();
//				int y = (int) hindernisLinks.get(i).getyPos();
//				int height = (int) hindernisLinks.get(i).getHeight();
//				g.fillRect(x, y, 1, height);
//			}
		}
		pistole.render(g);
	}

	public Waffe getWaffe() {
		return pistole;
	}

	private void laufen() {
		vision = vision.getBounds();
		collision = collision.getBounds();
		if (!nachOben && !nachUnten && !nachRechts && !nachLinks) {
			zielErreicht = laufeRichtungMarker();
			if (zielErreicht) {
				erstelleMarker();
				setZielErreicht(false);
			}
			checkCollisionMitHindernisProRichtung();
		}
		
		if(nachOben) {
			nachObenLaufen();
		}
		if(nachUnten) {
			nachUntenLaufen();
		}
		if(nachRechts) {
			nachRechtsLaufen();
		}
		if(nachLinks) {
			nachLinksLaufen();
		}
		if(ueberpruefeMarkerMitGegner()) {
			erstelleMarker();
			checkCollisionMitHindernisProRichtung();
		}
	}

	private boolean checkCollisionMitHindernisProRichtung() {
		hindernisliste = game.getHindernisListe();
		hindernisOben = game.getHindernisOben();
		hindernisUnten = game.getHindernisUnten();
		hindernisRechts = game.getHindernisRechts();
		hindernisLinks = game.getHindernisLinks();

		Rectangle markerRect = new Rectangle((int) xMarker, (int) yMarker, 1, 1);
		for (int i = 0; i < hindernisliste.size() - 1; i++) {
			Rectangle hindiOben = hindernisOben.get(i).getBounds();
			Rectangle hindiUnten = hindernisUnten.get(i).getBounds();
			Rectangle hindiRechts = hindernisRechts.get(i).getBounds();
			Rectangle hindiLinks = hindernisLinks.get(i).getBounds();
			
			if(hindiOben.intersects(collision)) {
				setHindernisnummer(i);
				if(xMarker > xMitte) {
					//nach rechts
					setNachRechts(true);
					
				}
				if(xMarker < xMitte) {
					//nach Links
					setNachLinks(true);
				}
			}
			
			if(hindiUnten.intersects(collision)) {
				setHindernisnummer(i);
				if(xMarker > xMitte) {
					//nach rechts
					setNachRechts(true);
					
				}
				if(xMarker < xMitte) {
					//nach Links
					setNachLinks(true);
				}
			}
			
			if(hindiRechts.intersects(collision)) {
				setHindernisnummer(i);
				if(yMarker > yMitte) {
					//nachUnten
					setNachUnten(true);
				}
				if(yMarker < yMitte) {
					//nach Oben
					setNachOben(true);
				}
				
			}
			
			if(hindiLinks.intersects(collision)) {
				setHindernisnummer(i);
				if(yMarker >= yMitte) {
					//nachUnten
					setNachUnten(true);
					System.out.println("hi");
				}
				if(yMarker < yMitte) {
					//nach Oben
					setNachOben(true);
				}
			}
			
		}
		return false;
	}

	private void nachObenLaufen() {
		setyPos(getyPos() - speed);
		Rectangle geg= collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiLinks = hindernisLinks.get(getHindernisnummer()).getBounds();
		Rectangle hindiRechts = hindernisRechts.get(getHindernisnummer()).getBounds();
		if(!hindiLinks.intersects(geg) && !hindiRechts.intersects(geg)) {
			setNachOben(false);
		}
	}

	private void nachUntenLaufen() {
		setyPos(getyPos() + speed);
		Rectangle geg= collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiLinks = hindernisLinks.get(getHindernisnummer()).getBounds();
		Rectangle hindiRechts = hindernisRechts.get(getHindernisnummer()).getBounds();
		if(!hindiLinks.intersects(geg) && !hindiRechts.intersects(geg)) {
			setNachUnten(false);
		}
		
	}

	private void nachRechtsLaufen() {
		setxPos(getxPos() + speed);
		
		Rectangle geg= collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiUnten = hindernisUnten.get(getHindernisnummer()).getBounds();
		Rectangle hindiOben = hindernisOben.get(getHindernisnummer()).getBounds();
		if(!hindiOben.intersects(geg) && !hindiUnten.intersects(geg)) {
			setNachRechts(false);
		}

	}

	private void nachLinksLaufen() {
		setxPos(getxPos() - speed);
		Rectangle geg= collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiUnten = hindernisUnten.get(getHindernisnummer()).getBounds();
		Rectangle hindiOben = hindernisOben.get(getHindernisnummer()).getBounds();
		if(!hindiOben.intersects(geg) && !hindiUnten.intersects(geg)) {
			setNachLinks(false);
		}
	}

	private boolean laufeRichtungMarker() {
		double xDavor = yMitte;
		double yDavor = yMitte;
		double xDelta = xMitte - xMarker;
		double yDelta = yMitte - yMarker;
		double tangente = Math.hypot(xDelta, yDelta);
		xDelta /= tangente;
		xDelta *= speed;
		xPos -= xDelta;
		yDelta /= tangente;
		yDelta *= speed;
		yPos -= yDelta;
		setyDelta(yDelta);
		setxDelta(xDelta);

		return ueberpruefeMarkerMitGegner();
	}

	private boolean ueberpruefeMarkerMitGegner() {
		Rectangle markerRect = new Rectangle((int) xMarker, (int) yMarker, 1, 1);
		if (markerRect.intersects(collision)) {
			return true;
		}

		return false;
	}

	private void erstelleMarker() {
		boolean check = true;
		do {
			setxMarker(Math.random()* 1500 + 100);
			setyMarker(Math.random() * 900 + 100);
//			setyMarker(200);
			check = ueberpruefeMarkerPos();
		} while (check);

	}

	private boolean ueberpruefeMarkerPos() {

		ArrayList<Hindernis> hindernisliste = new ArrayList<Hindernis>();
		hindernisliste = game.getHindernisListe();
		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			Rectangle markerRect = new Rectangle((int) xMarker, (int) yMarker, 1, 1);
			if (hindi.intersects(markerRect) && isDown()) {
				return true;
			}
		}

		return false;
	}


	public int getHindernisnummer() {
		return hindernisnummer;
	}


	public void setHindernisnummer(int hindernisnummer) {
		this.hindernisnummer = hindernisnummer;
	}


	public double getxMarker() {
		return xMarker;
	}

	public void setxMarker(double xMarker) {
		this.xMarker = xMarker;
	}

	public double getyMarker() {
		return yMarker;
	}

	public void setyMarker(double yMarker) {
		this.yMarker = yMarker;
	}

	private void schiessen() {
		if (vision.intersects(game.getSpielerBounds())) {
			pistole.schiessen(xZiel, yZiel);
		}

	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isNachOben() {
		return nachOben;
	}

	public void setNachOben(boolean nachOben) {
		this.nachOben = nachOben;
	}

	public boolean isNachUnten() {
		return nachUnten;
	}

	public void setNachUnten(boolean nachUnten) {
		this.nachUnten = nachUnten;
	}

	public boolean isNachRechts() {
		return nachRechts;
	}

	public void setNachRechts(boolean nachRechts) {
		this.nachRechts = nachRechts;
	}

	public boolean isNachLinks() {
		return nachLinks;
	}

	public void setNachLinks(boolean nachLinks) {
		this.nachLinks = nachLinks;
	}

	public boolean isZielErreicht() {
		return zielErreicht;
	}

	public void setZielErreicht(boolean zielErreicht) {
		this.zielErreicht = zielErreicht;
	}

	public int getPrio() {
		return prio;
	}

	public void setPrio(int prio) {
		this.prio = prio;
	}

	public double getxMitte() {
		return xMitte;
	}

	public void setxMitte(double xMitte) {
		this.xMitte = xMitte;
	}

	public double getyMitte() {
		return yMitte;
	}

	public void setyMitte(double yMitte) {
		this.yMitte = yMitte;
	}

	public boolean isUmgehung() {
		return umgehung;
	}

	public void setUmgehung(boolean umgehung) {
		this.umgehung = umgehung;
	}

	public double getxDelta() {
		return xDelta;
	}

	public void setxDelta(double xDelta) {
		this.xDelta = xDelta;
	}

	public double getyDelta() {
		return yDelta;
	}

	public void setyDelta(double yDelta) {
		this.yDelta = yDelta;
	}

}
