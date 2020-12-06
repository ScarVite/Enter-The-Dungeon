package enterTheDungeon.game;

import enterTheDungeon.resource.Texturen;
import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.waffen.typ.Pistole;

public class Spieler extends ExtendedObjectData {

	private Pistole pistole;

	public Spieler(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
//		schussarray = new Schuss[magazinsize];

		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
	}
	
	public Waffe getWaffe() {
		return pistole;
	}

	public void render(Graphics g) {
		g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
		g.drawImage(tex.spieler, (int) xPos, (int) yPos, (int) width, (int) height, null);
		pistole.render(g);

	}

	public void update() {
		xPos = getxPos();
		yPos = getyPos();
		laufen();
		pistole.update(xPos, yPos);
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
}
