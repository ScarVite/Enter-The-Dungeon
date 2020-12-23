package enterTheDungeon.game;

import enterTheDungeon.resource.Texturen;
import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.waffen.typ.Pistole;

public class Spieler extends ExtendedObjectData {

	private Pistole pistole;
	private Lebensbalken lebensbalken;
	private boolean getroffenVonFalle;
	private boolean getroffenVonSchuss;
	private int immunVorSchuss;
	private int immunVorFalle;
	private boolean powerupAn;


	public Spieler(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
//		schussarray = new Schuss[magazinsize];
		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
		pistole.setRenderable(true);
		lebensbalken = new Lebensbalken(xPos, yPos - 20, 10, 10, leben, tex);
		immunVorFalle = 0;
		immunVorSchuss = 0;
	}

	public Waffe getWaffe() {
		return pistole;
	}

	public void render(Graphics g) {
		lebensbalken.render(g);
		g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
		g.drawImage(tex.spieler, (int) xPos, (int) yPos, (int) width, (int) height, null);
		pistole.render(g);

	}

	public void update() {
		lebensbalken.setLeben(getLeben());
		lebensbalken.update((int) xPos, (int) yPos);
		xPos = getxPos();
		yPos = getyPos();
		laufen();
		pistole.update(xPos, yPos);
		if(getroffenVonFalle) {
			immunitaetVorFalle();
		}
		if(getroffenVonSchuss) {
			immunitaetVorSchuss();
		}
		if(powerupAn) {
			
		}
		
	}
	
	private void immunitaetVorSchuss() {
		immunVorSchuss++;
		if(immunVorSchuss == 2) {
			setGetroffenVonSchuss(false);
			immunVorSchuss = 0;
		}
		
	}
	
	private void immunitaetVorFalle() {
		immunVorFalle++;
		if(immunVorFalle == 150) {
			setGetroffenVonFalle(false);
			immunVorFalle = 0;
		}
	}

	private void laufen() {

		if (isLeft()) {
			setxPos(getxPos() - getSpeed());
		}

		if (isRight()) {
			setxPos(getxPos() + getSpeed());
		}

		if (isUp()) {
			setyPos(getyPos() - getSpeed());
		}

		if (isDown()) {
			setyPos(getyPos() + getSpeed());
		}

	}

	public void schiessen(double pX, double pY) {
		pistole.schiessen(pX, pY);
	}

	public boolean isGetroffenVonFalle() {
		return getroffenVonFalle;
	}

	public void setGetroffenVonFalle(boolean getroffen) {
		this.getroffenVonFalle = getroffen;
	}

	public boolean isGetroffenVonSchuss() {
		return getroffenVonSchuss;
	}

	public void setGetroffenVonSchuss(boolean getroffenVonSchuss) {
		this.getroffenVonSchuss = getroffenVonSchuss;
	}
	
	public boolean getPowerupAn() {
		return powerupAn;
	}
	
	public void setPowerupAn(boolean pPowerup) {
		this.powerupAn = pPowerup;
	}



}
