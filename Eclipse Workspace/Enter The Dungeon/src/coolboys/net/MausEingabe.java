package coolboys.net;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class MausEingabe implements MouseListener {

	// Variablen für Maus
	private static float xPosMouse;

	private static float yPosMouse;

	// Variablen für Vektor
	public static float xPosStartShoot;
	public static float yPosStartShoot;

	private static float xVelueVector;

	private static float yVelueVector;
	private float bulletSpeed = (float) 5.9;

	private GUI gui;

	static BufferedImage bullet;

	public MausEingabe(GUI gui) throws IOException {
		this.gui = gui;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gui.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gui.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (SwingUtilities.isLeftMouseButton(e)) {

			setxPosMouse(e.getX());
			setyPosMouse(e.getY());

			gui.mouseReleased(e);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		gui.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gui.mouseExited(e);
	}

	public static void update() {
		xPosStartShoot -= xVelueVector;
		yPosStartShoot -= yVelueVector;

	}

	public static float getxPosMouse() {
		return xPosMouse;
	}

	public void setxPosMouse(int xPosMouse) {
		this.xPosMouse = xPosMouse;
	}

	public static float getyPosMouse() {
		return yPosMouse;
	}

	public void setyPosMouse(int yPosMouse) {
		this.yPosMouse = yPosMouse;
	}
}
