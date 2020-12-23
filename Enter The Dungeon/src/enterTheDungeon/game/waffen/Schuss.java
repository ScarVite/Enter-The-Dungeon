package enterTheDungeon.game.waffen;

import java.awt.Color;
import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Schuss extends Waffe {

	private double speed = 4;
	private double xDelta, yDelta;
	private double xZiel, yZiel, xStart, yStart;
	private int bild = 0;
	private int reichweite;
	private boolean outOfBounds;

	public Schuss(double pX, double pY, double pWidth, double pHeight, double xZiel, double yZiel, int reichweite,Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		this.reichweite = reichweite;
		this.xZiel = xZiel;
		this.yZiel = yZiel;
		this.xStart  = xPos;
		this.yStart = yPos;

		xDelta = pX - xZiel;
		yDelta = pY - yZiel;
		double tangente = (double) Math.hypot(xDelta, yDelta);
		xDelta /= tangente;
		yDelta /= tangente;
		xDelta *= speed;
		yDelta *= speed;
		

	}

	public void update() {
		xPos -= xDelta;
		yPos -= yDelta;
		bild = getBild();
		int abstand = checkReichweite(xPos, yPos, xStart, yStart);
		
		if(abstand > reichweite) {
			setOutOfBounds(true);
		}
		else {
			setOutOfBounds(false);
		}
	}
	
	private int checkReichweite(double xPos, double yPos, double xStart, double yStart) {
		double xDelta = xPos - xStart;
		double yDelta = yPos - yStart;
		double abstand = Math.hypot(xDelta, yDelta);
		return (int) abstand;
	}

	public void render(Graphics g) {
		int uff = 0;
		while (uff < 1000) {
			uff++;
			xZiel -= xDelta;
			yZiel -= yDelta;
		}

		g.drawLine((int) xPos, (int) yPos, (int) xZiel, (int) yZiel);

		if (bild == 1) {
			g.drawImage(tex.feuerball1, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}
		if (bild == 2) {
			g.drawImage(tex.feuerball2, (int) xPos, (int) yPos, (int) width, (int) height, null);
		}

		g.setColor(new Color(0).GREEN);
		g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
	}

	public int getBild() {
		return bild;
	}

	public void setBild(int pBild) {
		this.bild = pBild;
	}

	public boolean isOutOfBounds() {
		return outOfBounds;
	}

	public void setOutOfBounds(boolean outOfBounds) {
		this.outOfBounds = outOfBounds;
	}

}
