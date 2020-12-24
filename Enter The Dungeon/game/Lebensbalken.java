package enterTheDungeon.game;

import java.awt.Color;
import java.awt.Graphics;

import enterTheDungeon.game.Oberklassen.StandardObjectData;
import enterTheDungeon.resource.Texturen;

public class Lebensbalken extends StandardObjectData {
	
	private int leben;

	public Lebensbalken(double pX, double pY, double pWidth, double pHeight, int leben,Texturen pTex) {
		super(pX, pY, pWidth, pHeight, pTex);
		this.leben = leben;
	}
	
	public void render(Graphics g) {
		int x = (int)getxPos();
		for(int i = 0; i < leben; i++) {
			g.setColor(new Color(0).BLUE);
			g.fillRect(x, (int) yPos, (int) width, (int) height);
			x += height + 1;
		}
	}
	
	public void update(int pX, int pY) {
		leben = getLeben();
		this.xPos = pX;
		this.yPos = pY - 20;
	}
	
	public int getLeben() {
		return leben;
	}
	
	public void setLeben(int pLeben) {
		this.leben = pLeben;
	}

}
