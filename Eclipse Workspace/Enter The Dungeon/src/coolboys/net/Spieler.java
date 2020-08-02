package coolboys.net;

import java.awt.Color;
import java.awt.Graphics;

public class Spieler {

	private static int xPos;
	private static int yPos;
	private int leben;
	private static int width;
	private static int heigth;
	
	private Texturen tex;
	
	public Spieler(int x, int y, Texturen tex) {
		this.xPos =  x;
		this.yPos =  y;
		this.tex = tex;
		setWidth(70);
		setHeigth(156);
		
	}

	public static void update() {
		xPos = getxPos();
		yPos = getyPos();
		width = getWidth();
		heigth = getHeigth();
		

	}

	public static void render(Graphics g) {
		g.drawImage(Texturen.spieler, xPos, yPos, width, heigth, null);
		g.setColor(new Color(heigth).BLACK);
		g.drawRect((int) xPos, (int) yPos, width, heigth);
	}

	public static int getWidth() {
		return width;
	}

	public void setWidth(int pWidth) {
		this.width = pWidth;
	}

	public static int getHeigth() {
		return heigth;
	}

	public void setHeigth(int pHeigth) {
		this.heigth = pHeigth;
	}
	
	public static int getLeben(int pLeben) {
		return pLeben;
	}
	
	public void setLeben(int pLeben) {
		this.leben = pLeben;
	}

	public static int getxPos() {
		return xPos;
	}

	public static void setxPos(int pxPos) {
		Spieler.xPos = pxPos;
	}

	public static int getyPos() {
		return yPos;
	}

	public static void setyPos(int pyPos) {
		Spieler.yPos = pyPos;
	}

}
