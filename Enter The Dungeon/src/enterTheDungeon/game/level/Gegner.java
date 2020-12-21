package enterTheDungeon.game.level;

import java.awt.Graphics;

import enterTheDungeon.game.Game;
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

	private Game game;

	private int feuerrate = 0;

	public Gegner(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex,
			Game pGame) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
		this.game = pGame;
	}

	public void update() {
		xPos = getxPos();
		yPos = getyPos();
		laufen();
//		magazin.update(xPos, yPos);
		pistole.update(xPos, yPos);

		xZiel = game.getxPosSpieler() + game.getWidthSpieler() / 2;
		yZiel = game.getyPosSpieler() + game.getHeightSpieler() / 2;
		feuerrate++;
//		
		if (feuerrate >= Math.random() * 400 + 90) {
			pistole.schiessen(xZiel, yZiel);
			feuerrate = 0;
		}

	}


	public void render(Graphics g) {
		if (isVisible()) {
			g.drawRect((int) xPos, (int) yPos, (int) width, (int) height);
			g.drawImage(tex.gegner, (int) xPos, (int) yPos, (int) width, (int) height, null);
//		magazin.render(g);
		}
		pistole.render(g);
	}

	public Waffe getWaffe() {
		return pistole;
	}

	public void schiessen() {

	}

	private void laufen() {
		setxPos(getxPos() + 1);
		setRight(true);
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

}
