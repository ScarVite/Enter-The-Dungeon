package enterTheDungeon.game.waffen.typ;

import java.awt.Graphics;

import enterTheDungeon.game.waffen.Magazin;
import enterTheDungeon.game.waffen.Waffe;
import enterTheDungeon.resource.Texturen;

public class Shotgun extends Waffe{

	
	public Shotgun(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		// TODO Auto-generated constructor stub
		setMagazinsize(9);
		magazin = new Magazin(pX, pY, 10, 10, magazinsize, this,pTex);
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.shotgun, (int) xPos, (int) yPos, (int) width, (int) height, null);
		magazin.render(g);

	}
	
	public void schiessen() {
		
	}
	
	public void update() {
		xPos = getxPos();
		yPos = getyPos();
		magazin.update();
	}

}
