package enterTheDungeon.game;

import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.resource.Texturen;

public class Gegner extends ExtendedObjectData {
	
	private Magazin magazin;

	public Gegner(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
//		magazin = new Magazin(xPos, yPos, 10, 10, 5, pTex);
	}

	public void update() {
		xPos = getxPos();
		yPos = getyPos();
//		magazin.update(xPos, yPos);

	}

	public void render(Graphics g) {
		g.drawImage(tex.gegner, (int) xPos, (int) yPos, (int) width, (int) height, null);
//		magazin.render(g);
	}

	public void schiessen() {

	}
	
	private void laufen(){
		
	}

}
