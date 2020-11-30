package enterTheDungeon.game.Oberklassen;

import enterTheDungeon.resource.Texturen;

public class StandardObjectData {

	protected double xPos, yPos;
	protected double width, height;
	protected Texturen tex;

	public StandardObjectData(double pX, double pY, double pWidth, double pHeight, Texturen pTex) {
		this.xPos = pX;
		this.yPos = pY;
		this.width = pWidth;
		this.height = pHeight;
		this.tex = pTex;
	}

	public double berechneXMitte() {
		double xMitte;
		xMitte = getxPos() + getWidth() / 2;
		return xMitte;
	}
	
	public double berechneYMitte() {
		double yMitte;
		yMitte = getyPos() + getHeight() / 2;
		return yMitte;
	}
	
	// Get und Set Methoden für alle Attribute
	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Texturen getTex() {
		return tex;
	}

	public void setTex(Texturen tex) {
		this.tex = tex;
	}

}
