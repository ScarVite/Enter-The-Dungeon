package coolboys.net;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Schuss {

	private float xSchuss;
	private float ySchuss;
	private float xStart;
	private float yStart;
	private float tangente;
	private float deltaX;
	private float deltaY;
	private float geschwindigkeit;
	private float xZiel;
	private float yZiel;
	private int bild;
	private static int width;
	private static int heigth;

	private GUI gui;
	private Texturen tex;

	public Schuss(float pXStart, float pYStart, float pXZiel, float pYZiel, int pBild, float geschwindigkeit, Texturen t) {
		this.xStart = pXStart;
		this.yStart = pYStart;
		this.xZiel = pXZiel - 25;
		this.yZiel = pYZiel;
		this.bild = pBild;
		this.geschwindigkeit = geschwindigkeit;
		
		this.tex = t;

		setWidth(25);
		setHeigth(25);

		// Berechnung für die Scad
		deltaY = yStart - yZiel;
		tangente = (float) Math.hypot(deltaX, deltaY);
		deltaX /= tangente;
		deltaY /= tangente;
		deltaX *= geschwindigkeit;
		deltaY *= geschwindigkeit;
		xSchuss = deltaX;
		ySchuss = deltaY;

	}

	public void update() {
		xStart -= xSchuss;
		yStart -= ySchuss;
		width = getWidth();
		heigth = getHeigth();
	}

	public void render(Graphics g) {

		// 0 für Spieler Schüsse grün
		if (bild == 0) {
			g.setColor(new Color(0).GREEN);
			// 1 für Gegner Schüsse rot
		} else if (bild == 1) {
			g.setColor(new Color(0).RED);
		}
//		Schuss wird jetzt als Rechteck gezeichnet
		g.fillRect((int) xStart, (int) yStart, width, heigth);
//		Schuss als Bild zeichnen lassen		
//		g.drawImage(tex.schuss, (int) xStart, (int) yStart, width, heigth,null);
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

}
