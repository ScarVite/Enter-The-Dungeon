package etd.dungeon.game;

import java.awt.Graphics;

import etd.dungeon.game.interfaces.Objekt;
import etd.dungeon.resource.Texturen;

public class Gegner implements Objekt {

	private double x;
	private double y;
	private double speed;
	private double height;
	private double width;
	private Texturen tex;
	private double xZiel;
	private double yZiel;
	private int feuerrate = 0;

	public Gegner(Texturen t, double x, double y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.tex = t;

		setWidth(125);
		setHeight(125);
		System.out.println("hi");
	}

	public void update() {
		xZiel = Spieler.getX() + Spieler.getWidth() / 2;
		yZiel = Spieler.getY() + Spieler.getHeight() / 2;
		feuerrate++;
		
		if (feuerrate >= 100) {
			Controller.addObjekt(new Schuss(x, y, xZiel, yZiel, 2.5, tex));
			feuerrate = 0;
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.gegner, (int) x, (int) y, (int) width, (int) height, null);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setWidth(double pWidth) {
		this.width = pWidth;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHeight(double pHeight) {
		this.height = pHeight;
	}

}
