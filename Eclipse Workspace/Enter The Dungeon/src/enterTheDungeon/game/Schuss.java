package enterTheDungeon.game;

import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Schuss extends StandardObjectData {

	private double speed = 4;
	private double xDelta, yDelta;
	private double xZiel, yZiel;

	public Schuss(double pX, double pY, double pWidth, double pHeight, double xZiel, double yZiel, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		
		xDelta = pX - xZiel;
		yDelta = pY - yZiel;
		double tangente = (double) Math.hypot(xDelta, yDelta);
		xDelta /= tangente;
		yDelta /= tangente;
		xDelta *= speed;
		yDelta *= speed;

//		deltaX = x - xZiel;
//		deltaY = y - yZiel;
//		tangente = (float) Math.hypot(deltaX, deltaY);
//		deltaX /= tangente;
//		deltaY /= tangente;
//		deltaX *= speed;
//		deltaY *= speed;

	}

	public void update() {
		xPos -= xDelta;
		yPos -= yDelta;
//		setxPos(getxPos() -xDelta);
//		setyPos(getyPos() - yDelta);
//		xPos = getxPos();
//		yPos = getyPos();
	}

	public void render(Graphics g) {
		g.drawImage(tex.schuss, (int) xPos, (int) yPos, (int) width, (int) height, null);
	}

}
