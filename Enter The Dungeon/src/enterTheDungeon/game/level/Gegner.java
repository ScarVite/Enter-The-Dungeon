package enterTheDungeon.game.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import enterTheDungeon.game.Game;
import enterTheDungeon.game.Lebensbalken;
import enterTheDungeon.game.Spieler;
import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.waffen.typ.Pistole;
import enterTheDungeon.resource.Texturen;
import jdk.internal.org.jline.utils.DiffHelper;
import sun.java2d.loops.DrawLine;

public class Gegner extends ExtendedObjectData {

	protected Magazin magazin;
	protected Pistole pistole;
	protected double xZiel, yZiel;
	protected boolean up, down, left, right;
	protected boolean nachOben, nachUnten, nachRechts, nachLinks;
	protected double xMarker, yMarker; // Zielpunkte zum Hinlaufen
	protected int sicht;
	protected boolean zielErreicht;
	protected double xMitte, yMitte;
	protected Rectangle vision;
	protected Rectangle collision;
	protected int prio = 1;
	protected boolean umgehung;
	protected double xDelta, yDelta;
	protected ArrayList<Hindernis> hindernisliste;
	protected ArrayList<Hindernis> hindernisOben, hindernisUnten, hindernisRechts, hindernisLinks;
	protected int hindernisnummer;
	protected Lebensbalken lebensbalken;
	protected Game game;
	protected int feuerrate;
	private int gegner;
	private int ctr = 0;

	public Gegner(double pX, double pY, double pWidth, double pHeight, int pLeben, double pSpeed, int pFeuerrate, Texturen pTex,
			Game pGame) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
		this.game = pGame;
		this.feuerrate = pFeuerrate;
		lebensbalken = new Lebensbalken(xPos, yPos - 20, 10, 10, leben, tex);

		pistole.setRenderable(false);
		sicht = pistole.getReichweite() - 100;
		vision = new Rectangle((int) xPos - sicht, (int) yPos - sicht, (int) width + sicht * 2,
				(int) height + sicht * 2);
		collision = new Rectangle((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
		setNachOben(false);
		setNachUnten(false);
		setNachRechts(false);
		setNachLinks(false);
		erstelleMarker();
		setSpeed(1.5);
		hindernisliste = new ArrayList<Hindernis>();
		hindernisOben = new ArrayList<Hindernis>();
		hindernisUnten = new ArrayList<Hindernis>();
		hindernisRechts = new ArrayList<Hindernis>();
		hindernisLinks = new ArrayList<Hindernis>();
		setGegner(1);

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

//		laufen();

		collision.setBounds((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
		vision.setBounds(vision.getBounds());

		setxMitte(berechneXMitte());
		setyMitte(berechneYMitte());
		pistole.update(xPos, yPos);

		xZiel = game.getxPosSpieler() + game.getWidthSpieler() / 2;
		yZiel = game.getyPosSpieler() + game.getHeightSpieler() / 2;
		ctr++;
//		
		if (ctr >= getFeuerrate()) {
//			schiessen();
			ctr = 0;
		}
		lebensbalken.setLeben(getLeben());
		lebensbalken.update((int) xPos, (int) yPos);
	}

	public void render(Graphics g) {
		if (isVisible()) {
//			g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
			g.drawImage(tex.gegner, (int) xPos, (int) yPos, (int) width, (int) height, null);
//			g.setColor(new Color(0).RED);
//			g.drawRect((int) xPos - sicht, (int) yPos - sicht, (int) width + sicht * 2, (int) height + sicht * 2);
//			g.setColor(new Color(0).PINK);
//			g.drawRect((int) xPos - 20, (int) yPos - 20, (int) width + 40, (int) height + 40);
			g.setColor(new Color(0).YELLOW);
			g.fillRect((int) xMarker, (int) yMarker, 10, 10);
//			g.drawLine((int) getxMitte(), (int) getyMitte(), (int) xMarker, (int) yMarker); // Ziellinie
//			g.setColor(new Color(4). cyan);
//			for (int i = 0; i < 4; i++) {
//				int x = (int) hindernisOben.get(i).getxPos();
//				int y = (int) hindernisOben.get(i).getyPos();
//				int width = (int) hindernisOben.get(i).getWidth();
//				g.fillRect(x, y, width, 1);
//			}
//			for (int i = 0; i < 4; i++) {
//				int x = (int) hindernisUnten.get(i).getxPos();
//				int y = (int) hindernisUnten.get(i).getyPos();
//				int width = (int) hindernisUnten.get(i).getWidth();
//				g.fillRect(x, y, width, 1);
//			}
//
//			for (int i = 0; i < 4; i++) {
//				int x = (int) hindernisRechts.get(i).getxPos();
//				int y = (int) hindernisRechts.get(i).getyPos();
//				int height = (int) hindernisRechts.get(i).getHeight();
//				g.fillRect(x, y, 1, height);
//			}
//
//			for (int i = 0; i < 4; i++) {
//				int x = (int) hindernisLinks.get(i).getxPos();
//				int y = (int) hindernisLinks.get(i).getyPos();
//				int height = (int) hindernisLinks.get(i).getHeight();
//				g.fillRect(x, y, 1, height);
//			}
//			g.setColor(new Color(8).RED);
//			int x = (int) getxPos();
//			int y = (int) (getyPos() + getHeight());
//			int height = 121;
//			int width = (int) getWidth();
//			g.drawRect(x, y, width, height);
//
//			x = (int) getxPos();
//			y = (int) (getyPos() - 121);
//			height = 121;
//			width = (int) getWidth();
//			g.drawRect(x, y, width, height);
//
//			x = (int) (getxPos() + getWidth());
//			y = (int) (getyPos());
//			height = (int) getHeight();
//			width = 121;
//			g.drawRect(x, y, width, height);
//
//			x = (int) (getxPos() - 121);
//			y = (int) (getyPos());
//			height = (int) getHeight();
//			width = 121;
//			g.drawRect(x, y, width, height);
		}
		pistole.render(g);
		lebensbalken.render(g);
	}

	public Waffe getWaffe() {
		return pistole;
	}

	protected void laufen() {
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

		if (nachOben) {
			nachObenLaufen();
		}
		if (nachUnten) {
			nachUntenLaufen();
		}
		if (nachRechts) {
			nachRechtsLaufen();
		}
		if (nachLinks) {
			nachLinksLaufen();
		}
		if (ueberpruefeMarkerMitGegner()) {
			erstelleMarker();
			checkCollisionMitHindernisProRichtung();
		}
	}

	protected boolean checkCollisionMitHindernisProRichtung() {
		hindernisliste = game.getHindernisListe();
		hindernisOben = game.getHindernisOben();
		hindernisUnten = game.getHindernisUnten();
		hindernisRechts = game.getHindernisRechts();
		hindernisLinks = game.getHindernisLinks();

		Rectangle markerRect = new Rectangle((int) xMarker, (int) yMarker, 1, 1);
		for (int i = 0; i < hindernisliste.size(); i++) {
			Rectangle hindiOben = hindernisOben.get(i).getBounds();
			Rectangle hindiUnten = hindernisUnten.get(i).getBounds();
			Rectangle hindiRechts = hindernisRechts.get(i).getBounds();
			Rectangle hindiLinks = hindernisLinks.get(i).getBounds();

			if (hindiOben.intersects(collision)) {
				setHindernisnummer(i);
				if (i != 3) { // untere Border vom FeldRand
					if (xMarker >= xMitte) {

						if (!ueberpruefeRechts()) { // keine Collision
							setNachRechts(true); // nach Rechts
						} else { // collison
							setNachLinks(true); // nach Links
						}

					}
					if (xMarker < xMitte) {

						if (!ueberpruefeLinks()) { // keine Collision
							setNachLinks(true); // nach Links
						} else { // Collision
							setNachRechts(true); // nach Rechts
						}
					}
					if (ueberpruefeRechts() && ueberpruefeLinks()) {
						setNachOben(true);
						erstelleMarker();
					}
				} else {
					setyPos(getyPos() - speed * 2);
					erstelleMarker();
				}
			}

			if (hindiUnten.intersects(collision)) {
				setHindernisnummer(i);
				if (i != 2) {
					if (xMarker >= xMitte) {

						if (!ueberpruefeRechts()) { // keine Collision
							setNachRechts(true); // nach Rechts
						} else { // collison
							setNachLinks(true); // nach Links
						}

					}
					if (xMarker < xMitte) {

						if (!ueberpruefeLinks()) { // keine Collision
							setNachLinks(true); // nach Links
						} else { // Collision
							setNachRechts(true); // nach Rechts
						}
					}
					if (ueberpruefeRechts() && ueberpruefeLinks()) {
						setNachUnten(true);
						erstelleMarker();
					}
				} else {
					setyPos(getyPos() + speed * 2);
					erstelleMarker();
				}
			}

			if (hindiRechts.intersects(collision)) {
				setHindernisnummer(i);
				if (i != 0) {
					if (yMarker >= yMitte) {

						if (!ueberpruefeUnten()) { // keine Collision
							setNachUnten(true); // nach Unten
						} else { // collision
							setNachOben(true); // nach Oben
						}

					}
					if (yMarker < yMitte) {

						if (!ueberpruefeOben()) { // keine Collision
							setNachOben(true); // nach Oben
						} else { // Collision
							setNachUnten(true); // nach unten
						}
					}
					if (!ueberpruefeOben() && !ueberpruefeUnten()) {
						setNachRechts(true);
						erstelleMarker();
					}

				} else {
					setxPos(getxPos() - speed * 2);
					erstelleMarker();
				}
			}

			if (hindiLinks.intersects(collision)) {
				setHindernisnummer(i);
				if (i != 1) {
					if (yMarker >= yMitte) {

						if (!ueberpruefeUnten()) { // keine Collision
							setNachUnten(true); // nach Unten
						} else { // collision
							setNachOben(true); // nach Oben
						}

					}
					if (yMarker < yMitte) {

						if (!ueberpruefeOben()) { // keine Collision
							setNachOben(true); // nach Oben
						} else { // Collision
							setNachUnten(true); // nach unten
						}
					}

					if (!ueberpruefeOben() && !ueberpruefeUnten()) {
						setNachLinks(true);
						erstelleMarker();
					}
				} else {
					setxPos(getxPos() - speed * 2);
					erstelleMarker();
				}
			}

		}
		return false;
	}

	protected boolean ueberpruefeUnten() {
		int x = (int) getxPos();
		int y = (int) (getyPos() + getHeight());
		int height = 121;
		int width = (int) getWidth();
		Rectangle untenFrei = new Rectangle(x, y, width, height);
		// bei true -> collision sonst keine collision
		return checkCollisionUnten(untenFrei);
	}

	protected boolean ueberpruefeOben() {
		int x = (int) getxPos();
		int y = (int) (getyPos() - 121);
		int height = 101;
		int width = (int) getWidth();
		Rectangle obenFrei = new Rectangle(x, y, width, height);
		// bei true -> collision sonst keine collision
		return checkCollisionOben(obenFrei);
	}

	protected boolean ueberpruefeRechts() {
		int x = (int) (getxPos() + getWidth());
		int y = (int) (getyPos());
		int height = (int) getHeight();
		int width = 101;
		Rectangle rechtsFrei = new Rectangle(x, y, width, height);
		// bei true -> collision sonst keine collision
		return checkCollisionRechts(rechtsFrei);
	}

	protected boolean ueberpruefeLinks() {
		int x = (int) (getxPos()) - 121;
		int y = (int) (getyPos());
		int height = (int) getHeight();
		int width = 101;
		Rectangle linksFrei = new Rectangle(x, y, width, height);
		// bei true -> collision sonst keine collision
		return checkCollisionLinks(linksFrei);
	}

	protected boolean checkCollisionOben(Rectangle rect) {
		for (Hindernis hindernis : hindernisUnten) {
			Rectangle hindiUnten = hindernis.getBounds();
			if (hindiUnten.intersects(rect)) {
				return true;
			}
		}

		return false;
	}

	protected boolean checkCollisionUnten(Rectangle rect) {
		for (Hindernis hindernis : hindernisOben) {
			Rectangle hindiOben = hindernis.getBounds();
			if (hindiOben.intersects(rect)) {
				return true;
			}
		}
		return false;
	}

	protected boolean checkCollisionRechts(Rectangle rect) {
		for (Hindernis hindernis : hindernisLinks) {
			Rectangle hindiLinks = hindernis.getBounds();
			if (hindiLinks.intersects(rect)) {
				return true;
			}
		}
		return false;
	}

	protected boolean checkCollisionLinks(Rectangle rect) {
		for (Hindernis hindernis : hindernisRechts) {
			Rectangle hindiRechts = hindernis.getBounds();
			if (hindiRechts.intersects(rect)) {
				return true;
			}
		}
		return false;
	}

	protected void nachObenLaufen() {
		setyPos(getyPos() - speed);
		Rectangle geg = collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiLinks = hindernisLinks.get(getHindernisnummer()).getBounds();
		Rectangle hindiRechts = hindernisRechts.get(getHindernisnummer()).getBounds();
		if (!hindiLinks.intersects(geg) && !hindiRechts.intersects(geg)) {
			setNachOben(false);
		}
	}

	protected void nachUntenLaufen() {
		setyPos(getyPos() + speed);
		Rectangle geg = collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiLinks = hindernisLinks.get(getHindernisnummer()).getBounds();
		Rectangle hindiRechts = hindernisRechts.get(getHindernisnummer()).getBounds();
		if (!hindiLinks.intersects(geg) && !hindiRechts.intersects(geg)) {
			setNachUnten(false);
		}

	}

	protected void nachRechtsLaufen() {
		setxPos(getxPos() + speed);

		Rectangle geg = collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiUnten = hindernisUnten.get(getHindernisnummer()).getBounds();
		Rectangle hindiOben = hindernisOben.get(getHindernisnummer()).getBounds();
		if (!hindiOben.intersects(geg) && !hindiUnten.intersects(geg)) {
			setNachRechts(false);
		}

	}

	protected void nachLinksLaufen() {
		setxPos(getxPos() - speed);
		Rectangle geg = collision.getBounds();
		geg.setBounds((int) xPos - 40, (int) yPos - 40, (int) width + 80, (int) height + 80);
		Rectangle hindiUnten = hindernisUnten.get(getHindernisnummer()).getBounds();
		Rectangle hindiOben = hindernisOben.get(getHindernisnummer()).getBounds();
		if (!hindiOben.intersects(geg) && !hindiUnten.intersects(geg)) {
			setNachLinks(false);
		}
	}

	protected boolean laufeRichtungMarker() {
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

	protected boolean ueberpruefeMarkerMitGegner() {
		Rectangle markerRect = new Rectangle((int) xMarker, (int) yMarker, 1, 1);
		if (markerRect.intersects(collision)) {
			return true;

		}

		return false;
	}

	protected void erstelleMarker() {
		do {
			setxMarker(Math.random() * 1720 + 200);
			setyMarker(Math.random() * 1000 + 200);
//			setyMarker(600);
//			setxMarker(1900);
		} while (ueberpruefeMarkerPos());

	}

	@SuppressWarnings("deprecation")
	protected boolean ueberpruefeMarkerPos() {

		ArrayList<Hindernis> hindernisliste = new ArrayList<Hindernis>();
		hindernisliste = game.getHindernisListe();
		for (Hindernis hindernis : hindernisliste) {
			Rectangle hindi = hindernis.getBounds();
			if (hindi.inside((int) xMarker, (int) yMarker)) {
				return true;
			}
		}

		return false;
	}

	public int getGegner() {
		return gegner;
	}

	public void setGegner(int gegner) {
		this.gegner = gegner;
	}

	public int getHindernisnummer() {
		return hindernisnummer;
	}

	protected void schiessen() {
		if (vision.intersects(game.getSpielerBounds())) {
			pistole.schiessen(xZiel, yZiel);
		}

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

	public int getFeuerrate() {
		return feuerrate;
	}

	public void setFeuerrate(int feuerrate) {
		this.feuerrate = feuerrate;
	}

	public Rectangle getVision() {
		return vision;
	}

	public void setVision(Rectangle vision) {
		this.vision = vision;
	}

}
