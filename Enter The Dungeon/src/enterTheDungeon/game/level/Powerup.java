package enterTheDungeon.game.level;


import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Powerup extends StandardObjectData {


	public Powerup(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
	}

	public void update() {
			
	}

	public void render(Graphics g) {
			g.drawImage(tex.powerUp, (int) xPos, (int) yPos, (int) width, (int) height, null);
	}

}
