package coolboys.net;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class Attack implements MouseListener {

	// Variablen für Maus
	private static int xPosMouse, yPosMouse;

	// Variablen für Vektor
	public static float xPosStartShoot;
	public static float yPosStartShoot;

	private static float xVelueVector;

	private static float yVelueVector;
	private float bulletSpeed = (float) 1.3;

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (SwingUtilities.isLeftMouseButton(e)) {

			GUI.shoot = true;

			xPosMouse = e.getX();
			yPosMouse = e.getY();

			xPosStartShoot = GUI.getXPlayer() + 170;
			yPosStartShoot = GUI.getYPlayer() + 120;

			float x2 = xPosStartShoot - xPosMouse;
			float y2 = yPosStartShoot - yPosMouse;

			float tangent = (float) java.lang.Math.hypot(x2, y2);

			x2 /= tangent;
			y2 /= tangent;

			x2 *= bulletSpeed;
			y2 *= bulletSpeed;

			xVelueVector = x2;
			yVelueVector = y2;

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		GUI.shoot = false;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		GUI.shoot = false;
	}

	public static void setxPosMouse(int pxPos) {
		xPosMouse = pxPos;
	}

	public static int getxPosMouse() {
		return xPosMouse;
	}

	public static int getyPosMouse() {
		return yPosMouse;
	}

	public static void setyPosMouse(int pyPos) {
		xPosMouse = pyPos;
	}

	public static void update() {
		GUI.shoot = true;
		xPosStartShoot -= xVelueVector;
		yPosStartShoot -= yVelueVector;

	}

}
