package enterTheDungeon.game.level;

import java.awt.Graphics;

import enterTheDungeon.game.Hindernis;
import enterTheDungeon.resource.Texturen;

public class Portal extends Hindernis{

	
	public Portal(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
	}

	public void render(Graphics g) {
		g.drawImage(tex.portal, (int) xPos, (int) yPos, (int) width, (int) height, null);
	}
	
	public void update() {
		xPos = getxPos();
		yPos = getyPos();
	}
	

}
