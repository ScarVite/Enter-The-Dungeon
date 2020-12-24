package enterTheDungeon.game.level;

import java.awt.Graphics;
import java.awt.Rectangle;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Hindernis extends StandardObjectData {

	private Rectangle oben, unten, rechts, links;

	public Hindernis(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);

	}

	public void render(Graphics g) {
		g.drawImage(tex.hindernis, (int) xPos, (int) yPos, (int) width, (int) height, null);
	}

	public void update() {
		xPos = getxPos();
		yPos = getyPos();
	}
	
	

}
