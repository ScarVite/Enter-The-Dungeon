package enterTheDungeon.game;

import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.ExtendedObjectData;
import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.game.waffen.typ.Pistole;
import enterTheDungeon.resource.Texturen;

public class Gegner extends ExtendedObjectData {
	
	private Magazin magazin;
	private Pistole pistole;

	public Gegner(double pX, double pY, double pWidth, double pHeight, int pLeben, int pSpeed, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pLeben, pSpeed, pTex);
//		magazin = new Magazin(xPos, yPos, 10, 10, 9, this, pTex);
		pistole = new Pistole(pX, pY, pWidth, pHeight, pTex);
	}

	public void update() {
		xPos = getxPos();
		yPos = getyPos();
//		magazin.update(xPos, yPos);
		pistole.update(xPos, yPos);

	}

	public void render(Graphics g) {
		if(isVisible()) {
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
	
	private void laufen(){
		
	}

}
