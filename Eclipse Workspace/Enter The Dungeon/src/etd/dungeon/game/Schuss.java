package etd.dungeon.game;

import java.awt.Color;
import java.awt.Graphics;

import etd.dungeon.game.interfaces.Objekt;
import etd.dungeon.resource.Texturen;

public class Schuss implements Objekt {

	private double x;
	private double y;
	private double speed;
	private double height;
	private double width;
	private double xZiel;
	private double yZiel;
	private Texturen tex;
	private double deltaX;
	private double deltaY;
	private double tangente;

	public Schuss(double x, double y, double pXZiel, double pYZiel, double speed, Texturen pTex) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.xZiel = pXZiel;
		this.yZiel = pYZiel;
		this.tex = pTex;

		setWidth(25);
		setHeight(25);

		// Schusslinie berechnen

		deltaX = x - xZiel;
		deltaY = y - yZiel;
		tangente = (float) Math.hypot(deltaX, deltaY);
		deltaX /= tangente;
		deltaY /= tangente;
		deltaX *= speed;
		deltaY *= speed;

	}

	@Override
	public void update() {
		x -= deltaX;
		y -= deltaY;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(tex.schuss, (int) x, (int) y, null);
	}

	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setWidth(double pWidth) {
		this.width = pWidth;
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHeight(double pHeight) {
		this.height = pHeight;
	}

}
